package qa_guru.practices.lesson_10.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class RepositoryPage {
    @Step("Переходим в таб Issue")
    public RepositoryPage openIssueTab() {
        $(By.partialLinkText("Issues")).click();

        return this;
    }

    @Step("Проверяем что существует Issue с номером {num}")
    public RepositoryPage shouldSeeIssueWithNumber(int num) {
        $(withText("#68")).should(exist);

        return this;
    }
}
