# Spring Boot 4 AWS Lambda

A minimal Spring Boot 4 application deployed as an AWS Lambda through Spring Cloud Function.

## Prerequisites

- Java 17 or newer
- Maven 3.9 or newer
- AWS SAM CLI for local Lambda invocation and deployment

## Project layout

- `Application.java` starts the Spring application.
- `Functions.java` defines the `hello` function.
- `template.yaml` describes the Lambda and HTTP API route.

## Function contract

The function accepts an API Gateway HTTP API v2 event and returns an
`APIGatewayV2HTTPResponse`. A successful request returns:

```json
{
	"message": "Hello from Spring Boot 4 on AWS Lambda"
}
```

The Lambda handler is `org.springframework.cloud.function.adapter.aws.FunctionInvoker`,
and `SPRING_CLOUD_FUNCTION_DEFINITION=hello` selects the function bean.

## Build and test

```text
mvn -B test
mvn -B package
```

The dependency-inclusive Lambda artifact is created at `target/spring-lambda.jar`.

## Run with AWS SAM

Build the Maven artifact, then validate and build the SAM application:

```text
mvn -B package
sam validate
sam build
```

Invoke the function locally with an HTTP API v2 event:

```text
sam local invoke HelloFunction -e events/http-api-v2.json
```

Deploy it to AWS using the guided deployment flow:

```text
sam deploy --guided
```

The template creates one `GET /hello` HTTP API route.