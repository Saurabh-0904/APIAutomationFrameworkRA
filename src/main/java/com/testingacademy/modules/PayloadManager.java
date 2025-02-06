package com.testingacademy.modules;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.testingacademy.payloads.pojos.Booking2;
import com.testingacademy.payloads.pojos.BookingDates2;

public class PayloadManager {

    //Here we will convert JAVA OBJECT to JSON OBJECT

    public String createPayloadGson(){

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

        System.out.println(booking2);

        //Object -> Json

        Gson gson = new Gson();
        String jsonStgringBooking = gson.toJson(booking2);
        System.out.println(jsonStgringBooking);
        return jsonStgringBooking;

    }


}
