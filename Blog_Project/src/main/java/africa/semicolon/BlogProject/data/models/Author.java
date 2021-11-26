package africa.semicolon.BlogProject.data.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Author {
    private String userName;
    private String userEmail;
    private String authorName;
//    List<Post> posts;
//    List<Comment> comments;
}
