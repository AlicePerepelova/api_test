package tests;

import api.BookingHelper;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import models.response.BookingIdResponseModel;
import models.response.CreateBookingResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static specs.Specifications.defaultRequestSpec;
import static specs.Specifications.responseSpecificationSpec200;

public class GetBookingIdTest extends TestBase {

  @Test
  @Owner("@perepelovaAS")
  @Severity(SeverityLevel.CRITICAL)
  @DisplayName("Проверка бронирования по id")
  void checkGetBooking() {

    CreateBookingResponseModel booking =
      step("Создание записи о бронировании", BookingHelper::createBooking);
    BookingIdResponseModel response =
      step(String.format("Получение данных бронирования по ID=%d", booking.getBookingid()), () ->
        given(defaultRequestSpec)
          .when()
          .get("/booking/" + booking.getBookingid())
          .then()
          .spec(responseSpecificationSpec200)
          .extract().as(BookingIdResponseModel.class));

    step("Проверка значения поля firstName полученного в ответе. Значение соответствует переданному в запросе" +
      " на создание бронирования", () ->
      assertThat(response.getFirstname().equals(booking.getBooking().getFirstname())));
    step("Проверка значения поля lastName полученного в ответе. Значение соответствует переданному в запросе" +
      " на создание бронирования", () ->
      assertThat(response.getLastname()).isEqualTo(booking.getBooking().getLastname()));

    step("Проверка общей цены бронирования. Значение соответствует переданному в запросе", () ->
      assertThat(response.getTotalprice()).isEqualTo(booking.getBooking().getTotalprice()));

    step("Проверка статуса депозита. Значение соответствует переданному в запросе", () ->
      assertThat(response.getDepositpaid()).isEqualTo(booking.getBooking().getDepositpaid()));

    step("Проверка дополнительных пожеланий. Значение соответствует переданному в запросе", () ->
      assertThat(response.getAdditionalneeds()).isEqualTo(booking.getBooking().getAdditionalneeds()));

    step("Проверка дат бронирования. Дата начала соответствуют значению переданному в запросе на создание " +
      " бронирования", () ->
      assertThat(response.getBookingdates().getCheckin()).isEqualTo("2025-03-25"));

    step("Проверка дат бронирования. Дата окончания соответствуют значению переданному в запросе на создание " +
      " бронирования", () ->
      assertThat(response.getBookingdates().getCheckout()).isEqualTo("2025-03-31"));
 }
}
