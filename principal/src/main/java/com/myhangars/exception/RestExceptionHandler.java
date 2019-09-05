package com.myhangars.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(GenericException.NotFound.class)
    protected ResponseEntity<ApiError> handleGenericNotFound(GenericException.NotFound exception) {

        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
        apiError.setMessage(exception.getMessage());

        return new ResponseEntity<ApiError>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(GenericException.AlreadyExists.class)
    protected ResponseEntity<ApiError> handleGenericAlreadyExists(GenericException.AlreadyExists exception) {

        ApiError apiError = new ApiError(HttpStatus.CONFLICT);
        apiError.setMessage(exception.getMessage());

        return new ResponseEntity<ApiError>(apiError, apiError.getStatus());
    }
}
