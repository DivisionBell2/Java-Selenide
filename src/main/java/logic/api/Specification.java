package logic.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public interface Specification {

    default ResponseSpecification getResponseSpecification() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build()
                ;
    }

    default RequestSpecification getRequestSpecification() {
        return new RequestSpecBuilder()
                .addFilter(new AllureRestAssured())
                .setBaseUri("linkName")
                .addHeader("Device-Type", "WEB")
                .setContentType(ContentType.JSON)
                .build()
                ;
    }
}
