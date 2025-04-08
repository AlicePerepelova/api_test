package tests;

import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import models.request.CreateBookingRequestModel;
import models.response.CreateBookingResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static data.BookingDataGenerator.generateBookingRequest;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.Specifications.defaultRequestSpec;
import static specs.Specifications.responseSpecificationSpec200;


public class CreateBookingTest extends TestBase{
  @Test
  @Owner("@perepelovaAS")
  @Severity(SeverityLevel.CRITICAL)
  @DisplayName("Проверка создания новой записи о бронировании")
  void checkCreateBooking() {
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

    step("Проверка корректности статуса депозита", () ->
      assertEquals(request.getDepositpaid(), response.getBooking().getDepositpaid(), "Статус депозита не совпадает")
    );

    step("Проверка корректности даты заезда", () ->
      assertEquals(request.getBookingdates().getCheckin(), response.getBooking().getBookingdates().getCheckin(), "Дата заезда не совпадает")
    );

    step("Проверка корректности даты выезда", () ->
      assertEquals(request.getBookingdates().getCheckout(), response.getBooking().getBookingdates().getCheckout(), "Дата выезда не совпадает")
    );

    step("Проверка корректности дополнительных пожеланий", () ->
      assertEquals(request.getAdditionalneeds(), response.getBooking().getAdditionalneeds(), "Дополнительные пожелания не совпадают")
    );
  }
}