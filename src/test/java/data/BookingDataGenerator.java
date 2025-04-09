package data;

import com.github.javafaker.Faker;
import models.request.CreateBookingRequestModel;
import models.request.UpdateBookingRequestModel;

public class BookingDataGenerator {
  private static final Faker faker = new Faker();

  public static CreateBookingRequestModel generateBookingRequest() {
    CreateBookingRequestModel request = new CreateBookingRequestModel();

    request.setFirstname(faker.name().firstName());
    request.setLastname(faker.name().lastName());
    request.setTotalprice(faker.number().numberBetween(100, 1000));
    request.setDepositpaid(faker.bool().bool());

    CreateBookingRequestModel.Bookingdates bookingdates = new CreateBookingRequestModel.Bookingdates();
    bookingdates.setCheckin("2025-03-25");
    bookingdates.setCheckout("2025-03-31");
    request.setBookingdates(bookingdates);

    request.setAdditionalneeds(faker.lorem().sentence());
    return request;
  }
  public static UpdateBookingRequestModel generateUpdateBookingRequest() {
    return UpdateBookingRequestModel.builder()
      .firstname(faker.name().firstName())
      .lastname(faker.name().lastName())
      .totalprice(faker.number().numberBetween(100, 1000))
      .depositpaid(faker.bool().bool())
      .additionalneeds(faker.lorem().sentence())
      .bookingdates(UpdateBookingRequestModel.Bookingdates.builder()
        .checkin("2025-03-25")
        .checkout("2025-03-31")
        .build())
      .build();
  }
}