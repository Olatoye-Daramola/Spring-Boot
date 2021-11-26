package africa.semicolon.BlogProject.data.repositories;

import africa.semicolon.BlogProject.data.models.Author;

import java.util.*;

public class AuthorRepositoryImpl implements AuthorRepository {
    private final Map<String, Author> database = new HashMap<>();

    @Override
    public Author save(Author author) {
        database.put(author.getUserEmail(), author);
        return database.get(author.getUserEmail());
    }

    @Override
    public Author findAuthorByUserEmail(String userEmail) {
        return database.get(userEmail);
    }

    @Override
    public Author findAuthor(Author author) {
        return findAuthorByUserEmail(author.getUserEmail());
    }

    @Override
    public Author findAuthorByPostHeadline(String postHeadline) {
        return null;
    }

    @Override
    public List<Author> findAllAuthors() {
        List<Author> all = new ArrayList<>();
        Set<String> keys = database.keySet();
        for (String key : keys) {
            all.add(database.get(key));
        }
        return all;
    }

    @Override
    public void deleteAuthorByUserEmail(String userEmail) {
        database.remove(userEmail);
    }


    @Override
    public void deleteAuthor(Author author) {
        deleteAuthorByUserEmail(author.getUserEmail());
    }

    @Override
    public void deleteAll() {
        database.clear();
    }
}
