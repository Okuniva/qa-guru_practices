package qa_guru.practices.lesson_9;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
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
    @ValueSource(strings = {"@gmail.com", " @gmail.com", "test@@gmail.com", "test@gmailcom", "test@gmail.c", "test+men@gmail.com"})
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

    @ParameterizedTest()
    @MethodSource("provideCheckboxSelectExpResultMessage")
    void checkboxTest(String[] checkboxs, String[] result) {
        open("/checkbox");
        // expand Home
        $$(".rct-icon-expand-close").shouldHave(size(1)).get(0).click();

        ElementsCollection secondTreeCheckboxLevel = $$(".rct-icon-expand-close").snapshot();
        secondTreeCheckboxLevel.shouldHave(size(3));
        // expand Desktop
        secondTreeCheckboxLevel.get(0).click();
        // expand Documents
        secondTreeCheckboxLevel.get(1).click();
        // expand Downloads
        secondTreeCheckboxLevel.get(2).click();

        ElementsCollection thirdTreeCheckboxLevel = $$(".rct-icon-expand-close").snapshot();
        thirdTreeCheckboxLevel.shouldHave(size(2));
        // expand WorkSpace
        thirdTreeCheckboxLevel.get(0).click();
        // expand Office
        thirdTreeCheckboxLevel.get(1).click();

        for (String checkbox : checkboxs) {
            $(byText(checkbox)).click();
        }

        $("#result").shouldHave(text("You have selected :"));
        for (String expText : result) {
            $("#result").shouldHave(text(expText));
        }
    }

    private static Stream<Arguments> provideCheckboxSelectExpResultMessage() {
        return Stream.of(
                Arguments.of(new String[] {"Home"},
                        new String[] {"home", "desktop", "notes","commands", "documents",
                                "workspace", "react", "angular", "veu", "office", "public", "private", "classified",
                                "general", "downloads", "wordFile", "excelFile"}),
                Arguments.of(new String[] {"Desktop"},
                        new String[] {"desktop", "notes", "commands", }),
                Arguments.of(new String[] {"Desktop", "WorkSpace", "Downloads"},
                        new String[] {"desktop", "notes", "commands", "workspace", "react", "angular", "veu", "downloads",
                                "wordFile", "excelFile"}),
                Arguments.of(new String[] {"WorkSpace", "Public", "General", "Excel File.doc"},
                        new String[] {"workspace", "react", "angular", "veu", "public", "general", "excelFile"})
        );
    }

}
