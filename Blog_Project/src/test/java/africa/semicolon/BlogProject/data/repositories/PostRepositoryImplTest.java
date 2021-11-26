package africa.semicolon.BlogProject.data.repositories;

import africa.semicolon.BlogProject.data.models.Author;
import africa.semicolon.BlogProject.data.models.Post;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PostRepositoryImplTest {
    PostRepository postRepository;

    @BeforeEach
    void setUp() {
        postRepository = new PostRepositoryImpl();
    }

    @AfterEach
    void tearDown() {
    }

    public Post testHelper() {
        Author author = new Author("olatoye", "toye@email.com", "Olatoye David");
        Post post = new Post();
        post.setAuthor(author);
        post.setHeadline("Saving effects of clean code");
        post.setBody("Taciti primis ante ac tellus odio aptent vulputate pretium tincidunt hac habitant");
        return postRepository.savePost(post);
    }

    @Test
    void save() {
        Post savedPost = testHelper();
        assertEquals(1, savedPost.getPostId());
        assertEquals(1, postRepository.findAllPosts().size());
    }

    @Test
    void updatePost() {
        Post savedPost = testHelper();
        String headline = savedPost.getHeadline();
        String body = """
                A curae ad sodales nulla accumsan tellus odio lobortis conubia.
                Per dictum rhoncus rutrum magnis pulvinar consectetur laoreet taciti.
                """;
        postRepository.updatePost(savedPost, headline, body);

        assertEquals(1, savedPost.getPostId());
        assertEquals(1, postRepository.findAllPosts().size());
        assertEquals(body, savedPost.getBody());
    }

    @Test
    void findPostByHeadline() {
        Post savedPost = testHelper();
        String headline = "Saving effects of clean code";
        Post foundPost = postRepository.findPostByHeadline(headline);

        assertEquals(foundPost, savedPost);
    }

    @Test
    void findPostByAuthor() {
        Post savedPost = testHelper();
        Author author = savedPost.getAuthor();
        List<Post> foundPosts = postRepository.findPostsByAuthor(author);

        assertTrue(foundPosts.contains(savedPost));
    }

    @Test
    void findAllPosts() {
        Post savedPost = testHelper();
        assertEquals(1, postRepository.findAllPosts().size());
    }

    @Test
    void deletePostsByAuthorUserEmail() {
        Author firstAuthor = new Author("olatoye", "toye@email.com", "Olatoye David");
        Post post = new Post();
        post.setAuthor(firstAuthor);
        post.setHeadline("Saving effects of clean code");
        post.setBody("Taciti primis ante ac tellus odio aptent vulputate pretium tincidunt hac habitant");
        postRepository.savePost(post);

        Author secondAuthor = new Author("david", "toye_david@email.com", "David Olatoye");
        Post anotherPost = new Post();
        anotherPost.setAuthor(secondAuthor);
        anotherPost.setHeadline("Saving effects of clean code");
        anotherPost.setBody("Taciti primis ante ac tellus odio aptent vulputate pretium tincidunt hac habitant");
        postRepository.savePost(anotherPost);

        assertEquals(2, postRepository.findAllPosts().size());

        postRepository.deletePostsByAuthorEmail("toye@email.com");
        assertEquals(1, postRepository.findAllPosts().size());
        assertEquals(anotherPost, postRepository.findAllPosts().get(0));
    }

    @Test
    void deletePost() {
        
    }

    @Test
    void deleteAll() {
    }
}