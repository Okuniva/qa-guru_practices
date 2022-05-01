package qa_guru.practices.lesson_23.practice;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.pdftest.assertj.Assertions.assertThat;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@Story("Auth test")
@Owner("Valeev_A_A")
@Tag("lesson_23")
public class AuthTests extends TestBase {
    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Successful authorization")
    void loginTest() {
        step("Open login page", () -> {
                open("");
                $(".esh-identity-name").as("login btn").click();
        });

        assertThat(WebDriverRunner.url()).contains("http://docker.for.mac.localhost:5105/Account/Login?ReturnUrl=");

        step("Fill login form", () -> {
            $("#Email").setValue("demouser@microsoft.com");
            $("#Password").setValue("Pass@word1")
                    .pressEnter();
        });

        step("Verify successful authorization", () ->
                $(".esh-identity-name").shouldHave(text("demouser@microsoft.com")));
    }
}
