package qa_guru.practices.lesson_22;

import io.qameta.allure.*;
import org.junit.jupiter.api.Test;
import qa_guru.practices.lesson_22.data.Resource;

import java.util.List;

import static io.restassured.RestAssured.get;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.is;

@Feature("Api Tests")
@Owner("Valeev_A_A")
public class ReqresTests {
    @Test
    @Story("Authorization")
    @Severity(SeverityLevel.CRITICAL)
    @Description(
            "Verify get user 2 have id = 2, email = 'janet.weaver@reqres.in'"
    )
    void getSingleUserTest() {
        get("https://reqres.in/api/users/2")
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
        List<Resource> resources = get("https://reqres.in/api/unknown")
                .then()
                .statusCode(200)
                .extract()
                .path("data");

    }



}
