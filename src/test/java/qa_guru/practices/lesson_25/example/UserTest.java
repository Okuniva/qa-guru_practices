package qa_guru.practices.lesson_25.example;

import org.junit.jupiter.api.Test;
import qa_guru.practices.lesson_25.example.lombok.LombokUserData;
import qa_guru.practices.lesson_25.example.models.UserData;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static qa_guru.practices.lesson_25.example.Specs.request;
import static qa_guru.practices.lesson_25.example.Specs.responseSpec;

public class UserTest {
    @Test
    void singleUser() {
        // @formatter:off
        given()
            .spec(request)
        .when()
            .get("/users/2")
        .then()
            .spec(responseSpec)
            .log().body();
        // @formatter:on
    }

    @Test
    void listOfUsers() {
        // @formatter:off
        given()
            .spec(request)
        .when()
            .get("/users?page=2")
        .then()
            .log().body();
        // @formatter:on
    }

    @Test
    void singleUserWithModel() {
        // @formatter:off
        UserData data = given()
                            .spec(request)
                        .when()
                            .get("/users/2")
                        .then()
                            .spec(responseSpec)
                            .log().body()
                            .extract().as(UserData.class);
        // @formatter:on
        assertEquals(2, data.getData().getId());
    }

    @Test
    void singleUserWithLombokModel() {
        // @formatter:off
        LombokUserData data = given()
                                    .spec(request)
                              .when()
                                    .get("/users/2")
                              .then()
                                    .spec(responseSpec)
                                    .log().body()
                                    .extract().as(LombokUserData.class);
        // @formatter:on
        assertEquals(2, data.getUser().getId());
    }

    @Test
    public void checkEmailUsingGroovy() {
        // @formatter:off
        given()
            .spec(request)
        .when()
            .get("/users")
        .then()
            .log().body()
            .body("data.findAll{it.email =~/.*?@reqres.in/}.email.flatten()",
                        hasItem("eve.holt@reqres.in"));
        // @formatter:on
    }
}
