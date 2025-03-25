package tests;

import models.request.CreateUserModel;
import models.response.CreateUserResponseModel;

import static io.restassured.RestAssured.given;
import static specs.Specifications.defaultRequestSpec;
import static specs.Specifications.responseSpecificationSpec200;

public class AuthorizationAPI extends TestBase {
  private String token; // Поле для хранения токена

  public void generateToken() {
    CreateUserModel user = new CreateUserModel();
    user.setUsername("admin");
    user.setPassword("password123");

    CreateUserResponseModel response =
      given(defaultRequestSpec)
        .body(user)
        .when()
        .post("/auth")
        .then()
        .spec(responseSpecificationSpec200)
        .extract().as(CreateUserResponseModel.class);


    this.token = response.getToken();
    System.out.println("Generated token: " + this.token);
  }

  public String getToken() {
    return token;
  }
}