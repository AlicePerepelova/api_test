package models.response;

import lombok.Data;
@Data
public class UpdateBookingResponseModel {
  private String firstname, lastname;
  private Integer totalprice;
  private Boolean depositpaid;
  private BookingDates bookingdates;
  private String additionalneeds;

  @Data
  public static class BookingDates {
    private String checkin;
    private String checkout;
  }
}
