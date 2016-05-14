package org.foilage.http.server;

import org.foilage.authorization.exceptions.NotAuthorizedException;
import org.foilage.http.RequestMethod;
import org.foilage.http.StatusCode;
import org.foilage.http.exceptions.HttpRequestLineException;
import org.foilage.http.exceptions.URLNotFoundException;
import org.pmw.tinylog.Logger;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;

public class ClientSocketThread implements Runnable {

    private final Socket socket;

    private final HttpServerEnvironment serverEnv;

    private final static RequestData errorRequest = new RequestData(RequestMethod.GET, "", new HashMap<String,String>(), new HashMap<String,String>());

    public ClientSocketThread(Socket socket, HttpServerEnvironment serverEnv) {

        this.socket = socket;
        this.serverEnv = serverEnv;
    }

    @Override
    public void run() {

        Logger.info("Call: " + Thread.currentThread().getName() + " count: " + Thread.activeCount());

        try {

            OutputStream out = socket.getOutputStream();

            try {

                RequestData req = RequestReader.INSTANCE.readInData(socket.getInputStream(), serverEnv.getBufferSize());

                Logger.info(req.getMethod().name());

                try {

                    if (req.getHeaderMap().containsKey("accept") && req.getHeaderMap().get("accept").startsWith("image")) {

                        FileRenderer.INSTANCE.renderFile(socket.getOutputStream(), req.getUrl(), serverEnv.getDataFilesRoot());

                    } else if (req.getHeaderMap().containsKey("accept") && req.getHeaderMap().get("accept").startsWith("text/css")) {

                        FileRenderer.INSTANCE.renderFile(socket.getOutputStream(), req.getUrl(), serverEnv.getDataFilesRoot());

                    } else {

                        ServerEndPoint endPoint = serverEnv.findEndPointByUrl(req.getUrl());

                        ResponseData resp = endPoint.createResponseData();

                        List<LogicWorker> logicWorkerList = endPoint.getPreRenderLogicList();
                        for (LogicWorker logicWorker : logicWorkerList) {

                            logicWorker.performLogic(serverEnv, req, resp);
                        }

                        out.write(endPoint.renderEndPointResponse(serverEnv, req, resp));
                    }

                } catch (URLNotFoundException e) {

                    try {

                        FileRenderer.INSTANCE.renderFile(socket.getOutputStream(), req.getUrl(), serverEnv.getDataFilesRoot());

                    } catch (NotAuthorizedException ex) {

                        out.write(serverEnv.findErrorEndPointByStatusCode(StatusCode.NOT_FOUND_404).renderEndPointResponse(serverEnv, errorRequest, new ResponseDataImpl(StatusCode.NOT_FOUND_404)));

                        Logger.error(e.getMessage());

                    } catch (Exception ex) {

                        out.write(serverEnv.findErrorEndPointByStatusCode(StatusCode.NOT_FOUND_404).renderEndPointResponse(serverEnv, errorRequest, new ResponseDataImpl(StatusCode.NOT_FOUND_404)));

                        Logger.info(e.getMessage());
                    }

                } catch (NotAuthorizedException e) {

                    out.write(serverEnv.findErrorEndPointByStatusCode(StatusCode.NOT_FOUND_404).renderEndPointResponse(serverEnv, errorRequest, new ResponseDataImpl(StatusCode.NOT_FOUND_404)));

                    Logger.error(e.getMessage());

                } catch (IOException e) {

                    out.write(serverEnv.findErrorEndPointByStatusCode(StatusCode.NOT_FOUND_404).renderEndPointResponse(serverEnv, errorRequest, new ResponseDataImpl(StatusCode.NOT_FOUND_404)));

                    Logger.error(e.getMessage());
                }

            } catch (HttpRequestLineException e) {

                out.write(serverEnv.findErrorEndPointByStatusCode(StatusCode.BAD_REQUEST_400).renderEndPointResponse(serverEnv, errorRequest, new ResponseDataImpl(StatusCode.BAD_REQUEST_400)));

                Logger.info(e.getMessage());
            }

            out.flush();
            out.close();

        } catch (IOException e) {

            Logger.error(e.getMessage());
        }
    }
}
