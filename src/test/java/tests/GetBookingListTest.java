package tests;

import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static specs.Specifications.defaultRequestSpec;
import static specs.Specifications.responseSpecificationSpec200;

@Slf4j
public class GetBookingListTest extends TestBase {
  @Test
  @Owner("@perepelovaAS")
  @Severity(SeverityLevel.CRITICAL)
  @DisplayName("Проверка получения списка бронирований")
  void checkBookingList() {
    step("Проверка, что ответ пришел непустой со статусом 200", () ->
      given(defaultRequestSpec)
        .when()
        .get("/booking")
        .then()
        .spec(responseSpecificationSpec200)
        .body("$", hasSize(greaterThan(0)))
    );
  }
}
