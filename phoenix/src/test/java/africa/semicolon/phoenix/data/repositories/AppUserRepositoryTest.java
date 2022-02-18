package africa.semicolon.phoenix.data.repositories;

import africa.semicolon.phoenix.data.models.AppUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(scripts={"/db/insert.sql"})
@Slf4j
class AppUserRepositoryTest {

    @Autowired
    AppUserRepository appUserRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void whenUserIsCreated_ThenCreateCartTest() {
        AppUser appUser = new AppUser();
        appUser.setFirstName("John");
        appUser.setLastName("Badmus");
        appUser.setEmail("johndoe@yupmail.com");
        appUser.setAddress("123 Peace road");

        appUserRepository.save(appUser);

        assertThat(appUser.getId()).isNotNull();
        assertThat(appUser.getMyCart()).isNotNull();

        log.info("App user created -> {}", appUser);
    }
}