package org.foilage.http.server;

import org.foilage.authorization.Role;
import org.foilage.utils.DateUtil;
import org.pmw.tinylog.Logger;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class ServerEndPoint <R extends ResponseData> {

    protected final static SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMMM YYYY HH:mm:ss");

    private final List<String> path;

    private final boolean catchAllBelow;

    private final List<Role> accessRoles;

    private final List<Role> denyRoles;

    protected final List<LogicWorker> preRenderLogicList;

    protected ServerEndPoint(List<String> path, List<Role> accessRoles, List<Role> denyRoles) {

        this.path = path;
        this.accessRoles = accessRoles;
        this.denyRoles = denyRoles;
        this.catchAllBelow = false;
        this.preRenderLogicList = new ArrayList<>();
    }

    protected ServerEndPoint(List<String> path, List<Role> accessRoles, List<Role> denyRoles, List<LogicWorker> preRenderLogicList) {

        this.path = path;
        this.accessRoles = accessRoles;
        this.denyRoles = denyRoles;
        this.catchAllBelow = false;
        this.preRenderLogicList = preRenderLogicList;
    }

    protected ServerEndPoint(List<String> path, List<Role> accessRoles, List<Role> denyRoles, boolean catchAllBelow, List<LogicWorker> preRenderLogicList) {

        this.path = path;
        this.accessRoles = accessRoles;
        this.denyRoles = denyRoles;
        this.catchAllBelow = catchAllBelow;
        this.preRenderLogicList = preRenderLogicList;
    }

    public List<String> getPath() {
        return path;
    }

    public boolean isCatchAllBelow() {
        return catchAllBelow;
    }

    public List<LogicWorker> getPreRenderLogicList() {
        return preRenderLogicList;
    }

    public byte[] renderEndPointResponse(HttpServerEnvironment serverEnv, RequestData req, R resp) {

        StringBuilder sb = new StringBuilder();

        String responseData = renderSpecificEndPointResponse(serverEnv, req, resp);

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

        if(serverEnv.isSessionsActive() && (req.getSession()!=null && req.getSession().isCreateClientCookie())) {

            sb.append("Set-Cookie:");
            sb.append("X-FOILAGE-SESSION-ID=");
            sb.append(req.getSession().getSessionId());
            sb.append("; domain=");
            sb.append(serverEnv.getDomain());
            sb.append(";path=/;\r\n");

            req.getSession().setCreateClientCookie(false);
        }

        sb.append("Connection: close\r\n");
        sb.append("\r\n");
        sb.append(responseData);
        sb.append("\r\n");

        Logger.trace("-------------RESPONSE-------------");
        Logger.trace(sb.toString());

        try {

            return sb.toString().getBytes("utf-8");

        } catch (UnsupportedEncodingException e) {

            return sb.toString().getBytes();
        }
    }

    protected abstract String renderSpecificEndPointResponse(HttpServerEnvironment serverEnv, RequestData req, R resp);

    protected abstract R createResponseData();

    public List<Role> getAccessRoles() {
        return accessRoles;
    }

    public List<Role> getDenyRoles() {
        return denyRoles;
    }
}
