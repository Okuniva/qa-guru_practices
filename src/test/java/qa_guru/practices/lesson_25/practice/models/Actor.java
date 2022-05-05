package qa_guru.practices.lesson_25.practice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Actor {
    Integer id;
    String login;
    String display_login;
    String gravatar_id;
    String url;
    String avatar_url;
}
