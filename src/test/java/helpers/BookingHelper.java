package helpers;

import models.request.CreateBookingRequestModel;
import models.response.CreateBookingResponseModel;
import tests.TestBase;

import static data.BookingDataGenerator.generateBookingRequest;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.Specifications.defaultRequestSpec;
import static specs.Specifications.responseSpecificationSpec200;

public class BookingHelper extends TestBase {

  public static Integer createBooking() {
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

    step("Проверка корректности имени гостя", () ->
      assertEquals(request.getFirstname(), response.getBooking().getFirstname(), "Имя гостя не совпадает")
    );


    return response.getBookingid();
  }
}