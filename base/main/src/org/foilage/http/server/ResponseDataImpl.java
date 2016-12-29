package org.foilage.http.server;

import org.foilage.http.StatusCode;

import static org.foilage.utils.checkers.NullChecker.notNull;

public class ResponseDataImpl implements ResponseData {

    private StatusCode responseCode;

    private String title;

    public ResponseDataImpl() {

        this.responseCode = StatusCode.OK_200;
    }

    public ResponseDataImpl(StatusCode responseCode) {

        this.responseCode = notNull(responseCode);
    }

    public StatusCode getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(StatusCode responseCode) {
        this.responseCode = notNull(responseCode);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
