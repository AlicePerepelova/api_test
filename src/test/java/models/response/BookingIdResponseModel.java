package models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class BookingIdResponseModel {
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
