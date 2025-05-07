package org.com.MSBank.login_service.login_service.exception;

public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException() {
        super();
    }

    public UnauthorizedException(String message){
        super(message);
    }
}
