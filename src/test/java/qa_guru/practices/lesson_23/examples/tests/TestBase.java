package qa_guru.practices.lesson_23.examples.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import qa_guru.practices.lesson_13.AllureHelper;

import static com.codeborne.selenide.FileDownloadMode.FOLDER;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {
    @BeforeAll
    static void configureBaseUrl() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        RestAssured.baseURI = "http://docker.for.mac.localhost";

        Configuration.baseUrl = "http://docker.for.mac.localhost";
        Configuration.browserSize = System.getProperty("browserSize", "1920x1080");
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.fileDownload = FOLDER;
        Configuration.remote = System.getProperty("remote", null);
        Configuration.browserPosition = "0x0";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    void tearDown() {
        AllureHelper.screenshotAs("Last screenshot");
        AllureHelper.pageSource();
        AllureHelper.browserConsoleLogs();
        AllureHelper.addVideo();
        closeWebDriver();
    }
}
