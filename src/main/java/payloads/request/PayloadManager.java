package payloads.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testingacademy.utils.FakerUtil;
import payloads.response.BookingResponse;

public class PayloadManager extends FakerUtil {

    ObjectMapper objectMapper ;
    //Here we are converting a JAVA OBJECT to JSON OBJECT

    public String createPayloadGson() throws JsonProcessingException {

        objectMapper = new ObjectMapper();


        Booking booking2 = new Booking();

        booking2.setFirstname(FakerUtil.getUsername());
        booking2.setLastname("Bhalerao");
        booking2.setTotalprice(1000);
        booking2.setDepositpaid(true);

        BookingDates bookingDates2 = new BookingDates();
        bookingDates2.setCheckin("2024-02-01");
        bookingDates2.setCheckout("2024-10-01");
        booking2.setBookingdates(bookingDates2);
        booking2.setAdditionalneeds("Lunch");

        String payload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking2);
        //System.out.println(payload);

        //Object -> Json
        Gson gson = new Gson();
        String jsonStgringBooking = gson.toJson(booking2);
        System.out.println(jsonStgringBooking);
        return jsonStgringBooking;

    }

    public String setToken() throws JsonProcessingException {
        objectMapper = new ObjectMapper();
        Auth auth = new Auth();
        auth.setUsername("admin");
        auth.setPassword("password123");
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(auth);
    }

    public BookingResponse jsonToObject (String jsonString) throws JsonProcessingException {

        objectMapper = new ObjectMapper();
        BookingResponse bookingResponse = objectMapper.readValue(jsonString, BookingResponse.class);
        return bookingResponse;
    }

    public Booking jsonToObjectPut (String jsonString) throws JsonProcessingException {

        objectMapper = new ObjectMapper();
        Booking booking = objectMapper.readValue(jsonString, Booking.class);
        return booking;
    }

    public String updatePayload () throws JsonProcessingException {

        objectMapper = new ObjectMapper();
        Booking booking = new Booking();
        booking.setFirstname("Saurabh");
        booking.setLastname("Bhalerao");
        booking.setTotalprice(500);
        booking.setDepositpaid(true);
        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin("2024-02-01");
        bookingDates.setCheckout("2024-02-02");
        booking.setBookingdates(bookingDates);
        booking.setAdditionalneeds("Brunch");

        String payload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking);
        return payload;

    }




}
