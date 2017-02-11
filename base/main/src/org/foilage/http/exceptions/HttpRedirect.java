package org.foilage.http.exceptions;

public class HttpRedirect extends Exception {

    private final String redirectURL;

    public HttpRedirect(String message, String redirectURL) {
        super(message);

        this.redirectURL = redirectURL;
    }

    public String getRedirectURL() {
        return redirectURL;
    }
}
