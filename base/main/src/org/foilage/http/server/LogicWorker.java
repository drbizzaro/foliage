package org.foilage.http.server;

import org.foilage.http.exceptions.HttpRedirect;

public abstract class LogicWorker<R extends ResponseData> {

    public abstract void performLogic(HttpServerEnvironment serverEnv, RequestData req, R resp) throws HttpRedirect;

}
