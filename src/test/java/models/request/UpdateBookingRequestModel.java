package models.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UpdateBookingRequestModel {
  private String firstname;
  private String lastname;
  private Integer totalprice;
  private Boolean depositpaid;
  private Bookingdates bookingdates;
  private String additionalneeds;

  @Builder
  @Data
  public static class Bookingdates {
    private String checkin;
    private String checkout;
  }

}