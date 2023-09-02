package exercise.dto.posts;

import java.util.List;

import com.fasterxml.jackson.databind.ser.Serializers;
import exercise.model.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import exercise.dto.BasePage;

// BEGIN
@AllArgsConstructor
@Getter
public class PostsPage extends BasePage {
    private List<Post> posts;
    public String term;
    public String flash;

}
// END
