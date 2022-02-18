package africa.semicolon.phoenix.service.cart;

import africa.semicolon.phoenix.data.dto.CartRequestDto;
import africa.semicolon.phoenix.data.dto.CartResponseDto;
import africa.semicolon.phoenix.data.dto.CartUpdateDto;
import africa.semicolon.phoenix.data.models.Cart;
import africa.semicolon.phoenix.web.exceptions.BusinessLogicException;
import africa.semicolon.phoenix.web.exceptions.ProductNotFoundException;
import africa.semicolon.phoenix.web.exceptions.UserNotFoundException;
import com.github.fge.jsonpatch.JsonPatch;

public interface CartService {
    CartResponseDto addItemToCart(CartRequestDto cart) throws UserNotFoundException, ProductNotFoundException, BusinessLogicException;
    Cart viewCart();
    CartResponseDto updateCartItem(CartUpdateDto updateDto) throws UserNotFoundException, BusinessLogicException;
}
