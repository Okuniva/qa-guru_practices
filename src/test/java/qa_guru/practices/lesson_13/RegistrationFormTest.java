package qa_guru.practices.lesson_13;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationFormTest {
    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.browser = "chrome";
//        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
        Configuration.remote = "http://localhost:4444/wd/hub";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    void tearDown() {
//        AllureHelper.screenshotAs("Last screenshot");
//        AllureHelper.pageSource();
//        AllureHelper.browserConsoleLogs();
//        AllureHelper.addVideo();
        closeWebDriver();
    }

    @Test
    void successFillAllFieldsTest() {
        open("/automation-practice-form");
        $(".main-header").shouldHave(text("Practice Form"));

        String firstName = "testFirstName";
        $("#firstName").setValue(firstName);
        String lastName = "testLastName";
        $("#lastName").setValue(lastName);
        String email = "testEmail@gmail.com";
        $("#userEmail").setValue(email);
        String gender = "Other";
        $(byText(gender)).click();
        String mobile = "8937583231";
        $("#userNumber").setValue(mobile);

        $("#dateOfBirthInput").click();
        $(".react-datepicker-popper").shouldBe(visible);
        String year = "1997";
        $(".react-datepicker__year-select").selectOption(year);
        String month = "January";
        $(".react-datepicker__month-select").selectOption(month);
        String day = "30";
        $$(".react-datepicker__day:not(.react-datepicker__day--outside-month)").find(text(day)).click();
        $(".react-datepicker-popper").shouldNotBe(visible);

        String[] subjects = {"Maths", "Computer Science"};
        $("#subjectsInput").sendKeys("Mat");
        $(byText(subjects[0])).click();
        $("#subjectsInput").sendKeys("Computer Sci");
        $(byText(subjects[1])).click();

        String[] hobbies = {"Sports", "Music"};
        $("#hobbiesWrapper").find(byText(hobbies[0])).click();
        $("#hobbiesWrapper").find(byText(hobbies[1])).click();

//        // image is free to use from https://www.pexels.com/photo/black-and-white-portrait-of-woman-with-freckles-laughing-10724233/
//        String photo = "pexels-photo-10724233.jpeg";
//        $("#uploadPicture").uploadFromClasspath(photo);

        String address = "399 Jennings Lane Brooklyn, NY 11221";
        $("#currentAddress").scrollIntoView(true).setValue(address);

        $("#stateCity-wrapper").scrollIntoView(true).find(("#state")).click();
        String state = "NCR";
        $(byText(state)).click();
        $("#stateCity-wrapper").scrollIntoView(true).find(("#city")).click();
        String city = "Delhi";
        $(byText(city)).click();

        $("#submit").click();

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $$(".table-responsive table tbody tr").shouldHave(size(10));
        $(".table-responsive").shouldHave(
                textCaseSensitive("Student Name " + firstName + " " + lastName),
                textCaseSensitive("Student Email " + email),
                textCaseSensitive("Gender " + gender),
                textCaseSensitive("Mobile " + mobile),
                textCaseSensitive("Date of Birth " + day + " " + month + "," + year),
                textCaseSensitive("Subjects " + subjects[0] + ", " + subjects[1]),
                textCaseSensitive("Hobbies " + hobbies[0] + ", " + hobbies[1]),
//                textCaseSensitive("Picture " + photo),
                textCaseSensitive("Address " + address),
                textCaseSensitive("State and City " + state + " " + city)
        );
        $("#closeLargeModal").click();
        $(".table-responsive").shouldNotBe(visible);
    }
}
