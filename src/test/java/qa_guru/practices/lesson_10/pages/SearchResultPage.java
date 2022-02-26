package qa_guru.practices.lesson_10.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class SearchResultPage {
    @Step("Открываем репозиторий {repo}")
    public SearchResultPage openRepository(String repo) {
        $(By.linkText(repo)).click();

        return this;
    }
}
