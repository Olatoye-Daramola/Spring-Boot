package africa.semicolon.phoenix.data.repositories;

import africa.semicolon.phoenix.data.models.Cart;
import africa.semicolon.phoenix.data.models.Item;
import africa.semicolon.phoenix.data.models.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(scripts={"/db/insert.sql"})
@Slf4j
class CartRepositoryTest {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {

    }

    @Test
    void addProductToCart() {
        Product product = productRepository.findByName("Macbook Air").orElse(null);
        assertThat(product).isNotNull();

        Item item = new Item(product, 2);
        Cart cart = new Cart();
        cart.addItem(item);

        cartRepository.save(cart);
        assertThat(cart.getId()).isNotNull();
        log.info("Cart -> {}", cart);
    }

    @Test
    @Transactional
    void viewItemsInCartTest() {
        Cart savedCart = cartRepository.findById(345L).orElse(null);
        assertThat(savedCart).isNotNull();
        assertThat(savedCart.getItemList().size()).isEqualTo(3);
        log.info("Cart retrieved from DB -> {}", savedCart);
    }
}