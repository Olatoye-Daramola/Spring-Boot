package africa.semicolon.phoenix.service.product;

import africa.semicolon.phoenix.data.dto.ProductDto;
import africa.semicolon.phoenix.data.models.Product;
import africa.semicolon.phoenix.data.repositories.ProductRepository;
import africa.semicolon.phoenix.service.cloud.CloudService;
import africa.semicolon.phoenix.web.exceptions.BusinessLogicException;
import africa.semicolon.phoenix.web.exceptions.ProductDoesNotExistException;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CloudService cloudService;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product findProductById(Long productId) throws ProductDoesNotExistException {
        if(productId == null) throw new IllegalArgumentException("ID cannot be null");

        Optional<Product> queryResult = productRepository.findById(productId);

        if(queryResult.isPresent()) return queryResult.get();
        throw new ProductDoesNotExistException("Product with ID : " + productId + " does not exist");
    }

    @Override
    public Product createProduct(ProductDto productDto) throws BusinessLogicException, IOException {
        if (productDto == null) throw new IllegalArgumentException("Argument cannot be null");

        Optional<Product> queryResult = productRepository.findByName(productDto.getName());
        if (queryResult.isPresent()) throw new BusinessLogicException("Product with name already exists!");

        Product product = new Product();

        try {
            if(productDto.getImage()!=null) {
                Map<?, ?> uploadResult = cloudService.upload(productDto.getImage().getBytes(),
                        ObjectUtils.asMap(
                                "public_id", "inventory/" + productDto.getImage().getOriginalFilename(),
                                "overwrite", true
                        ));
                product.setImageUrl(uploadResult.get("url").toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setDescription(productDto.getDescription());

        return productRepository.save(product);
    }

    @Override
    public Product saveOrUpdate(Product product) throws BusinessLogicException {
        if(product == null) throw new BusinessLogicException("Product cannot be null");
        return productRepository.save(product);
    }

//    @Override
//    public Product updateProductById(Long productId, ProductDto productDto) throws ProductDoesNotExistException {
//        if(productId == null) throw new IllegalArgumentException("ID cannot be null");
//
//        Optional<Product> queryResult = productRepository.findById(productId);
//        if(queryResult.isEmpty())
//            throw new ProductDoesNotExistException("Product with ID : " + productId + " does not exist");
//
//        queryResult.get().setName(productDto.getName());
//        queryResult.get().setPrice(productDto.getPrice());
//        queryResult.get().setQuantity(productDto.getQuantity());
//        queryResult.get().setDescription(productDto.getDescription());
//
//        return productRepository.save(queryResult.get());
//    }

    @Override
    public Product updateProductDetails(Long productId, JsonPatch productPatch)
            throws ProductDoesNotExistException, BusinessLogicException, JsonPatchException, JsonProcessingException {
        if(productId == null) throw new IllegalArgumentException("ID cannot be null");

        Optional<Product> productQuery = productRepository.findById(productId);
        if(productQuery.isEmpty())
            throw new ProductDoesNotExistException("Product with ID : " + productId + " does not exist");

        Product targetProduct = productQuery.get();
        try {
            targetProduct = applyPatchToProduct(productPatch, targetProduct);
            return saveOrUpdate(targetProduct);
        } catch (JsonPatchException | JsonProcessingException je) {
            throw new BusinessLogicException("Update failed");
        }
    }

//    @Override
//    public String retrieveCloudinaryUrl(String filePath) throws IOException {
//        File imageFile = new File(filePath);
//        Map<?,?> cloudinaryResponse = cloudService.upload(Files.readAllBytes(imageFile.toPath()), ObjectUtils.emptyMap());
//        return cloudinaryResponse.get("url").toString();
//    }

    private Product applyPatchToProduct (JsonPatch productPatch, Product targetProduct)
            throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode patched = productPatch.
                apply(objectMapper.convertValue(targetProduct, JsonNode.class));

        return objectMapper.treeToValue(patched, Product.class);
    }
}
