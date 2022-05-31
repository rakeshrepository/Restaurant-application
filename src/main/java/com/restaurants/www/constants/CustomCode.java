package com.restaurants.www.constants;

import lombok.Getter;

@Getter
public enum CustomCode {

    /**
     * RESTAURANT001 or RESTAURANT099 is not a standard codes, it is representation of internal success or failure code for specific scenario.
     */
    RST_001("RST_001", "Successfully able to fetch records"),
    RST_099("RST_099", "No records available");

    private String code;
    private String message;

    CustomCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
