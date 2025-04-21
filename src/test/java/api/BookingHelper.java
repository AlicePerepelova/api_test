package api;

import models.request.CreateBookingRequestModel;
import models.response.CreateBookingResponseModel;

import static data.BookingDataGenerator.generateBookingRequest;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.Specifications.defaultRequestSpec;
import static specs.Specifications.responseSpecificationSpec200;

public class BookingHelper {

  public static CreateBookingResponseModel createBooking() {
    CreateBookingRequestModel request = generateBookingRequest();
    CreateBookingResponseModel response =
      step("Создание нового бронирования", () ->
        given(defaultRequestSpec)
          .header("Accept", "application/json")
          .body(request)
          .when()
          .post("/booking")
          .then()
          .spec(responseSpecificationSpec200)
          .extract().as(CreateBookingResponseModel.class));
    return response;
  }
}