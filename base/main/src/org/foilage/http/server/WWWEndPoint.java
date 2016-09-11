package org.foilage.http.server;

import org.foilage.authorization.Role;

import java.util.List;

public abstract class WWWEndPoint<R extends ResponseData> extends ServerEndPoint<R> {

    protected WWWEndPoint(List<String> path, List<Role> accessRoles, List<Role> denyRoles) {
        super(path, accessRoles, denyRoles);
    }

    protected WWWEndPoint(List<String> path, List<Role> accessRoles, List<Role> denyRoles, List<LogicWorker> preRenderLogicList) {
        super(path, accessRoles, denyRoles, preRenderLogicList);
    }

    protected WWWEndPoint(List<String> path, List<Role> accessRoles, List<Role> denyRoles, boolean catchAllBelow, List<LogicWorker> preRenderLogicList) {
        super(path, accessRoles, denyRoles, catchAllBelow, preRenderLogicList);
    }

    @Override
    protected String renderSpecificEndPointResponse(HttpServerEnvironment serverEnv, RequestData req, R resp) {

        StringBuilder sb = new StringBuilder();

        sb.append("<!DOCTYPE html>\n");
        sb.append("<html>\n");

        sb.append(renderHead(req, resp));
        sb.append(renderBody(req, resp));

        sb.append("</html>");

        return sb.toString();
    }

    protected abstract String renderHead(RequestData req, R resp);

    protected abstract String renderBody(RequestData req, R resp);
}
