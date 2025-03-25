package models.request;

import lombok.Data;

@Data
public class CreateBookingRequestModel {
  private String firstname, lastname;
  private Integer totalprice;
  private Boolean depositpaid;
  private Bookingdates bookingdates;
  private String additionalneeds;

  @Data
  public static class Bookingdates {
    private String checkin;
    private String checkout;
  }
}
