package com.restaurants.www.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class RestaurantResponseDTO {
    private List<RestaurantDTO> restaurant;
}

