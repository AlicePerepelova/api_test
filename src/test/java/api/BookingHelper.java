package api;

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
    step("Проверка корректности фамилии гостя", () ->
      assertEquals(request.getLastname(), response.getBooking().getLastname(), "Фамилия гостя не совпадает")
    );

    step("Проверка корректности общей цены", () ->
      assertEquals(request.getTotalprice(), response.getBooking().getTotalprice(), "Общая цена не совпадает")
    );

    step("Проверка корректности статуса предоплаты", () ->
      assertEquals(request.getDepositpaid(), response.getBooking().getDepositpaid(), "Статус предоплаты не совпадает")
    );

    step("Проверка корректности дат бронирования", () -> {
      assertEquals(request.getBookingdates().getCheckin(), response.getBooking().getBookingdates().getCheckin(), "Дата заезда не совпадает");
      assertEquals(request.getBookingdates().getCheckout(), response.getBooking().getBookingdates().getCheckout(), "Дата выезда не совпадает");
    });

    step("Проверка корректности дополнительных требований", () ->
      assertEquals(request.getAdditionalneeds(), response.getBooking().getAdditionalneeds(), "Дополнительные требования не совпадают")
    );

    return response.getBookingid();
  }
}