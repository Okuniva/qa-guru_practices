package qa_guru.practices.lesson_2;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class StudentRegistrationFormTest {
    @Test
    void correctFillingFieldsTest() {
        open("https://demoqa.com/automation-practice-form");

        $("#firstName").setValue("testFirstName");
        $("#lastName").setValue("testLastName");
        $("#userEmail").setValue("testEmail@gmail.com");
        // ToDo select gender
        $("#userNumber").setValue("8937583231");
        // ToDo add fill arr fields
    }
}
