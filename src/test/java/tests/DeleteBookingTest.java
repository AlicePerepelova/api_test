package tests;

import api.BookingHelper;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.Specifications.defaultRequestSpec;
import static specs.Specifications.responseSpecificationSpec201;

public class DeleteBookingTest extends TestBase {
  private String token;
  private Integer bookingId;
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
    step("Создаем запись о бронировании", () -> {
      this.bookingId = BookingHelper.createBooking();
    });
    step("Удаляем запись о бронировании", () -> {
      given(defaultRequestSpec)
        .header("Cookie", "token=" + token)
        .when()
        .delete("/booking/" + bookingId)
        .then()
        .spec(responseSpecificationSpec201);
    });
  }
}