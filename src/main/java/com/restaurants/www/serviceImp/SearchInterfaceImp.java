package com.restaurants.www.serviceImp;

import com.restaurants.www.configuration.AppPropertiesConfig;
import com.restaurants.www.constants.CustomCode;
import com.restaurants.www.dto.GeneralResponse;
import com.restaurants.www.dto.RestaurantDTO;
import com.restaurants.www.dto.RestaurantResponseDTO;
import com.restaurants.www.exception.RestraurantException;
import com.restaurants.www.mapper.RestaurantMapper;
import com.restaurants.www.model.PostCodeResponse;
import com.restaurants.www.service.SearchInterface;
import com.restaurants.www.util.RestaurantUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * SearchInterfaceImp holds all the methods related to search functionality.
 * All the search related methods must implement here.
 */

@Service
@Slf4j
@AllArgsConstructor
public class SearchInterfaceImp implements SearchInterface {

    private CustomRestTemplate customRestTemplate;
    private RestaurantMapper restaurantMapper;
    private AppPropertiesConfig appPropertiesConfig;
    private RestaurantUtil restaurantUtil;

    /**
     * Returns the General Response object to controller based on the input postcode.
     * <p>
     * This method always the return the General Response object with restaurant details.
     * It throws Restraurant exception if the response is empty or null with the expected
     * error code with message to client.
     * </p>
     *
     * @param postCode
     * @return General Response consists for restaurant details.
     */
    @Override
    public GeneralResponse searchByPostCode(String postCode) {
        log.debug("SearchInterfaceImp searchByPostCode() method START");

        String apiUrl = restaurantUtil.generateAPIURLWithPathVariable(appPropertiesConfig.getRestaurantByPostCodeAPIUrl(), postCode);

        ResponseEntity<?> response = customRestTemplate.getEntity(apiUrl, PostCodeResponse.class, "accessToken");

        Optional<PostCodeResponse> responseObj = validateRestaurantResponse(response);

        List<RestaurantDTO> restaurantDTOList = null;
        if (responseObj.isPresent()) {
            restaurantDTOList = responseObj.get().getRestaurantDTOS()
                    .parallelStream()
                    .filter(restaurantModel -> StringUtils.isNotEmpty(restaurantModel.getName()) && ObjectUtils.isNotEmpty(restaurantModel.getCuisineTypes()))
                    .map(restaurant -> restaurantMapper.responseMapper(restaurant.getName(), restaurant.getRatingAverage(), restaurant.getCuisineTypes()))
                    .collect(Collectors.toList());
        } else {
            log.error("Error code = {} | message = {}", CustomCode.RST_099.getCode(), CustomCode.RST_099.getMessage());
            throw new RestraurantException(CustomCode.RST_099.getMessage(), CustomCode.RST_099.getCode(), HttpStatus.NOT_FOUND);
        }

        if (CollectionUtils.isEmpty(restaurantDTOList)) {
            log.error("Error code = {} | message = {}", CustomCode.RST_099.getCode(), CustomCode.RST_099.getMessage());
            throw new RestraurantException(CustomCode.RST_099.getMessage(), CustomCode.RST_099.getCode(), HttpStatus.NOT_FOUND);
        }

        log.debug("SearchInterfaceImp searchByPostCode() method END");
        return new GeneralResponse(CustomCode.RST_001.getMessage(), CustomCode.RST_001.getCode(), new RestaurantResponseDTO(restaurantDTOList));
    }

    /**
     * This method is used to validate the restaurant response received from the external API.
     *
     * @param response
     * @return
     */
    private Optional<PostCodeResponse> validateRestaurantResponse(ResponseEntity<?> response) {
        log.debug("SearchInterfaceImp validateRestaurantResponse() START");
        Optional<PostCodeResponse> restaurantResponse = Optional.empty();
        if (ObjectUtils.isNotEmpty(response) && ObjectUtils.isNotEmpty(response.getBody())) {
            restaurantResponse = Optional.ofNullable((PostCodeResponse) response.getBody());
        }
        log.debug("SearchInterfaceImp validateRestaurantResponse() END");
        return restaurantResponse;
    }


}
