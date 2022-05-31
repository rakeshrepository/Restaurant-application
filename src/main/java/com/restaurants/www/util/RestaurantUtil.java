package com.restaurants.www.util;

import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * This class is a Restaurant utility class for implementing the specific utility methods
 * for the Restaurant purpose.
 */

@Component
public class RestaurantUtil {

    /**
     * This method is used to build the url with the dynamic path param.
     *
     * @param restaurantByPostCodeAPIUrl
     * @param pathParam
     * @return Dynamic URL string.
     */
    public String generateAPIURLWithPathVariable(String restaurantByPostCodeAPIUrl, String pathParam) {
        return UriComponentsBuilder.fromHttpUrl(restaurantByPostCodeAPIUrl).build(pathParam).toString();
    }
}
