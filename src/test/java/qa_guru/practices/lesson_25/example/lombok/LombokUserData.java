package qa_guru.practices.lesson_25.example.lombok;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LombokUserData {
    @JsonProperty("data")
    private User user;
}
