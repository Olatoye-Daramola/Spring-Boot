package africa.semicolon.phoenix.service.cart;

import africa.semicolon.phoenix.data.dto.CartResponseDto;
import africa.semicolon.phoenix.data.dto.CartRequestDto;
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
import com.github.fge.jsonpatch.JsonPatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Predicate;

import static africa.semicolon.phoenix.data.dto.QuantityOperation.*;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    ProductRepository productRepository;


    @Override
    public CartResponseDto addItemToCart(CartRequestDto cartRequestDto) throws UserNotFoundException, ProductNotFoundException, BusinessLogicException {

        Optional<AppUser> userQuery = appUserRepository.findById(cartRequestDto.getUserId());

        if (userQuery.isEmpty()) throw new UserNotFoundException("User with ID " +
                cartRequestDto.getUserId() + " not found");

        AppUser existingUser = userQuery.get();

        Product product = productRepository.findById(cartRequestDto.getProductId()).orElse(null);
        if (product == null) throw new ProductNotFoundException("Product with ID " +
                cartRequestDto.getProductId() + " not found");

        if (!quantityIsValid(product, cartRequestDto.getQuantity())) {
            throw new BusinessLogicException("Quantity too large");
        }

        Item cartItem = new Item(product, cartRequestDto.getQuantity());

        Cart myCart = existingUser.getMyCart();
        myCart.addItem(cartItem);
        myCart.setTotalPrice(myCart.getTotalPrice() + calculateItemPrice(cartItem));

        cartRepository.save(myCart);

        return buildCartResponse(myCart);
    }

    private Double calculateItemPrice(Item item) {
        return item.getProduct().getPrice() * item.getQuantityAddedToCart();
    }

    private CartResponseDto buildCartResponse(Cart cart) {
        return CartResponseDto.builder()
                .cartItems(cart.getItemList())
                .totalPrice(cart.getTotalPrice())
                .build();
    }

    private boolean quantityIsValid(Product product, int quantity) {
        return product.getQuantity() >= quantity;
    }

    @Override
    public Cart viewCart() {
        return null;
    }

    @Override
    public CartResponseDto updateCartItem(CartUpdateDto updateDto) throws UserNotFoundException, BusinessLogicException {
        AppUser appUser = appUserRepository.findById(updateDto.getUserId()).orElse(null);
        if(appUser == null) throw new UserNotFoundException("User not found");

        // Get a cart by userId
        Cart myCart = appUser.getMyCart();

        // Find an item within cart with itemId
        Item item = findCartItem(updateDto.getItemId(), myCart).orElse(null);
        if (item == null) throw new BusinessLogicException("Item not in cart");

        // Perform update to item
        if(updateDto.getQuantityOp() == INCREASE) {
            item.setQuantityAddedToCart(item.getQuantityAddedToCart() + 1);
            myCart.setTotalPrice(myCart.getTotalPrice() + item.getProduct().getPrice());
        }
        else if(updateDto.getQuantityOp() == DECREASE) {
            item.setQuantityAddedToCart(item.getQuantityAddedToCart() - 1);
            myCart.setTotalPrice(myCart.getTotalPrice() - item.getProduct().getPrice());
        }
        cartRepository.save(myCart);
        return buildCartResponse(myCart);
    }

    private Optional<Item> findCartItem(Long itemId, Cart cart) {
        Predicate<Item> itemPredicate = i -> i.getId().equals(itemId);
        return cart.getItemList().stream().filter(itemPredicate).findFirst();
    }
}
