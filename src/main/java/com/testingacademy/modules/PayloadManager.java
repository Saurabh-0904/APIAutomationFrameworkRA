package com.testingacademy.modules;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.testingacademy.payloads.pojos.Booking2;
import com.testingacademy.payloads.pojos.BookingDates2;
import payloads.request.Auth;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PayloadManager {

    //Here we are converting a JAVA OBJECT to JSON OBJECT

    public String createPayloadGson() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        Faker faker = new Faker();
        String expectFirstName = faker.name().firstName();
        Booking2 booking2 = new Booking2();

        booking2.setFirstname(expectFirstName);
        booking2.setLastname("Bhalerao");
        booking2.setTotalprice(1000);
        booking2.setDepositpaid(true);

        BookingDates2 bookingDates2 = new BookingDates2();
        bookingDates2.setCheckin("2024-02-01");
        bookingDates2.setCheckout("2024-10-01");
        booking2.setBookingdates(bookingDates2);
        booking2.setAdditionalneeds("Lunch");

        String payload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking2);


        //Object -> Json

        Gson gson = new Gson();
        String jsonStgringBooking = gson.toJson(booking2);
        System.out.println(jsonStgringBooking);
        return jsonStgringBooking;

    }

    public String setToken() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Auth auth = new Auth();
        auth.setUserName("admin");
        auth.setPassword("password123");
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(auth);
    }


}
