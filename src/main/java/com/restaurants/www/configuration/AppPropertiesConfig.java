package com.restaurants.www.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * This class represents the application properties configurations for Restaurant Application.
 */
@Configuration
public class AppPropertiesConfig {

    @Value("${restaurant-postcode}")
    private String restaurantByPostCodeAPIUrl;

    /**
     * This method is used to read the restaurant-postcode.
     *
     * @return restaurant-postcode URL
     */
    public String getRestaurantByPostCodeAPIUrl() {
        return restaurantByPostCodeAPIUrl;
    }
}
