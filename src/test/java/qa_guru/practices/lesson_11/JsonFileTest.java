package qa_guru.practices.lesson_11;


import io.qameta.allure.internal.shadowed.jackson.databind.JsonNode;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import qa_guru.practices.lesson_11.data.Squads;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonFileTest {
    @Test
    void jsonFileTest() throws IOException {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("squad.json")) {
            ObjectMapper mapper = new ObjectMapper();
            assert is != null;
            Squads squads = mapper.readValue(new String(is.readAllBytes(), StandardCharsets.UTF_8), Squads.class);

            assertThat(squads.getSquadName()).isEqualTo("Super hero squad");
            assertThat(squads.getFormed()).isEqualTo(2016);
            assertThat(squads.getActive()).isEqualTo(true);
            assertThat(squads.getMembers().get(1).getName()).isEqualTo("Madame Uppercut");
            assertThat(squads.getMembers().get(1).getAge()).isEqualTo(39);
        }
    }
}
