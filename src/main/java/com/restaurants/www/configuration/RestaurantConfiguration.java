package com.restaurants.www.configuration;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * This class is used to represent all the Restaurant configuration Beans.
 */
@Component
@Configuration
public class RestaurantConfiguration {

    public static final String APPLICATION_NAME = "Restaurant";

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public OpenAPI customOpenAPI(@Value("${application-description}") String appDesciption, @Value("${application-version}") String appVersion) {
        return new OpenAPI()
                .info(new Info()
                        .title(APPLICATION_NAME)
                        .version(appVersion)
                        .description(appDesciption));
    }

}
