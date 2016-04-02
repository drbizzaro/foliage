package org.foilage.http.server;

import org.foilage.http.StatusCode;

public class ResponseDataImpl implements ResponseData {

    private StatusCode responseCode;

    public ResponseDataImpl() {

        this.responseCode = StatusCode.OK_200;
    }

    public ResponseDataImpl(StatusCode responseCode) {

        this.responseCode = responseCode;
    }

    public StatusCode getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(StatusCode responseCode) {
        this.responseCode = responseCode;
    }
}
