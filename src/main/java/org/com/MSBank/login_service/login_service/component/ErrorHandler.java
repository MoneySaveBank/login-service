package org.com.MSBank.login_service.login_service.component;

import jakarta.servlet.http.HttpServletRequest;
import org.com.MSBank.login_service.login_service.exception.ForbiddenException;
import org.com.MSBank.login_service.login_service.exception.UnauthorizedException;
import org.com.MSBank.login_service.login_service.model.ErrorResponse;
import org.com.MSBank.login_service.login_service.model.ErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handlerUnauthorizedException(HttpServletRequest req, UnauthorizedException ex){

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(HttpStatus.UNAUTHORIZED.value());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setLocation(req.getRequestURI());
        errorResponse.setType(ErrorType.ERROR);

        System.out.println(errorResponse);

        return errorResponse;
    }

    @ExceptionHandler(HttpClientErrorException.Forbidden.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handlerForbiddenException(HttpServletRequest req, ForbiddenException ex){

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(HttpStatus.FORBIDDEN.value());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setLocation(req.getRequestURI());
        errorResponse.setType(ErrorType.ERROR);

        System.out.println(errorResponse);

        return errorResponse;
    }
}
