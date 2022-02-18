package africa.semicolon.phoenix.data.repositories;

import africa.semicolon.phoenix.data.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
}
