package qa_guru.practices.lesson_22;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.qameta.allure.*;
import org.junit.jupiter.api.Test;
import qa_guru.practices.lesson_22.data.Resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.codeborne.pdftest.assertj.Assertions.assertThat;
import static io.restassured.RestAssured.get;
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
        List<String> resourcesNames = get("https://reqres.in/api/unknown")
            .then()
                .statusCode(200)
            .extract()
                .path("data.name");

        List<String> expectedNames = List.of("cerulean", "fuchsia rose", "true red", "aqua sky", "tigerlily", "blue turquoise");

        assertThat(resourcesNames).isEqualTo(expectedNames);
    }



}
