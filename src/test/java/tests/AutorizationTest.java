package tests;

import models.request.CreateUserModel;
import models.response.CreateUserResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static specs.Specifications.defaultRequestSpec;
import static specs.Specifications.responseSpecificationSpec200;

public class AutorizationTest extends TestBase {
  @Test
  @DisplayName("Успешное создание токена аутентификации")
  public void checkAuth() {
    CreateUserModel user = new CreateUserModel();
    user.setUsername("admin");
    user.setPassword("password123");

    CreateUserResponseModel response = step("Создание токена аутентификации", () ->
      given(defaultRequestSpec)
        .body(user)
        .when()
        .post("/auth")
        .then()
        .spec(responseSpecificationSpec200)
        .extract().as(CreateUserResponseModel.class)
    );

    step("Проверка, что ответ с сервера пришел непустой", () ->
      assertThat(response).isNotNull()
    );

    step("Проверка, что токен непустой", () -> {
      assertThat(response.getToken()).isNotNull();
      assertThat(response.getToken()).isNotEmpty();
    });
  }
}