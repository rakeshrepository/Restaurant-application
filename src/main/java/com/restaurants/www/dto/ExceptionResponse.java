package com.restaurants.www.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * This is a special class which is used to represent the user defined exception to UI.
 */
@Getter
@Setter
public class ExceptionResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("Code")
    private String code;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("Message")
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("StackTrace")
    private String stackTrace;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("ExceptionType")
    private String exceptionCause;

    /**
     * This constructor can be used for representing only error code and error message to UI.
     *
     * @param errorCode
     * @param errorMessage
     */
    public ExceptionResponse(String errorCode, String errorMessage) {
        this.code = errorCode;
        this.message = errorMessage;
    }

    /**
     * This constructor can be used to represent the details of exception.
     *
     * @param message
     * @param stackTrace
     * @param exceptionCause
     */
    public ExceptionResponse(String message, String stackTrace, String exceptionCause) {
        this.message = message;
        this.stackTrace = stackTrace;
        this.exceptionCause = exceptionCause;
    }
}
