package qa_guru.practices.lesson_9;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class TextBoxTest {
    @BeforeEach
    void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.browser = "chrome";
    }

    @AfterEach
    void tearDown() {
        closeWebDriver();
    }

    @ParameterizedTest()
    @ValueSource(strings = { "@gmail.com", " @gmail.com", "test@@gmail.com", "test@gmailcom", "test@gmail.c", "test+men@gmail.com" })
    void negativeEmailsTest(String email) {
        open("/text-box");

        $("#userEmail").setValue(email);
        $("#submit").click();
        $("#userEmail").shouldHave(cssValue("border", "1px solid rgb(255, 0, 0)"));
    }


    @ParameterizedTest()
    @CsvSource(value = {
            "Alex A.N.| alex@gmail.com| Gurdon, Arkansas(AR), 71743| Marshall, Missouri(MO), 65340",
            "Norman D.X.| normal@gmail.com| Springfield, Missouri(MO), 65810| Montague, Michigan(MI), 49437"
    }, delimiter = '|')
    void successEnterAllFields(String fullName, String email, String currentAddress, String permanentAddress) {
        open("/text-box");

        $("#userName").setValue(fullName);
        $("#userEmail").setValue(email);
        $("#currentAddress").setValue(currentAddress);
        $("#permanentAddress").setValue(permanentAddress);
        $("#submit").click();

        $("#output").shouldHave(text(fullName),
                text(email),
                text(currentAddress),
                text(permanentAddress));
    }


}
