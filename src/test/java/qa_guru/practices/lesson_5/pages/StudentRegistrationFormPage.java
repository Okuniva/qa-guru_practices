package qa_guru.practices.lesson_5.pages;

import qa_guru.practices.lesson_5.pages.components.CalendarComponent;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class StudentRegistrationFormPage {
    public static void openStudentRegistrationForm() {
        open("/automation-practice-form");
        $(".main-header").shouldHave(text("Practice Form"));
    }

    public static void fillMainInfo(String firstName, String lastName, String email, String gender, String mobile) {
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $(byText(gender)).click();
        $("#userNumber").setValue(mobile);
    }

    public static void fillDateOfBithday(String day, String month, String year) {
        $("#dateOfBirthInput").click();
        CalendarComponent.setDate(day, month, year);
    }

    public static void fillSubjects(String[] subjects) {
        for (String subject : subjects) {
            $("#subjectsInput").setValue(subject).pressEnter();
        }
    }

    public static void fillHobbies(String[] hobbies) {
        for (String hobby : hobbies) {
            $("#hobbiesWrapper").find(byText(hobby)).click();
        }
    }

    public static void uploadPhoto(String photo) {
        $("#uploadPicture").uploadFromClasspath(photo);
    }

    public static void fillAdressInfo(String address, String state, String city) {
        $("#currentAddress").scrollIntoView(true).setValue(address);
        $("#stateCity-wrapper").scrollIntoView(true).find(("#state")).click();
        $(byText(state)).click();
        $("#stateCity-wrapper").scrollIntoView(true).find(("#city")).click();
        $(byText(city)).click();
    }

    public static void submit() {
        $("#submit").click();
    }

    public static void closeTable() {
        $("#closeLargeModal").click();
        $(".table-responsive").shouldNotBe(visible);
    }

    public static void checkTable(String firstName, String lastName, String email, String gender, String mobile, String day, String month, String year,
                                  String[] subjects, String[] hobbies, String photo, String address, String state, String city) {
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
                textCaseSensitive("Picture " + photo),
                textCaseSensitive("Address " + address),
                textCaseSensitive("State and City " + state + " " + city)
        );
    }
}
