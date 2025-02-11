package payloads.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import payloads.request.Booking;


public class BookingResponse {

    @SerializedName("bookingid")
    @Expose
    private Integer bookingid;
    @SerializedName("booking")
    @Expose
    private Booking booking2;

    public Integer getBookingid() {
        return bookingid;
    }

    public void setBookingid(Integer bookingid) {
        this.bookingid = bookingid;
    }

    public Booking getBooking() {
        return booking2;
    }

    public void setBooking(Booking booking2) {
        this.booking2 = booking2;
    }
}
