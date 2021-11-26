package africa.semicolon.BlogProject.data.repositories;

import africa.semicolon.BlogProject.data.models.Author;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthorRepositoryImplTest {
    AuthorRepository authorRepository;

    @BeforeEach
    void setUp() {
        authorRepository = new AuthorRepositoryImpl();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void saveNewAuthor() {
        Author author = createAuthorHelper();

        Author savedAuthor = authorRepository.save(author);
        assertEquals(savedAuthor, author);
        assertEquals(1, authorRepository.findAllAuthors().size());
    }

    public Author createAuthorHelper() {
        return new Author("olatoye", "toye@email.com", "Olatoye David");
    }

    @Test
    void findAuthorByUserEmail() {
        Author author = authorRepository.save(createAuthorHelper());
        assertEquals(author.getUserEmail(), authorRepository.findAuthorByUserEmail(author.getUserEmail()).getUserEmail());
    }

    @Test
    void findAuthor() {
        Author author = authorRepository.save(createAuthorHelper());
        assertEquals(author.getAuthorName(), authorRepository.findAuthor(author).getAuthorName());
    }

    @Test
    void findAuthorByPostHeadline() {
    }

    @Test
    void findAllAuthors() {
        Author firstAuthor = new Author("olatoye", "toye@email.com", "Olatoye David");
        authorRepository.save(firstAuthor);

        Author secondAuthor = new Author("david", "toye_david@email.com", "David Olatoye");
        authorRepository.save(secondAuthor);

        assertEquals(2, authorRepository.findAllAuthors().size());
    }

    @Test
    void deleteAuthorByUserName() {
        Author author = authorRepository.save(createAuthorHelper());
        assertEquals(1, authorRepository.findAllAuthors().size());

        authorRepository.deleteAuthorByUserEmail(author.getUserEmail());
        assertEquals(0, authorRepository.findAllAuthors().size());

    }

    @Test
    void deleteAuthor() {
        Author firstAuthor = new Author("olatoye", "toye@email.com", "Olatoye David");
        authorRepository.save(firstAuthor);

        Author secondAuthor = new Author("david", "toye_david@email.com", "David Olatoye");
        authorRepository.save(secondAuthor);

        assertEquals(2, authorRepository.findAllAuthors().size());

        authorRepository.deleteAuthor(secondAuthor);
        assertEquals(1, authorRepository.findAllAuthors().size());
    }

    @Test
    void deleteAll() {
        authorRepository.save(createAuthorHelper());
        assertEquals(1, authorRepository.findAllAuthors().size());

        authorRepository.deleteAll();
        assertEquals(0, authorRepository.findAllAuthors().size());
    }
}