package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;
import static reqres.helpers.CustomAllureListener.withCustomTemplates;

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
}
