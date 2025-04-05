package tests;

import config.WebConfig;
import io.restassured.RestAssured;
import lombok.Getter;
import models.request.CreateUserModel;
import models.response.CreateUserResponseModel;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.given;
import static specs.Specifications.defaultRequestSpec;
import static specs.Specifications.responseSpecificationSpec200;

@Getter
public class AuthorizationAPI extends TestBase {
  private String token;
  private static final WebConfig config = ConfigFactory.create(WebConfig.class);

  public void generateToken() {
    CreateUserModel user = new CreateUserModel();
    user.setUsername(config.userName());
    user.setPassword(config.password());

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

}