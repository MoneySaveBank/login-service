package org.com.MSBank.login_service.login_service.exception;

public class ForbiddenException extends RuntimeException{

    public ForbiddenException() {
        super();
    }

    public ForbiddenException(String message) {
        super(message);
    }
}
