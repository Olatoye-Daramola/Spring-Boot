package africa.semicolon.phoenix.web.controllers;

import africa.semicolon.phoenix.data.dto.CartRequestDto;
import africa.semicolon.phoenix.data.dto.CartResponseDto;
import africa.semicolon.phoenix.service.cart.CartService;
import africa.semicolon.phoenix.web.exceptions.BusinessLogicException;
import africa.semicolon.phoenix.web.exceptions.ProductNotFoundException;
import africa.semicolon.phoenix.web.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
public class CartRestController {

    @Autowired
    CartService cartService;

    @PostMapping
    public ResponseEntity<?> addItemToCart(@RequestBody CartRequestDto request) {
        CartResponseDto response = null;
        try {
            response = cartService.addItemToCart(request);
        } catch (UserNotFoundException | BusinessLogicException | ProductNotFoundException e) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
