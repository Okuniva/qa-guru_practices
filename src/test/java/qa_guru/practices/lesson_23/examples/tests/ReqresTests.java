package qa_guru.practices.lesson_23.examples.tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.is;

public class ReqresTests {

    @Test
    void successfulLogin() {
        /*
        Request url:
        https://reqres.in/api/login

        Data:
        {
            "email": "eve.holt@reqres.in",
            "password": "cityslicka"
        }

        Response:
        {
            "token": "QpwL5tke4Pnpja7X4"
        }
         */

        String data = "{ \"email\": \"eve.holt@reqres.in\", " +
                "\"password\": \"cityslicka\" }";

        given()
                .contentType(JSON)
                .body(data)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void negativeLogin() {
        /*
        Request url:
        https://reqres.in/api/login

        Data:
        {
            "email": "eve.holt@reqres.in"
        }

        Response:
        {
            "error": "Missing password"
        }
         */

        String data = "{ \"email\": \"eve.holt@reqres.in\"}";

        given()
                .contentType(JSON)
                .body(data)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .statusCode(400)
                .body("error", is("Missing password"));
    }
}
