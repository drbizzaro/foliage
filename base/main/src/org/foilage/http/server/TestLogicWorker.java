package org.foilage.http.server;

public class TestLogicWorker extends LogicWorker<TestResponseData> {

    public TestLogicWorker() {
    }

    @Override
    public void performLogic(HttpServerEnvironment serverEnv, RequestData req, TestResponseData resp) {

        resp.setTest(24);
    }
}
