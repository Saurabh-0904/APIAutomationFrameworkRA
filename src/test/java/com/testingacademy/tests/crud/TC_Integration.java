package com.testingacademy.tests.crud;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.testingacademy.base.BaseTest;
import com.testingacademy.endpoints.APIConstants;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;
import payloads.request.Booking;
import payloads.request.PayloadManager;
import payloads.response.BookingResponse;

import static org.assertj.core.api.Assertions.*;

public class TC_Integration extends BaseTest {

    //Note: Commented printing statements coz it is not good to keep
    //not deleted, kept for knowledge purpose in actual we need to delete it
//----------------------------------------------------------------------------------------------------------------------

    //Get a Token (POST)
    //Create a booking (POST)
    //Update the booking with Token and Booking ID (UPDATE) - How to pass the variables from 1 test to another.
        //1. Auth - API Key
        //2. Cookie Based Auth Side.
        //3. OAuth 2.0 - Method How you can use the OAuth 2.0
    //Delete (DELETE)



    String token ;
    String bookingID ;
    String bookingIDPojo ;


    //Create a booking
    @Test (groups = "P0")
    public void testCreateBooking() throws JsonProcessingException {
        token = getToken();
        //System.out.println(token);

        //Checking token if token is not null and not empty
        assertThat(token).isNotNull().isNotEmpty();

        //given
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        //when
        response = RestAssured.given().spec(requestSpecification)
                .when().body(payloadManager.createPayloadGson()).post();
        //then
        validatableResponse = response.then().log().all();
        //Extracted Response
        //(JsonPath used coz if we don't want to create a response class)
        //In a response body if we have only a BookingID then no need to create a response class,
        //but if we have other data along with BookingID, then we have to create a Response class (Available in - payload->response->BookingREsponse)
        jsonPath = JsonPath.from(response.asString());
        validatableResponse.statusCode(200);

        //Direct extraction from JSON path
        bookingID = jsonPath.getString("bookingid");
        //System.out.println("JSON Booking ID : " + bookingID);

        //Booking response extraction from BookingResponse class
        //GOOD PRACTICE IS TO CREATE A RESPONSE CLASS TO EXTRACT A RESPONSE
        BookingResponse bookingResponse = payloadManager.jsonToObject(response.asString());    //Check jsonToObject method in payloadmanager class ehich sir created
        bookingIDPojo = bookingResponse.getBookingid().toString();
        //System.out.println("Booking ID POJO :" + bookingIDPojo);

        assertThat(bookingID).isNotNull().isNotEmpty();

    }

    //Update the booking with Token and Booking ID
    @Test (groups = "P0", dependsOnMethods = {"testCreateBooking"})
    public void testCreateAndUpdateBooking() throws JsonProcessingException {

        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingID);
        response = RestAssured.given().spec(requestSpecification).cookie("token", token)
                .when().body(payloadManager.updatePayload()).put();
        validatableResponse = response.then().log().all();

        Booking bookingresponse = payloadManager.jsonToObjectPut(response.asString());
        assertThat(bookingresponse.getFirstname()).isEqualTo("Saurabh").isNotNull();
        assertThat(bookingresponse.getTotalprice()).isEqualTo(500).isNotNull();
        assertThat(bookingresponse.getBookingdates().getCheckout()).isEqualTo("2024-02-02").isNotNull();

    }

    //Delete Booking
    @Test (groups = "P0", dependsOnMethods = {"testCreateAndUpdateBooking"})
    public void testDeleteCreateBooking(){

        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingID).cookie("token", token);
        ValidatableResponse validatableResponse = RestAssured.given().spec(requestSpecification).auth().basic("admin", "password123").when().delete().then().log().all();
        validatableResponse.statusCode(201);

    }

    //Delete Booking
    @Test (groups = "P0", dependsOnMethods = {"testDeleteCreateBooking"})
    public void testDeleteBookingByGet(){
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingID);
        response = requestSpecification.given().spec(requestSpecification).when().get();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(404);

    }



}
