package com.restaurants.www.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class is used to represent the standard response from the Restaurant application.
 * It is General response object for all the CRUD APIs in these services.
 * It is used to maintain the uniformity return response to UI or client application.
 */
@Getter
@Setter
@NoArgsConstructor
public class GeneralResponse {

    @JsonProperty("Message")
    private String message;
    @JsonProperty("Code")
    private String code;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("Data")
    private Object data;

    /**
     * This constructor is used to represent the successful response object for client or UI application.
     *
     * @param mes
     * @param code
     * @param response
     */
    public GeneralResponse(String mes, String code, Object response) {
        this.message = mes;
        this.code = code;
        this.data = response;
    }

}
