package africa.semicolon.BlogProject.data.models;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Comment {
    private Author author;
    private String body;
    private LocalDateTime timeOfCreation = LocalDateTime.now();
//    private List<Comment> comments;
}
