package com.restaurants.www.controller;

import com.restaurants.www.constants.CustomCode;
import com.restaurants.www.dto.GeneralResponse;
import com.restaurants.www.service.SearchInterface;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RestaurantControllerTest {

    @InjectMocks
    private RestaurantController restaurantController;
    @Mock
    private SearchInterface searchInterface;

    @DisplayName("Get Restaurants details by postcode success response")
    @Test
    void getRestaurantsTest() {
        when(searchInterface.searchByPostCode(anyString())).thenReturn(generalResponse());
        ResponseEntity<GeneralResponse> response = restaurantController.getRestaurants("ecm4");
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    public GeneralResponse generalResponse() {
        List<String> listRestaurant = new ArrayList<>();
        listRestaurant.add("Restaurant");
        return new GeneralResponse(CustomCode.RST_001.getCode(), CustomCode.RST_001.getMessage(), listRestaurant);
    }
}
