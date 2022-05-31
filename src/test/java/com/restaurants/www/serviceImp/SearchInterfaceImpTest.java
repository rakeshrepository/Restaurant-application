package com.restaurants.www.serviceImp;

import com.restaurants.www.configuration.AppPropertiesConfig;
import com.restaurants.www.dto.GeneralResponse;
import com.restaurants.www.dto.RestaurantDTO;
import com.restaurants.www.dto.RestaurantResponseDTO;
import com.restaurants.www.exception.RestraurantException;
import com.restaurants.www.mapper.RestaurantMapper;
import com.restaurants.www.model.PostCodeResponse;
import com.restaurants.www.util.RestaurantUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SearchInterfaceImpTest {

    @InjectMocks
    private SearchInterfaceImp searchInterfaceImp;
    @Mock
    private CustomRestTemplate customRestTemplate;
    @Mock
    private RestaurantMapper restaurantMapper;
    @Mock
    private AppPropertiesConfig appPropertiesConfig;
    @Mock
    private RestaurantUtil restaurantUtil;

    private CustomRestTemplate mockResponse;

    @DisplayName("Search by post code success response")
    @Test
    void searchByPostCodeTest() {
        RestTemplate restTemplate = new RestTemplate();
        MockResponseService responseService = new MockResponseService();
        mockResponse = new CustomRestTemplate(restTemplate, responseService);
        String urlPattern = "https://host-name/restaurants/bypostcode/{postcode}";
        when(appPropertiesConfig.getRestaurantByPostCodeAPIUrl()).thenReturn(urlPattern);
        String url = "https://host-name/restaurants/bypostcode/ecm4";
        when(restaurantUtil.generateAPIURLWithPathVariable(anyString(), anyString())).thenReturn(url);

        MockResponseService mockResponseService = new MockResponseService();
        PostCodeResponse mockResponse = mockResponseService.getMockPostCodeResponse("ecm4");
        ResponseEntity<PostCodeResponse> res = new ResponseEntity<>(mockResponse, HttpStatus.OK);

        when(customRestTemplate.getEntity(anyString(), any(), anyString())).thenReturn(res);
        when(restaurantMapper.responseMapper(anyString(), anyFloat(), any())).thenReturn(getRestaurantDTO());
        GeneralResponse response = searchInterfaceImp.searchByPostCode("ecm4");
        assertNotNull(response);
    }

    @DisplayName("Search by post code Restaurant Exception for empty object")
    @Test
    void searchByPostCodeRestaurantExceptionTest() {
        RestTemplate restTemplate = new RestTemplate();
        MockResponseService responseService = new MockResponseService();
        mockResponse = new CustomRestTemplate(restTemplate, responseService);
        String urlPattern = "https://host-name/restaurants/bypostcode/{postcode}";
        when(appPropertiesConfig.getRestaurantByPostCodeAPIUrl()).thenReturn(urlPattern);
        String url = "https://host-name/restaurants/bypostcode/ecm4";
        when(restaurantUtil.generateAPIURLWithPathVariable(anyString(), anyString())).thenReturn(url);

        String urlStr = new StringBuilder(url).append("test").toString();
        ResponseEntity<?> res = getRestaurantResponse(urlStr);
        when(customRestTemplate.getEntity(anyString(), any(), anyString())).thenReturn(res);
        assertThrows(RestraurantException.class, () -> searchInterfaceImp.searchByPostCode("ecm4"));
    }

    public ResponseEntity<?> getRestaurantResponse(String url) {
        return mockResponse.getEntity(url, RestaurantResponseDTO.class, "access");
    }

    public ResponseEntity<?> getRestaurantResponseSuccess(String url) {
        return mockResponse.getEntity(url, RestaurantResponseDTO.class, "access");
    }

    public RestaurantDTO getRestaurantDTO() {
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        restaurantDTO.setRating(4.8f);
        restaurantDTO.setName("Restaurant 1");
        List<String> cuisinList = new ArrayList<>();
        cuisinList.add("BreakFast");
        cuisinList.add("Brench");
        restaurantDTO.setCuisines(cuisinList);
        return restaurantDTO;
    }


}
