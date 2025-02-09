package com.testingacademy.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.testingacademy.actions.AssertActions;
import com.testingacademy.endpoints.APIConstants;
import com.testingacademy.modules.PayloadManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BaseTest {

    //This class contains common functions

    public RequestSpecification requestSpecification;
    public AssertActions assertActions;
    public PayloadManager payloadManager;
    public JsonPath jsonPath;
    public Response response;
    public ValidatableResponse validatableResponse;

    @BeforeMethod (alwaysRun = true)
    public void setConfig(){
        payloadManager = new PayloadManager();
        assertActions = new AssertActions();
        requestSpecification = RestAssured.given()
                .baseUri(APIConstants.BASE_URL)
                .contentType(ContentType.JSON);


        /*requestSpecification = new RequestSpecBuilder()
                .setBaseUri(APIConstants.BASE_URL)
                .addHeader("Content-Type", "application/json")
                .build().log().all();*/


    }

    //Get a Token (Token is common for all, so it will be here in BaseTest)
    public String getToken() throws JsonProcessingException {

        requestSpecification = RestAssured.given().baseUri(APIConstants.BASE_URL).basePath("/auth");
        String payload = payloadManager.setToken();

        response = requestSpecification.contentType(ContentType.JSON)
                .body(payload).when().post();
        jsonPath = new JsonPath(response.asString());
        return jsonPath.getString("token");

    }
}
