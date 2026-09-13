package com.example.lambda;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.Function;

import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FunctionsTest {

    @Qualifier("hello")
    @org.springframework.beans.factory.annotation.Autowired
    private Function<APIGatewayV2HTTPEvent, APIGatewayV2HTTPResponse> hello;

    @Test
    void returnsJsonResponseForHttpApiEvent() {
        APIGatewayV2HTTPEvent request = new APIGatewayV2HTTPEvent();
        request.setRouteKey("GET /");

        APIGatewayV2HTTPResponse response = hello.apply(request);

        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(response.getHeaders()).containsEntry("Content-Type", "application/json");
        assertThat(response.getBody()).isEqualTo("{\"message\":\"Hello from Spring Boot 4 on AWS Lambda\"}");
    }
}