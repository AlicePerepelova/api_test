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
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static specs.Specifications.defaultRequestSpec;
import static specs.Specifications.responseSpecificationSpec200;


public class CreateBookingTest extends TestBase {
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
    {
      assertThat(response.getBooking().getFirstname())
        .as("Имя гостя не совпадает")
        .isEqualTo(request.getFirstname());
    });

    step("Проверка корректности фамилии гостя", () ->
    {
      assertThat(response.getBooking().getLastname())
        .as("Фамилия гостя не совпадает")
        .isEqualTo(request.getLastname());
    });
    step("Проверка корректности общей цены", () ->
    {
      assertThat(response.getBooking().getTotalprice())
        .as("Общая цена не совпадает")
        .isEqualTo(request.getTotalprice());
    });

    step("Проверка корректности статуса депозита", () ->
    {
      assertThat(request.getDepositpaid())
        .as("Статус депозита не совпадает")
        .isEqualTo(response.getBooking().getDepositpaid());
    });

    step("Проверка корректности даты заезда", () ->
    {
      assertThat(request.getBookingdates().getCheckin())
        .as("Дата заезда не совпадает")
        .isEqualTo(response.getBooking().getBookingdates().getCheckin());
    });
    step("Проверка корректности даты выезда", () -> {
      assertThat(request.getBookingdates().getCheckout())
        .as("Дата выезда не совпадает")
        .isEqualTo(response.getBooking().getBookingdates().getCheckout());
    });

    step("Проверка корректности дополнительных пожеланий", () -> {
      assertThat(request.getAdditionalneeds())
        .as("Дополнительные пожелания не совпадают")
        .isEqualTo(response.getBooking().getAdditionalneeds());
    });
  }
}