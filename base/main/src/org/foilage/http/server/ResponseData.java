package org.foilage.http.server;

import org.foilage.http.StatusCode;

public interface ResponseData {

    public StatusCode getResponseCode();

    public void setResponseCode(StatusCode responseCode);
}
