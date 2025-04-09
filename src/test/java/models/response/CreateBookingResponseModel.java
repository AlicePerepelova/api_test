package models.response;

import lombok.Data;

@Data
public class CreateBookingResponseModel {
  private Integer bookingid;
  private Booking booking;

  @Data
  public static class Booking {
    private String firstname, lastname;
    private Integer totalprice;
    private Boolean depositpaid;
    private String additionalneeds;
    private Bookingdates bookingdates;

    @Data
    public static class Bookingdates {
      private String checkin;
      private String checkout;
    }
  }
}
