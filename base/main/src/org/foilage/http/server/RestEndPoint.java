package org.foilage.http.server;

import org.foilage.authorization.Role;
import org.foilage.utils.DateUtil;
import org.json.JSONObject;

import java.util.Date;
import java.util.List;

import static org.foilage.http.StatusCode.NOT_IMPLEMENTED_501;

public abstract class RestEndPoint extends ServerEndPoint {

    protected RestEndPoint(List<String> path, List<Role> accessRoles, List<Role> denyRoles, List<LogicWorker> preRenderLogicList) {
        super(path, accessRoles, denyRoles, preRenderLogicList);
    }

    protected RestEndPoint(List<String> path, List<Role> accessRoles, List<Role> denyRoles, boolean catchAllBelow, List<LogicWorker> preRenderLogicList) {
        super(path, accessRoles, denyRoles, catchAllBelow, preRenderLogicList);
    }

    public byte[] renderEndPointResponse(HttpServerEnvironment serverEnv, RequestData req, ResponseData resp) {

        StringBuilder sb = new StringBuilder();

        String responseData = renderSpecificEndPointResponse(serverEnv, req, resp);

        if(responseData.startsWith("HTTP/1.1")) {

            sb.append(responseData);

        } else {

            sb.append("HTTP/1.1 ");
            sb.append(resp.getResponseCode().getId());
            sb.append(" ");
            sb.append(resp.getResponseCode().getName());
            sb.append("\r\n");

            sb.append("Date: ");
            sb.append(dateFormat.format(DateUtil.stepBack(new Date(), 3600000)));
            sb.append(" GMT\r\n");

            sb.append("Server: ");
            sb.append(serverEnv.getServerName());
            sb.append("\r\n");

            sb.append("Connection: close\r\n");
            sb.append("\r\n");
            sb.append(responseData);
            sb.append("\r\n");
        }

        return sb.toString().getBytes();
    }

    @Override
    protected String renderSpecificEndPointResponse(HttpServerEnvironment serverEnv, RequestData req, ResponseData resp) {

        switch (req.getMethod()) {

            case GET:
                return renderEndPointGetResponse(serverEnv, req, resp);
            case POST:
                return renderEndPointPostResponse(serverEnv, req, resp);
            case HEAD:
                 return renderEndPointHeadResponse(serverEnv, req, resp);
            case PUT:
                return renderEndPointPutResponse(serverEnv, req, resp);
            case DELETE:
                return renderEndPointDeleteResponse(serverEnv, req, resp);
            case CONNECT:
                return renderEndPointConnectResponse(serverEnv, req, resp);
            case OPTIONS:
                return renderEndPointOptionsResponse(serverEnv, req, resp);
            case TRACE:
                return renderEndPointTraceResponse(serverEnv, req, resp);
        }

        return renderGeneralNotImplementedResponse(serverEnv);
    }

    protected String renderGeneralNotImplementedResponse(HttpServerEnvironment serverEnvironment) {

        StringBuilder sb = new StringBuilder();

        sb.append("HTTP/1.1 "+ NOT_IMPLEMENTED_501.getId()+" "+NOT_IMPLEMENTED_501.getName()+"\r\n");
        sb.append("Date: "+dateFormat.format(DateUtil.stepBack(new Date(), 3600000))+" GMT\r\n");
        sb.append("Server: "+serverEnvironment.getServerName()+"\r\n");
        sb.append("Connection: close\r\n");

        return sb.toString();
    }

    protected String renderEndPointGetResponse(HttpServerEnvironment serverEnvironment, RequestData req, ResponseData resp) {

        return renderGeneralNotImplementedResponse(serverEnvironment);
    }

    protected String renderEndPointHeadResponse(HttpServerEnvironment serverEnvironment, RequestData req, ResponseData resp) {

        return renderGeneralNotImplementedResponse(serverEnvironment);
    }

    protected String renderEndPointPostResponse(HttpServerEnvironment serverEnvironment, RequestData req, ResponseData resp) {

        return renderGeneralNotImplementedResponse(serverEnvironment);
    }

    protected String renderEndPointPutResponse(HttpServerEnvironment serverEnvironment, RequestData req, ResponseData resp) {

        return renderGeneralNotImplementedResponse(serverEnvironment);
    }

    protected String renderEndPointDeleteResponse(HttpServerEnvironment serverEnvironment, RequestData req, ResponseData resp) {

        return renderGeneralNotImplementedResponse(serverEnvironment);
    }

    protected String renderEndPointConnectResponse(HttpServerEnvironment serverEnvironment, RequestData req, ResponseData resp) {

        return renderGeneralNotImplementedResponse(serverEnvironment);
    }

    protected String renderEndPointOptionsResponse(HttpServerEnvironment serverEnvironment, RequestData req, ResponseData resp) {

        return renderGeneralNotImplementedResponse(serverEnvironment);
    }

    protected String renderEndPointTraceResponse(HttpServerEnvironment serverEnvironment, RequestData req, ResponseData resp) {

        return renderGeneralNotImplementedResponse(serverEnvironment);
    }
}
