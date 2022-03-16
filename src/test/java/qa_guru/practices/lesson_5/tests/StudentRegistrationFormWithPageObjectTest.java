package qa_guru.practices.lesson_5.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import qa_guru.practices.lesson_5.pages.StudentRegistrationFormPage;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class StudentRegistrationFormWithPageObjectTest {
    String firstName = "testFirstName";
    String lastName = "testLastName";
    String email = "testEmail@gmail.com";
    String gender = "Other";
    String mobile = "8937583231";
    String year = "1991";
    String month = "June";
    String day = "19";
    String[] subjects = {"Maths", "Computer Science"};
    String[] hobbies = {"Sports", "Music"};
    // image is free to use from https://www.pexels.com/photo/black-and-white-portrait-of-woman-with-freckles-laughing-10724233/
    String photo = "pexels-photo-10724233.jpeg";
    String address = "399 Jennings Lane Brooklyn, NY 11221";
    String state = "NCR";
    String city = "Gurgaon";

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.browser = "chrome";
    }

    @AfterEach
    void tearDown() {
        closeWebDriver();
    }

    @Test
    void successFillAllFieldsTest() {
        StudentRegistrationFormPage.openStudentRegistrationForm();
        StudentRegistrationFormPage.fillMainInfo(firstName, lastName, email, gender, mobile);
        StudentRegistrationFormPage.fillDateOfBithday(day, month, year);
        StudentRegistrationFormPage.fillSubjects(subjects);
        StudentRegistrationFormPage.fillHobbies(hobbies);
        StudentRegistrationFormPage.uploadPhoto(photo);
        StudentRegistrationFormPage.fillAdressInfo(address, state, city);
        StudentRegistrationFormPage.submit();

        StudentRegistrationFormPage.checkTable(
                firstName,
                lastName,
                email,
                gender,
                mobile,
                day,
                month,
                year,
                subjects,
                hobbies,
                photo,
                address,
                state,
                city);

        StudentRegistrationFormPage.closeTable();
    }
}
