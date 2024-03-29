package org.foilage.http.server;

import org.foilage.authorization.Role;
import org.foilage.authorization.exceptions.NotAuthenticatedException;
import org.foilage.authorization.exceptions.NotAuthorizedException;
import org.foilage.http.RequestMethod;
import org.foilage.http.StatusCode;
import org.foilage.http.exceptions.HttpRedirect;
import org.foilage.http.exceptions.HttpRequestLineException;
import org.foilage.http.exceptions.URLNotFoundException;
import org.foilage.utils.log.Log;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClientSocketThread implements Runnable {

    private final Socket socket;

    private final HttpServerEnvironment serverEnv;

    private final static RequestData errorRequest = new RequestData(RequestMethod.GET, "", "", new HashMap<String,String>(), new HashMap<String,String>(), new ArrayList<>(), "");

    public ClientSocketThread(Socket socket, HttpServerEnvironment serverEnv) {

        this.socket = socket;
        this.serverEnv = serverEnv;
    }

    @Override
    public void run() {

        Log.debug("Call: " + Thread.currentThread().getName() + " count: " + Thread.activeCount());
        long startTime = System.currentTimeMillis();

        try {

            OutputStream out = socket.getOutputStream();

            try {

                RequestData req = RequestReader.INSTANCE.readInData(serverEnv.getBaseUrl(), socket.getInputStream(), serverEnv.getBufferSize());

                req.setSession(SessionStore.I.getSession(req.getHeader("cookie"), socket.getRemoteSocketAddress().toString().split(":")[0].replaceAll("/","")));
                errorRequest.setSession(req.getSession());

                for(PreEndPointLogicWorker preWorker: serverEnv.getPreEndPointWorkers()) {

                    preWorker.performLogic(serverEnv, req);
                }

                Log.debug(req.getMethod().name());

                try {

                    if (req.getHeaderMap().containsKey("accept") && req.getHeaderMap().get("accept").startsWith("image")) {

                        FileRenderer.INSTANCE.renderFile(out, req.getUrl(), serverEnv.getDataFilesRoot());

                    } else if (req.getHeaderMap().containsKey("accept") && req.getHeaderMap().get("accept").startsWith("text/css")) {

                        FileRenderer.INSTANCE.renderFile(out, req.getUrl(), serverEnv.getDataFilesRoot());

                    } else {

                        ServerEndPoint endPoint = serverEnv.findEndPointByUrl(req.getUrl());

                        authorize(endPoint, req.getSession().getRoleList());

                        ResponseData resp = endPoint.createResponseData();

                        List<LogicWorker> logicWorkerList = endPoint.getPreRenderLogicList();
                        for (LogicWorker logicWorker : logicWorkerList) {

                            logicWorker.performLogic(serverEnv, req, resp);
                        }

                        out.write(endPoint.renderEndPointResponse(serverEnv, req, resp));
                    }

                } catch (URLNotFoundException e) {

                    try {

                        FileRenderer.INSTANCE.renderFile(out, req.getUrl(), serverEnv.getDataFilesRoot());

                    } catch (NotAuthorizedException ex) {

                        out.write(serverEnv.findErrorEndPointByStatusCode(StatusCode.FORBIDDEN_403).renderEndPointResponse(serverEnv, errorRequest, new ResponseDataImpl(StatusCode.NOT_FOUND_404)));

                        Log.error(e.getMessage());

                    } catch (Exception ex) {

                        out.write(serverEnv.findErrorEndPointByStatusCode(StatusCode.NOT_FOUND_404).renderEndPointResponse(serverEnv, errorRequest, new ResponseDataImpl(StatusCode.NOT_FOUND_404)));

                        Log.info(ex.getMessage());
                    }

                } catch(NotAuthenticatedException e) {

                    out.write(serverEnv.findErrorEndPointByStatusCode(StatusCode.NOT_AUTHORIZED_401).renderEndPointResponse(serverEnv, errorRequest, new ResponseDataImpl(StatusCode.NOT_AUTHORIZED_401)));

                } catch (NotAuthorizedException e) {

                    out.write(serverEnv.findErrorEndPointByStatusCode(StatusCode.FORBIDDEN_403).renderEndPointResponse(serverEnv, errorRequest, new ResponseDataImpl(StatusCode.NOT_FOUND_404)));

                    Log.error(e.getMessage());

                }  catch (HttpRedirect e) {

                    out.write(serverEnv.getRedirectData(e.getRedirectURL()).getBytes("UTF-8"));

                } catch (IOException e) {

                    out.write(serverEnv.findErrorEndPointByStatusCode(StatusCode.NOT_FOUND_404).renderEndPointResponse(serverEnv, errorRequest, new ResponseDataImpl(StatusCode.NOT_FOUND_404)));

                    Log.error(e.getMessage());
                }

            } catch (HttpRequestLineException e) {

                out.write(serverEnv.findErrorEndPointByStatusCode(StatusCode.BAD_REQUEST_400).renderEndPointResponse(serverEnv, errorRequest, new ResponseDataImpl(StatusCode.BAD_REQUEST_400)));

                Log.info(e.getMessage());
            }

            out.flush();
            out.close();

        } catch (IOException e) {

            Log.error(e.getMessage());
        }

        Log.debug("Call time in milliseconds: "+(System.currentTimeMillis()-startTime));

    }

    private void authorize(ServerEndPoint endPoint, List<Role> roleList) throws NotAuthenticatedException, NotAuthorizedException {

        boolean denied = false;

        boolean authorized = false;

        for(Role role: roleList) {

            if(endPoint.getDenyRoles().contains(role)) {

                if(role==Role.NOT_AUTHENTICATED) {

                    throw new NotAuthenticatedException();

                } else {

                    denied = true;
                }
            }

            if(endPoint.getAccessRoles().contains(role)) {

                authorized = true;
            }
        }

        if(!authorized || denied) {

            throw new NotAuthorizedException();
        }
    }

}
