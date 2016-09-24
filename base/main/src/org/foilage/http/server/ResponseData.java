package org.foilage.http.server;

import org.foilage.http.StatusCode;

public interface ResponseData {

    StatusCode getResponseCode();

    void setResponseCode(StatusCode responseCode);
}
