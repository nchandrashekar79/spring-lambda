package com.example.lambda;

import java.util.Map;
import java.util.function.Function;

import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Functions {

    @Bean
    public Function<APIGatewayV2HTTPEvent, APIGatewayV2HTTPResponse> hello() {
        return request -> {
            APIGatewayV2HTTPResponse response = new APIGatewayV2HTTPResponse();
            response.setStatusCode(200);
            response.setHeaders(Map.of("Content-Type", "application/json"));
            response.setBody("{\"message\":\"Hello from Spring Boot 4 on AWS Lambda\"}");
            return response;
        };
    }
}