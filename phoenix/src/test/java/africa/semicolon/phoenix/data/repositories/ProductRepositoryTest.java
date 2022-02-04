package africa.semicolon.phoenix.data.repositories;

import africa.semicolon.phoenix.data.models.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
@Sql(scripts={"/db/insert.sql"})
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {

    }

    @Test
    @DisplayName("Save a new product to the database")
    void saveProductToDatabaseTest() {
        Product product = new Product();
        product.setName("Bamboo chair");
        product.setDescription("World class bamboo");
        product.setPrice(12345);
        product.setQuantity(9);

        assertThat(product.getId()).isNull();

        productRepository.save(product);
        log.info("Product saved -> {}", product);
        assertThat(product.getId()).isNotNull();
        assertThat(product.getName()).isEqualTo("Bamboo chair");
        assertThat(product.getPrice()).isEqualTo(12345);
        assertThat(product.getDateCreated()).isNotNull();
    }

    @Test
    @DisplayName("Find product by Id")
    void findProductFromDatabaseTest() {
        Product product = productRepository.findById(12L).orElse(null);
        assertThat(product).isNotNull();
        assertThat(product.getId()).isNotNull();
        assertThat(product.getName()).isEqualTo("Luxury Map");
        assertThat(product.getPrice()).isEqualTo(2340);
        assertThat(product.getQuantity()).isEqualTo(3);
    }

    @Test
    @DisplayName("Find all products in the database")
    void findAllProductsTest() {
        List<Product> productList = productRepository.findAll();
        assertThat(productList).isNotNull();
        assertThat(productList.size()).isEqualTo(4);
    }

    @Test
    @DisplayName("Find product by name")
    void findProductByNameTest() {
        Product product = productRepository.findByName("Luxury Map").orElse(null);

        assertThat(product).isNotNull();
        assertThat(product.getId()).isNotNull();
        assertThat(product.getName()).isEqualTo("Luxury Map");
        assertThat(product.getPrice()).isEqualTo(2340);
        assertThat(product.getQuantity()).isEqualTo(3);
    }
}