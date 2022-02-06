package africa.semicolon.phoenix.service.product;

import africa.semicolon.phoenix.data.dto.ProductDto;
import africa.semicolon.phoenix.data.models.Product;
import africa.semicolon.phoenix.web.exceptions.BusinessLogicException;
import africa.semicolon.phoenix.web.exceptions.ProductDoesNotExistException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();
    Product findProductById(Long productId) throws ProductDoesNotExistException;
    Product createProduct(ProductDto productDto) throws BusinessLogicException;
//    Product updateProductById(Long productId, ProductDto productDto) throws ProductDoesNotExistException;
    Product saveOrUpdate(Product product) throws BusinessLogicException;
    Product updateProductDetails(Long productId, JsonPatch patch)
            throws ProductDoesNotExistException, BusinessLogicException, JsonPatchException, JsonProcessingException;
}
