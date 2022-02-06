package africa.semicolon.phoenix.service.product;

import africa.semicolon.phoenix.data.models.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class ProductServiceImplTest {

    @Test
    void applyPatchToProductTest() {
        Product product = new Product();
        product.setName("Table top");
        product.setPrice(450);
        product.setDescription("Fanciful table top");
        product.setQuantity(3);


    }

}