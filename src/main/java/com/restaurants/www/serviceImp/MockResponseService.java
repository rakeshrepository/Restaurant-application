package com.restaurants.www.serviceImp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurants.www.model.PostCodeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.InputStream;

/**
 * This service class is used for generating and creating mock responses to avoid the
 * external API calls.
 */

@Component
@Slf4j
public class MockResponseService {

    public static final String JSON_DEFAULT_PATH = "/mockJson/bypostcode-";
    public static final String JSON_FILE_TYPE = ".json";

    /**
     * This method is used to get the post code response from the given file name.
     *
     * @param fileName
     * @return PostCodeResponse object.
     */
    public PostCodeResponse getMockPostCodeResponse(String fileName) {
        PostCodeResponse postCodeResponse = null;
        ObjectMapper objectMapper = new ObjectMapper();

        StringBuilder builder = new StringBuilder(JSON_DEFAULT_PATH);
        builder.append(fileName).append(JSON_FILE_TYPE);

        InputStream inputStream = TypeReference.class.getResourceAsStream(builder.toString());
        try {
            postCodeResponse = objectMapper.readValue(inputStream, PostCodeResponse.class);
        } catch (Exception e) {
            log.error("Exception {}", e.getMessage());
        }
        return postCodeResponse;
    }

}
