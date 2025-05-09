package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;

public class Specifications {
  public static RequestSpecification defaultRequestSpec = with()
    .filter(withCustomTemplates())
    .log().all()
    .contentType(JSON);
  public static ResponseSpecification responseSpecificationSpec200 = new ResponseSpecBuilder()
    .expectStatusCode(200)
    .log(STATUS)
    .log(BODY)
    .build();
  public static ResponseSpecification responseSpecificationSpec201 = new ResponseSpecBuilder()
    .expectStatusCode(201)
    .log(STATUS)
    .log(BODY)
    .build();
  public static ResponseSpecification responseSpecificationSpec404 = new ResponseSpecBuilder()
    .expectStatusCode(404)
    .log(STATUS)
    .log(BODY)
    .build();
}
