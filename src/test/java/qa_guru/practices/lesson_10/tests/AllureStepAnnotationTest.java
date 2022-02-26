package qa_guru.practices.lesson_10.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qa_guru.practices.lesson_10.pages.MainPage;
import qa_guru.practices.lesson_10.pages.RepositoryPage;
import qa_guru.practices.lesson_10.pages.SearchResultPage;

@Feature("Задачи в репозитории")
public class AllureStepAnnotationTest {
    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final int ISSUE_NUMBER = 68;

    @Test
    @Owner("valeev_a_a")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Создание новой задачи")
    @DisplayName("Проверка отображения Issue для не авторизованного пользователя")
    @Description(
            "Этот тест проверят отображения Issue для не авторизованного пользователя...."
    )
    @Link(value = "Testing", url = "https://github.com")
    void checkIssueNameExistTest() {
        Allure.parameter("REPOSITORY", REPOSITORY);
        Allure.parameter("ISSUE_NUMBER", ISSUE_NUMBER);

        new MainPage()
                .openMainPage()
                .searchForRepository(REPOSITORY);

        new SearchResultPage()
                .openRepository(REPOSITORY);

        new RepositoryPage()
                .openIssueTab()
                .shouldSeeIssueWithNumber(ISSUE_NUMBER);
    }
}
