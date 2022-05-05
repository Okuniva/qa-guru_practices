package qa_guru.practices.lesson_25.practice.Specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static org.hamcrest.CoreMatchers.containsString;

public class BaseSpec {
    public static RequestSpecification request = with()
            .baseUri("https://api.coindesk.com")
            .basePath("/v1")
            .log().all()
            .contentType(ContentType.JSON);

    public static ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectBody(containsString("time"))
            .build();
}
