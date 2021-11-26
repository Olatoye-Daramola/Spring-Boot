package africa.semicolon.BlogProject.data.repositories;


import africa.semicolon.BlogProject.data.models.Author;
import africa.semicolon.BlogProject.data.models.Post;

import java.util.List;

public interface PostRepository {
    Post savePost(Post post);
    void updatePost(Post post, String headline, String body);
    Post findPostByPostId(Integer id);
    Post findPostByHeadline(String headline);
    List<Post> findPostsByAuthor(Author author);
    //    TODO:
    List<Post> findAllPosts();
    void deletePostsByAuthorEmail(String userEmail);
    void deletePost(Post post);
    void deleteAll();
}
