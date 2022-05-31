package com.restaurants.www.serviceImp;

import com.restaurants.www.model.PostCodeResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * This class is used to implement the custom rest template for calling the external APIs.
 * It is a generalized class which represents the different HTTP method implementations.
 */

@Service
@AllArgsConstructor
@Slf4j
public class CustomRestTemplate {

    private static final String AUTHORIZATION_KEY = "Authorization";

    private RestTemplate restTemplate;
    private MockResponseService mockResponseService;

    /**
     * <p>This method is used to call the URL with HTTP method GET.
     * It is used to communicate the external services having the GET REST method.</p>
     *
     * @param url
     * @param classType
     * @param accessToken
     * @return ResponseEntity with the class type.
     */
    public ResponseEntity getEntity(String url, Class<?> classType, String accessToken) {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(AUTHORIZATION_KEY, accessToken);
        ResponseEntity<?> response = null;
        try {
            HttpEntity<?> requestEntity = new HttpEntity<>(headers);
            /** This below code must uncomment for calling external API. */
            //  response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, classType);
        } catch (RestClientException exception) {
            log.error("Exception while calling url {}", exception.getMessage());
            throw exception;
        }


        /** Below few lines of code should be removed once integrate with actual public service API.
         This reads the response from the mock API response. */
        String[] urlArr = url.split("/");
        String postcode = urlArr[urlArr.length - 1];
        PostCodeResponse mockResponse = mockResponseService.getMockPostCodeResponse(postcode);

        HttpStatus httpStatus = HttpStatus.OK;
        if (ObjectUtils.isEmpty(mockResponse)) {
            httpStatus = HttpStatus.NOT_FOUND;
        }

        /** pass response object instead of mockResponse */
        return new ResponseEntity<>(mockResponse, httpStatus);
    }

}
