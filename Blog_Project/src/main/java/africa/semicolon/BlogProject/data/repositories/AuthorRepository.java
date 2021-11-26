package africa.semicolon.BlogProject.data.repositories;

import africa.semicolon.BlogProject.data.models.Author;

import java.util.List;

public interface AuthorRepository {
    Author save(Author author);
    Author findAuthorByUserEmail(String userEmail);
    Author findAuthor(Author author);
//    TODO:
    Author findAuthorByPostHeadline(String postHeadline);
    List<Author> findAllAuthors();
    void deleteAuthorByUserEmail(String userEmail);
    void deleteAuthor(Author author);
    void deleteAll();
}
