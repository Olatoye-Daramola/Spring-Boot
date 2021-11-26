package africa.semicolon.BlogProject.data.repositories;

import africa.semicolon.BlogProject.data.models.Author;
import africa.semicolon.BlogProject.data.models.Post;
import africa.semicolon.BlogProject.exceptions.PostNotFoundException;

import java.util.*;

public class PostRepositoryImpl implements PostRepository{
    Map<Integer, Post> database = new HashMap<>();

    @Override
    public Post savePost(Post post) {
        Integer postId = null;
        if (post.getPostId() == null) {
            postId = database.size() + 1;
            post.setPostId(postId);
        }
        postId = post.getPostId();
        database.put(postId, post);
        return database.get(postId);
    }

    @Override
    public void updatePost(Post post, String headline, String body) {
        if (!(database.containsKey(post.getPostId()))) throw new PostNotFoundException("Post does not even exist");
        post.setHeadline(headline);
        post.setBody(body);
    }

    @Override
    public Post findPostByPostId(Integer postId) {
        return database.get(postId);
    }

    @Override
    public Post findPostByHeadline(String headline) {
        Post foundPost = new Post();
        for (Post post: database.values()) {
            if (post.getHeadline().equalsIgnoreCase(headline)) foundPost = post;
        }
        if (foundPost == null) throw new PostNotFoundException("Post cannot be found");
        return foundPost;
    }

    @Override
    public List<Post> findPostsByAuthor(Author author) {
        List<Post> foundPosts = new ArrayList<>();
        for (Post post: database.values()) {
            if (post.getAuthor().equals(author)) foundPosts.add(post);
        }
        if (foundPosts.isEmpty()) throw new PostNotFoundException("Author has no post");
        return foundPosts;
    }

    @Override
    public List<Post> findAllPosts() {
        List<Post> allPosts = new ArrayList<>();
        Set<Integer> keys = database.keySet();
        for(Integer key : keys) {
            allPosts.add(database.get(key));
        }
        return allPosts;
    }

    @Override
    public void deletePostsByAuthorEmail(String userEmail) {
        List<Post> listOfFoundPosts = new ArrayList<>();
        for(Post post : database.values()) {
            if (post.getAuthor().getUserEmail().equals(userEmail)) listOfFoundPosts.add(post);
        }
        for (Post post : listOfFoundPosts) {
            database.remove(post.getPostId());
        }
    }

    @Override
    public void deletePost(Post post) {

    }

    @Override
    public void deleteAll() {

    }
}
