package com.restaurants.www.controller;

import com.restaurants.www.dto.GeneralResponse;
import com.restaurants.www.service.SearchInterface;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Responsible for handling CURD APIs related to Restaurant exposed to client.
 */
@RestController
@AllArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantController {

    private SearchInterface searchInterface;

    @GetMapping("/bypostcode/{postcode}")
    public ResponseEntity<GeneralResponse> getRestaurants(@PathVariable String postcode) {
        return ResponseEntity.ok(searchInterface.searchByPostCode(postcode));
    }
}
