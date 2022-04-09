package qa_guru.practices.lesson_16;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import qa_guru.practices.lesson_13.AllureHelper;

import static com.codeborne.selenide.FileDownloadMode.FOLDER;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("allure_notifi_test")
public class SimpleTagTest {
    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        Configuration.baseUrl = System.getProperty("baseUrl", "https://demoqa.com");
        Configuration.browserSize = System.getProperty("browserSize", "1920x1080");
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.fileDownload = FOLDER;
        Configuration.remote = System.getProperty("remote", "http://localhost:4444/wd/hub");
        Configuration.browserPosition = "0x0";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
    }

    @BeforeEach
    void openBaseUrl() {
        open("");
    }

    @AfterEach
    void tearDown() {
        AllureHelper.screenshotAs("Last screenshot");
        AllureHelper.pageSource();
        AllureHelper.browserConsoleLogs();
        AllureHelper.addVideo();
        closeWebDriver();
    }

    @Test
    void test1() {
        assertTrue(true);
    }

    @Test
    void test2() {
        assertTrue(true);
    }

    @Test
    void test3() {
        assertTrue(true);
    }
}
