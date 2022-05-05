package qa_guru.practices.lesson_23.practice.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AddToWishListResponse {
    Boolean success;
    String message;
    @JsonProperty("updatetopwishlistsectionhtml")
    String updateTopWishListSectionHtml;
}
