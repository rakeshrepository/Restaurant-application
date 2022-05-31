package com.restaurants.www.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestaurantModel {
    @JsonProperty("Name")
    private String name;
    @JsonProperty("RatingAverage")
    private float ratingAverage;
    @JsonProperty("CuisineTypes")
    private List<Cuisine> cuisineTypes;
}
