package tests;

import helpers.BookingHelper;
import models.response.BookingIdResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static specs.Specifications.defaultRequestSpec;
import static specs.Specifications.responseSpecificationSpec200;

public class GetBookingIdTest extends TestBase{
  private Integer bookingId;
  @Test
  @DisplayName("Проверка бронирования по id={bookingId}")
  void checkGetBooking() {

    step("Создание записи о бронировании", ()-> {
      BookingHelper.createBooking();
      this.bookingId = BookingHelper.createBooking();
    });
    BookingIdResponseModel response =
      step("Получение данных бронирования по ID= {bookingId}", () ->
        given(defaultRequestSpec)
          .when()
          .get("/booking/"+bookingId)
          .then()
          .spec(responseSpecificationSpec200)
          .extract().as(BookingIdResponseModel.class));

//    step("Проверка соответствия имени", () ->
//    assertThat(response.getFirstname()).isEqualTo(request.getFirstname());
//    step("Проверка соответствия фамилии", () ->
//      assertThat(response.getFirstname()).isEqualTo(request.getFirstname());
//
//    step("Проверка соответствия цены", () ->
//      assertThat(response.getFirstname()).isEqualTo(request.getFirstname());
//
//    step("Проверка статуса депозита", () ->
//      assertEquals(false, response.getDepositpaid(), "Статус депозита не совпадает"));
//
//    step("Проверка даты заезда", () ->
//      assertEquals("2017-03-16", response.getBookingdates().getCheckin(), "Дата заезда не совпадает"));
//
//    step("Проверка даты выезда", () ->
//      assertEquals("2019-03-11", response.getBookingdates().getCheckout(), "Дата выезда не совпадает"));
//
//    step("Проверка дополнительных пожеланий", () ->
//      assertNull(response.getAdditionalneeds(), "Дополнительные пожелания не совпадают"));
 }

}
