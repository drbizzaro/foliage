package org.foilage.http.server;

import org.foilage.http.exceptions.HttpRedirect;

public abstract class PreEndPointLogicWorker {

    public abstract void performLogic(HttpServerEnvironment serverEnv, RequestData req);

}
