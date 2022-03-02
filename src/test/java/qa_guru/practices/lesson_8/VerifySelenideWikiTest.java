package qa_guru.practices.lesson_8;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class VerifySelenideWikiTest {
    @Test
    void verifySoftAssertionsTest() {
        Configuration.browserSize = "1920x1080";
        open("https://github.com/selenide/selenide");
        $("h1").shouldHave(text("selenide / selenide"));

        $(by("data-content", "Wiki")).click();
        $(byText("Welcome to the selenide wiki!")).shouldBe(visible);

        $("#wiki-pages-box").$(withText("more pages")).click();
        $("#wiki-pages-box").$(byText("SoftAssertions")).click();
        $("#wiki-wrapper h1").shouldHave(text("SoftAssertions"));
        $("#wiki-body").shouldHave(
                text("JUnit5 extension - "),
                text("com.codeborne.selenide.junit5.SoftAssertsExtension"),
                text("Using JUnit5 extend test class:"),
                text("@ExtendWith({SoftAssertsExtension.class}) " +
                        "class Tests { " +
                        "  @Test " +
                        "  void test() { " +
                        "    Configuration.assertionMode = SOFT; " +
                        "    open(\"page.html\"); " +
                        " " +
                        "    $(\"#first\").should(visible).click(); " +
                        "    $(\"#second\").should(visible).click(); " +
                        "  } " +
                        "}")
                );

        closeWebDriver();
    }
}
