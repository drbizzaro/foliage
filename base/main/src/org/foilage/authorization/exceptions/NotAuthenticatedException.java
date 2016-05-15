package org.foilage.authorization.exceptions;

public class NotAuthenticatedException extends Exception {

    public NotAuthenticatedException() {
    }

    public NotAuthenticatedException(String message) {
        super(message);
    }
}
