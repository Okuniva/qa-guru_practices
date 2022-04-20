package qa_guru.practices.lesson_20.config;

import org.aeonbits.owner.Config;

import java.net.URL;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/local.properties",
        "classpath:config/${configType}.properties"
})
public interface WebConfig extends Config {
    @Key("baseUrl")
    String getBaseUrl();

    @Key("browser")
    Browser getBrowser();

    @Key("browserVersion")
    String getBrowserVersion();

    @Key("browserSize")
    String getBrowserSize();

    @Key("remoteDriverUrl")
    URL getRemoteDriverUrl();
}
