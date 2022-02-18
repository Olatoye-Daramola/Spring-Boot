package africa.semicolon.phoenix.service.cart;

import africa.semicolon.phoenix.data.dto.CartRequestDto;
import africa.semicolon.phoenix.data.dto.CartResponseDto;
import africa.semicolon.phoenix.data.dto.CartUpdateDto;
import africa.semicolon.phoenix.data.dto.QuantityOperation;
import africa.semicolon.phoenix.data.models.AppUser;
import africa.semicolon.phoenix.data.models.Cart;
import africa.semicolon.phoenix.data.models.Item;
import africa.semicolon.phoenix.data.models.Product;
import africa.semicolon.phoenix.data.repositories.AppUserRepository;
import africa.semicolon.phoenix.data.repositories.CartRepository;
import africa.semicolon.phoenix.data.repositories.ProductRepository;
import africa.semicolon.phoenix.web.exceptions.BusinessLogicException;
import africa.semicolon.phoenix.web.exceptions.ProductNotFoundException;
import africa.semicolon.phoenix.web.exceptions.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Sql(scripts={"/db/insert.sql"})
@Slf4j
class CartServiceImplTest {

    @Autowired
    CartService cartService;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    ProductRepository productRepository;

    CartUpdateDto updateDto;

    @BeforeEach
    void setUp() {
        updateDto = CartUpdateDto.builder()
                .itemId(122L)
                .quantityOp(QuantityOperation.INCREASE)
                .userId(5005L).build();
    }

    @Test
    void addItemToCart() throws UserNotFoundException, BusinessLogicException, ProductNotFoundException {
        CartRequestDto cartRequestDto = new CartRequestDto();
        cartRequestDto.setProductId(13L);
        cartRequestDto.setUserId(5011L);
        cartRequestDto.setQuantity(1);

        CartResponseDto response = cartService.addItemToCart(cartRequestDto);
        assertThat(response.getCartItems()).isNotNull();
        assertThat(response.getCartItems().size()).isEqualTo(1);

//        AppUser existingUser = appUserRepository.findById(cartRequestDto.getUserId()).orElse(null);
//        assertThat(existingUser).isNotNull();
//
//        Cart myCart = existingUser.getMyCart();
//        assertThat(myCart).isNotNull();
//
//        Product product = productRepository.findById(cartRequestDto.getProductId()).orElse(null);
//        assertThat(product).isNotNull();
//        assertThat(product.getQuantity()).isGreaterThanOrEqualTo(cartRequestDto.getQuantity());
//
//        Item cartItem = new Item(product, cartRequestDto.getQuantity());
//        myCart.addItem(cartItem);
//        cartRepository.save(myCart);
//
//        assertThat(myCart.getItemList().size()).isEqualTo(1);
    }

    @Test
    void updateCartPriceTest() throws UserNotFoundException, BusinessLogicException, ProductNotFoundException {
        CartRequestDto cartRequestDto = new CartRequestDto();
        cartRequestDto.setProductId(13L);
        cartRequestDto.setUserId(5011L);
        cartRequestDto.setQuantity(2);

        CartResponseDto response = cartService.addItemToCart(cartRequestDto);
        assertThat(response.getCartItems()).isNotNull();
        assertThat(response.getCartItems().size()).isEqualTo(1);
        assertThat(response.getTotalPrice()).isEqualTo(2680);
    }

    @Test
    void viewCart() {
    }

    @Test
    @DisplayName("Update cart item quantity test")
    void updateCartItemTest() throws UserNotFoundException, BusinessLogicException {

        AppUser appUser = appUserRepository.findById(updateDto.getUserId()).orElse(null);
        assert appUser != null;
        Cart userCart = appUser.getMyCart();
        Item item = userCart.getItemList().get(0);

        log.info("Item -> {}", item);

//        Predicate<Item> itemPredicate = i ->
//                i.getId().equals(updateDto.getItemId());
//
//        Optional<Item> optionalItem =
//                userCart.getItemList().stream()
//                        .filter(itemPredicate).findFirst();
//        get the item
//        for (int i = 0; i < userCart.getItemList().size(); i++) {
//            item = userCart.getItemList().get(i);
//            if (item.getId() == updateDto.getUserId()) break;
//        }

        assertThat(item).isNotNull();
        assertThat(item.getQuantityAddedToCart()).isEqualTo(2);
//        assertThat(userCart.getItemList())

        log.info("Cart update obj -> {}", userCart);

        CartResponseDto responseDto = cartService.updateCartItem(updateDto);

//        for (int i = 0; i < responseDto.getCartItems().size(); i++) {
//            item = responseDto.getCartItems().get(i);
//            if (Objects.equals(item.getId(), updateDto.getUserId())) break;
//        }

        assertThat(responseDto.getCartItems()).isNotNull();
        assertThat(responseDto.getCartItems().get(0)
                .getQuantityAddedToCart()).isEqualTo(2);
    }
}