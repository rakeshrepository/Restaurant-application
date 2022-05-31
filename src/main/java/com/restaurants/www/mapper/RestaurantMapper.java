package com.restaurants.www.mapper;

import com.restaurants.www.dto.RestaurantDTO;
import com.restaurants.www.model.Cuisine;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to implement the specific mapper methods for the Restaurant application.
 * It consists of only mapper methods to map the model object into DTO object which can be
 * shared to UI interface.
 * It can also have the mappers for converting DTO object into model object before storing into db.
 */
@Service
public class RestaurantMapper {

    /**
     * This method is used to generate RestaurantDTO from the response object.
     *
     * @param name
     * @param ratingAverage
     * @param cuisineTypes
     * @return RestaurantDTO based on the response object.
     */
    public RestaurantDTO responseMapper(String name, float ratingAverage, List<Cuisine> cuisineTypes) {
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        restaurantDTO.setName(name);
        restaurantDTO.setRating(ratingAverage);
        List<String> cousinList = mapperCuisineDetails(cuisineTypes);
        restaurantDTO.setCuisines(cousinList);
        return restaurantDTO;
    }

    /**
     * This method is used for map the cuisine details into DTO object
     *
     * @param cuisineTypes
     * @return List of cuisine details.
     */
    private List<String> mapperCuisineDetails(List<Cuisine> cuisineTypes) {

        List<String> cuisineDTOList = new ArrayList<>();
        cuisineTypes.forEach(cuisine -> {
            cuisineDTOList.add(cuisine.getName());
        });

        return cuisineDTOList;
    }

}
