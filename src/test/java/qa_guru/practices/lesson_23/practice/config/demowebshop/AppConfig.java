package qa_guru.practices.lesson_23.practice.config.demowebshop;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/demowebshop/app.properties"
})
public interface AppConfig extends Config {
    String webUrl();

    String apiUrl();

    String userLogin();

    String userPassword();
}
