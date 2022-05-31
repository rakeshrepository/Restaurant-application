package com.restaurants.www.serviceImp;

import com.restaurants.www.model.PostCodeResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MockResponseServiceTest {

    @InjectMocks
    private MockResponseService mockResponseService;

    @DisplayName("Successful mock response for API")
    @Test
    void getMockPostCodeResponseTest() {
        PostCodeResponse mockResponse = mockResponseService.getMockPostCodeResponse("ecm4");
        assertNotNull(mockResponse);

    }

    @DisplayName("General exception catch for mock API")
    @Test
    void getMockPostCodeResponseExceptionTest() {
        assertNull(mockResponseService.getMockPostCodeResponse("ecm4test"));

    }
}
