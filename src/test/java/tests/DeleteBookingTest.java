package tests;

import api.BookingHelper;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import models.response.CreateBookingResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.Specifications.*;

public class DeleteBookingTest extends TestBase {
  private String token;

  @Test
  @Story("Позитивный тест")
  @Owner("@perepelovaAS")
  @Severity(SeverityLevel.CRITICAL)
  @DisplayName("Проверка удаления бронирования")
  public void checkBookingDelete() {
    step("Авторизуемся, получаем токен", () -> {
      tests.AuthorizationAPI authorizationAPI = new tests.AuthorizationAPI();
      authorizationAPI.generateToken();
      this.token = authorizationAPI.getToken();
    });
    CreateBookingResponseModel booking = step("Создание записи о бронировании", BookingHelper::createBooking);
    Integer bookingId = booking.getBookingid();
    step("Удаляем запись о бронировании", () -> {
      given(defaultRequestSpec)
        .header("Cookie", "token=" + token)
        .when()
        .delete("/booking/" + bookingId)
        .then()
        .spec(responseSpecificationSpec201);
    });
    step("Отправить запрос на получение информации по конкретному бронированию", () ->
      given(defaultRequestSpec)
        .when()
        .get("/booking/" + bookingId)
        .then()
        .spec(responseSpecificationSpec404));
  }
}