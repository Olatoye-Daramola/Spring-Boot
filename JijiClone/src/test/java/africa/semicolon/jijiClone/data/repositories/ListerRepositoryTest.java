package africa.semicolon.jijiClone.data.repositories;

import africa.semicolon.jijiClone.data.models.Lister;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class ListerRepositoryTest {

    @Autowired
    private ListerRepository listerRepository;

    @Test
    public void repositoryTest() {
        Lister lister = new Lister();
        lister.setFullName("Kim Bauer");
        lister.setEmail("kim@email.com");
        lister.setLocation("Sabo");
        lister.setPhoneNumber("1234567890");

        Lister savedLister = listerRepository.save(lister);
        assertEquals("Kim Bauer", savedLister.getFullName());
        assertNotNull(savedLister.getId());
    }
}