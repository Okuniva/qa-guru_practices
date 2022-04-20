package qa_guru.practices.lesson_20;

import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;
import qa_guru.practices.lesson_20.config.WebConfig;

public class OwnerConfigTest {
    private final WebConfig config;

    public OwnerConfigTest() {
        this.config = ConfigFactory.create(WebConfig.class, System.getProperties());
    }

    @Test
    void configTest() {
        System.out.println(config.toString()); // ToDo check env
    }
}
