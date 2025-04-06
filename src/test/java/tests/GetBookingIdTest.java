package tests;

import api.BookingHelper;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import models.response.BookingIdResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static specs.Specifications.defaultRequestSpec;
import static specs.Specifications.responseSpecificationSpec200;

public class GetBookingIdTest extends TestBase {
  private Integer bookingId;

  @Test
  @Owner("@perepelovaAS")
  @Severity(SeverityLevel.CRITICAL)
  @DisplayName("Проверка бронирования по id")
  void checkGetBooking() {

    step("Создание записи о бронировании", () -> {
      this.bookingId = BookingHelper.createBooking();
    });
    BookingIdResponseModel response =
      step(String.format("Получение данных бронирования по ID=%d", bookingId), () ->
        given(defaultRequestSpec)
          .when()
          .get("/booking/" + bookingId)
          .then()
          .spec(responseSpecificationSpec200)
          .extract().as(BookingIdResponseModel.class));

    step("Проверка значения поля firstName полученного в ответе. Значение соответствует переданному в запросе" +
      " на создание бронирования", () ->
      assertThat(response.getFirstname().equals("defaultName")));
    step("Проверка значения поля lastName полученного в ответе. Значение соответствует переданному в запросе" +
      " на создание бронирования", () ->
      assertThat(response.getFirstname().equals("defaultLastName")));

    step("Проверка дат бронирования. Дата начала соответствуют значению переданному в запросе на создание " +
      " бронирования", () ->
      assertThat(response.getBookingdates().getCheckin().equals("2023-01-01")));
    step("Проверка дат бронирования. Дата окончания соответствуют значению переданному в запросе на создание " +
      " бронирования", () ->
      assertThat(response.getBookingdates().getCheckout().equals("2023-04-17")));
  }
}