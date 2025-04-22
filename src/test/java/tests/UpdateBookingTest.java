package tests;

import data.BookingDataGenerator;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import models.request.UpdateBookingRequestModel;
import models.response.UpdateBookingResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static specs.Specifications.defaultRequestSpec;
import static specs.Specifications.responseSpecificationSpec200;

public class UpdateBookingTest extends TestBase{
  @Test
  @Owner("@perepelovaAS")
  @Severity(SeverityLevel.CRITICAL)
  @DisplayName("Проверка обновления данных")
  public void checkBookingUpdate2() {
    tests.AuthorizationAPI authorizationAPI = new tests.AuthorizationAPI();
    authorizationAPI.generateToken();
    String token = authorizationAPI.getToken();
    UpdateBookingRequestModel request = BookingDataGenerator.generateUpdateBookingRequest();
    UpdateBookingResponseModel response =
      step("Отправляем запрос на сервер на обновление бронирования по id=2", () ->
        given(defaultRequestSpec)
          .body(request)
          .header("Accept", "application/json")
          .header("Cookie", "token=" + token)
          .when()
          .put("/booking/" + 2)
          .then()
          .spec(responseSpecificationSpec200)
          .extract().as(UpdateBookingResponseModel.class));
    step("Проверка корректности обновления имени", () -> {
      assertThat(response.getFirstname()).isEqualTo(request.getFirstname());
    });
    step("Проверка корректности обновления фамилии", () -> {
      assertThat(response.getLastname()).isEqualTo(request.getLastname());
    });
    step("Проверка корректности обновления общей цены", () -> {
      assertThat(response.getTotalprice()).isEqualTo(request.getTotalprice());
    });
    step("Проверка корректности обновления статуса предоплаты", () -> {
      assertThat(response.getDepositpaid()).isEqualTo(request.getDepositpaid());
    });
    step("Проверка корректности обновления дополнительных нужд", () -> {
      assertThat(response.getAdditionalneeds()).isEqualTo(request.getAdditionalneeds());
    });
    step("Проверка корректности обновления дат бронирования", () -> {
      assertThat(response.getBookingdates().getCheckin()).isEqualTo(request.getBookingdates().getCheckin());
      assertThat(response.getBookingdates().getCheckout()).isEqualTo(request.getBookingdates().getCheckout());
    });
  }
}
