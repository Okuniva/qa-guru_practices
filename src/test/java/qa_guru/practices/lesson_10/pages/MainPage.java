package qa_guru.practices.lesson_10.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MainPage {
    @Step("Открываем главную страницу")
    public MainPage openMainPage() {
        open("https://github.com");

        return this;
    }

    @Step("Ищем репоизторий {repo}")
    public MainPage searchForRepository(String repo) {
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(repo);
        $(".header-search-input").submit();

        return this;
    }

}
