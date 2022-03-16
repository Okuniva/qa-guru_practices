package qa_guru.practices.lesson_9;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class CheckBoxTest {
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

    @ParameterizedTest()
    @MethodSource("provideCheckboxSelectExpResultMessage")
    void checkboxTest(String[] checkboxes, String[] result) {
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

        for (String checkbox : checkboxes) {
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
