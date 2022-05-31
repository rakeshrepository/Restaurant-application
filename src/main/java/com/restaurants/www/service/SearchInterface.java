package com.restaurants.www.service;

import com.restaurants.www.dto.GeneralResponse;

/**
 * This interface can be used for the different search operation.
 */
public interface SearchInterface {
    GeneralResponse searchByPostCode(String postCode);
}
