package qa_guru.practices.lesson_22;

import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.codeborne.pdftest.assertj.Assertions.assertThat;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@Feature("Api Tests")
@Owner("Valeev_A_A")
public class ReqresTests {
    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "https://reqres.in/";
    }

    @Test
    @Story("Authorization")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify get user 2 have id = 2, email = 'janet.weaver@reqres.in'")
    void getSingleUserTest() {
        get("api/users/2")
                .prettyPeek()
                .then()
                .statusCode(200)
                .body("data.id", is(2),
                        "data.email", is("janet.weaver@reqres.in")
                );
    }

    @Test
    @Story("MainProducts")
    @Severity(SeverityLevel.CRITICAL)
    void getResourceListTest() {
        List<String> resourcesNames = get("api/unknown")
                .then()
                .statusCode(200)
                .extract()
                .path("data.name");

        List<String> expectedNames = List.of("cerulean", "fuchsia rose", "true red", "aqua sky", "tigerlily", "blue turquoise");

        assertThat(resourcesNames).isEqualTo(expectedNames);
    }

    @Test
    @Story("Authorization")
    @Severity(SeverityLevel.CRITICAL)
    @Description("post empty password on api/register, return 400 error message")
    void postEmptyPasswordTest() {
        String authorizedData = "{\"email\": \"sydney@fife\"}";

        given()
                .contentType(ContentType.JSON)
                .body(authorizedData)
                .when()
                .post("api/register")
                .then()
                .statusCode(400)
                .body("error", is("Missing password"));

    }

    @Test
    @Story("Authorization")
    @Severity(SeverityLevel.CRITICAL)
    @Description("put name and job to user2")
    void putUserTest() {
        String userData = "{ \"name\": \"morpheus\", \"job\": \"zion resident\" }";

        given()
                .contentType(ContentType.JSON)
                .body(userData)
                .when()
                .put("api/user/2")
                .then()
                .statusCode(200)
                .body("name", is("morpheus"),
                        "job", is("zion resident"),
                        "updatedAt", notNullValue());
    }

    @Test
    @Story("UserSetting")
    @Severity(SeverityLevel.NORMAL)
    @Description("delete user on api/user/2")
    void deleteUserTest() {
        delete("api/user/2")
                .then()
                .statusCode(204);
    }


}
