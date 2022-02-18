package africa.semicolon.phoenix.data.repositories;

import africa.semicolon.phoenix.data.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
