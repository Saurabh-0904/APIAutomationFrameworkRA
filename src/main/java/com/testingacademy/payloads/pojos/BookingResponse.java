package com.testingacademy.payloads.pojos;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class BookingResponse {

    @SerializedName("bookingid")
    @Expose
    private Integer bookingid;
    @SerializedName("booking")
    @Expose
    private Booking2 booking2;

    public Integer getBookingid() {
        return bookingid;
    }

    public void setBookingid(Integer bookingid) {
        this.bookingid = bookingid;
    }

    public Booking2 getBooking() {
        return booking2;
    }

    public void setBooking(Booking2 booking2) {
        this.booking2 = booking2;
    }
}
