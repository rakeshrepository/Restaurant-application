package com.restaurants.www.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * This is a General Exception which represents the exceptions in the Restaurant application.
 */
@Setter
@Getter
public class RestraurantException extends RuntimeException {
    private String message;
    private String code;
    private HttpStatus httpStatus;

    /**
     * This constructor is used to represent the cause, message and respective http status for the scenario.
     *
     * @param message
     * @param code
     * @param httpStatus
     */
    public RestraurantException(String message, String code, HttpStatus httpStatus) {
        this.message = message;
        this.code = code;
        this.httpStatus = httpStatus;
    }

}
