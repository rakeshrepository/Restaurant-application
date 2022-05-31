package com.restaurants.www.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * This class represents the RestaurantDTO class for the UI or Client.
 */
@Setter
@Getter
@ToString
public class RestaurantDTO {
    private String name;
    private float rating;
    private List<String> cuisines;
}
