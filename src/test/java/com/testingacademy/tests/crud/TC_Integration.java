package com.testingacademy.tests.crud;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.testingacademy.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

public class TC_Integration extends BaseTest {

    //Get a Token (POST)
    //Create a booking (POST)
    //Update the booking with Token and Booking ID (UPDATE) - How to pass the variables from 1 test to another.
        //1. Auth - API Key
        //2. Cookie Based Auth Side.
        //3. OAuth 2.0 - Method How you can use the OAuth 2.0
    //Delete (DELETE)



    String token ;


    //Create a booking
    @Test (groups = "P0")
    public void testCreateBooking() throws JsonProcessingException {
        token = getToken();
        System.out.println(token);


    }



    //Update the booking with Token and Booking ID
    @Test (groups = "P0", dependsOnMethods = {"testCreateBooking"})
    public void testCreateAndUpdateBooking(){
        assertThat("Saurabh").isEqualTo("Saurabh");


    }

    //Delete Booking
    @Test (groups = "P0", dependsOnMethods = {"testCreateAndUpdateBooking"})
    public void testDeleteCreateBooking(){
        assertThat("Saurabh").isEqualTo("Saurabh");


    }



}
