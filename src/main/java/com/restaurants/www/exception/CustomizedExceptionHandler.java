package com.restaurants.www.exception;

import com.restaurants.www.dto.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Customized Exceptions for the Restaurant class can be implemented here. It is global exception
 * handler for the application.
 */
@ControllerAdvice
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * This method is used to handle the Restaurant Exception and provide failure details to UI.
     *
     * @param exception
     * @return ResponseEntity object with exception cause and messages.
     */
    @ExceptionHandler(RestraurantException.class)
    public ResponseEntity<Object> handleException(RestraurantException exception) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getCode(), exception.getMessage());
        return new ResponseEntity<Object>(exceptionResponse, exception.getHttpStatus());
    }
}
