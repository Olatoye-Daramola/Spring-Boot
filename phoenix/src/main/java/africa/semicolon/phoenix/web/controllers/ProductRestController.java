package africa.semicolon.phoenix.web.controllers;

import africa.semicolon.phoenix.data.dto.ProductDto;
import africa.semicolon.phoenix.data.models.Product;
import africa.semicolon.phoenix.service.product.ProductService;
import africa.semicolon.phoenix.web.exceptions.BusinessLogicException;
import africa.semicolon.phoenix.web.exceptions.ProductDoesNotExistException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductRestController {

    @Autowired
    ProductService productService;

    @GetMapping()
    public ResponseEntity<?> findAllProducts() {
        List<Product> productList = productService.getAllProducts();
        return ResponseEntity.ok().body(productList);
    }

    @PostMapping(consumes={"multipart/form-data"})
    public ResponseEntity<?> createProduct(@ModelAttribute ProductDto productDto) {

        try {
            Product savedProduct = productService.createProduct(productDto);
            return ResponseEntity.ok().body(savedProduct);
        } catch (IllegalArgumentException | BusinessLogicException | IOException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
//        try {
//            Product updatedProduct = productService.updateProductById(id, productDto);
//            return ResponseEntity.ok().body(updatedProduct);
//        }  catch (IllegalArgumentException | ProductDoesNotExistException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }

    @PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody JsonPatch patch) {
        try {
            Product updatedProduct = productService.updateProductDetails(id, patch);
            return ResponseEntity.ok().body(updatedProduct);
        }
        catch (BusinessLogicException | JsonPatchException | JsonProcessingException | ProductDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
