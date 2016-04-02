package org.foilage.authorization.exceptions;

public class NotAuthorizedException extends Exception {

    public NotAuthorizedException() {
    }

    public NotAuthorizedException(String message) {
        super(message);
    }
}
