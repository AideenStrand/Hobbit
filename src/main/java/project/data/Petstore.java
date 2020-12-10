package project.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Petstore {

    @JsonProperty("id")
    private String id;
    @JsonProperty("category")
    private Category category;
    @JsonProperty("name")
    private String name;
    @JsonProperty("photoUrls")
    private List<String> photoUrls;
    @JsonProperty("tags")
    private List<Category> tags;
    @JsonProperty("status")
    private String status;

    public String getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public List<Category> getTags() {
        return tags;
    }

    public String getStatus() {
        return status;
    }
}
