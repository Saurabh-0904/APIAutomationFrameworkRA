package com.testingacademy.tests.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.testingacademy.base.BaseTest;
import com.testingacademy.endpoints.APIConstants;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class TCCerateBooking extends BaseTest {

    //Step 1 - POST
    //URL
    //Header
    //Body

    //Step 2
    //Prepare the payload (Object -> JSON String)
    //Send the request

    //Step 3
    //Validate Response
    //First name
    //Status code
    //Response time


    @Owner("Saurabh")
    @Description("Verify that the CREATE Booking with the valid Payload, Status code 200")
    @Test
    public void testPositivePOSTReq() throws JsonProcessingException {

        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given().spec(requestSpecification)
                .when().body(payloadManager.createPayloadGson()).post();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);




    }
}
