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
public class PostCodeResponse {
    @JsonProperty("Area")
    private String area;
    @JsonProperty("Restaurants")
    private List<RestaurantModel> restaurantDTOS;
    @JsonProperty("ShortResultText")
    private String ShortResultText;
}

