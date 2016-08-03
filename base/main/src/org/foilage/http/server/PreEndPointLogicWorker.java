package org.foilage.http.server;

public abstract class PreEndPointLogicWorker {

    public abstract void performLogic(HttpServerEnvironment serverEnv, RequestData req);

}
