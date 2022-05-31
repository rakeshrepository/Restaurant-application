package com.restaurants.www.serviceImp;

import com.restaurants.www.model.PostCodeResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
public class CustomRestTemplateTest {

    @InjectMocks
    private CustomRestTemplate customRestTemplate;
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private MockResponseService mockResponseService;

    @DisplayName("Get entity call for success response")
    @Test
    void getEntityTest() {
        PostCodeResponse mockResponse = new PostCodeResponse();
        Mockito.when(mockResponseService.getMockPostCodeResponse(Mockito.anyString())).thenReturn(mockResponse);
        String url = "https://host-name/restaurants/bypostcode/ecm4";
        Assertions.assertNotNull(customRestTemplate.getEntity(url, PostCodeResponse.class, "accesstoken"));
    }
}
