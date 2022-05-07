package qa_guru.practices.lesson_15;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import qa_guru.practices.lesson_13.AllureHelper;

import static com.codeborne.selenide.FileDownloadMode.FOLDER;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class BaseTestFixture {
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

        printConfig();
    }

    public static void printConfig() {
        System.out.println("Set up configuration:");
        System.out.println("Configuration.baseUrl: " + Configuration.baseUrl);
        System.out.println("Configuration.timeout: " + Configuration.timeout);
        System.out.println("Configuration.pollingInterval: " + Configuration.pollingInterval);
        System.out.println("Configuration.holdBrowserOpen: " + Configuration.holdBrowserOpen);
        System.out.println("Configuration.reopenBrowserOnFail: " + Configuration.reopenBrowserOnFail);
        System.out.println("Configuration.browser: " + Configuration.browser);
        System.out.println("Configuration.browserVersion: " + Configuration.browserVersion);
        System.out.println("Configuration.remote: " + Configuration.remote);
        System.out.println("Configuration.browserSize: " + Configuration.browserSize);
        System.out.println("Configuration.browserPosition: " + Configuration.browserPosition);
        System.out.println("Configuration.browserCapabilities: " + Configuration.browserCapabilities.asMap());
        System.out.println("Configuration.pageLoadStrategy: " + Configuration.pageLoadStrategy);
        System.out.println("Configuration.pageLoadTimeout: " + Configuration.pageLoadTimeout);
        System.out.println("Configuration.clickViaJs: " + Configuration.clickViaJs);
        System.out.println("Configuration.screenshots: " + Configuration.screenshots);
        System.out.println("Configuration.savePageSource: " + Configuration.savePageSource);
        System.out.println("Configuration.reportsFolder: " + Configuration.reportsFolder);
        System.out.println("Configuration.downloadsFolder: " + Configuration.downloadsFolder);
        System.out.println("Configuration.reportsUrl: " + Configuration.reportsUrl);
        System.out.println("Configuration.fastSetValue: " + Configuration.fastSetValue);
        System.out.println("Configuration.selectorMode: " + Configuration.selectorMode);
        System.out.println("Configuration.assertionMode: " + Configuration.assertionMode);
        System.out.println("Configuration.fileDownload: " + Configuration.fileDownload);
        System.out.println("Configuration.proxyEnabled: " + Configuration.proxyEnabled);
        System.out.println("Configuration.proxyHost: " + Configuration.proxyHost);
        System.out.println("Configuration.proxyPort: " + Configuration.proxyPort);
        System.out.println("Configuration.driverManagerEnabled: " + Configuration.driverManagerEnabled);
        System.out.println("Configuration.webdriverLogsEnabled: " + Configuration.webdriverLogsEnabled);
        System.out.println("Configuration.headless: " + Configuration.headless);
        System.out.println("Configuration.browserBinary: " + Configuration.browserBinary);

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
}
