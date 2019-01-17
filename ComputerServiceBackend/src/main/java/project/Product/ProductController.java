package project.Product;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/find")
    public List<ProductDTO> findProduct(@RequestParam String productName) throws ProductNotFoundException {
        return productService.findByName(productName);
    }

    @GetMapping("/get")
    public ProductDTO getProduct(@RequestParam int id) throws ProductNotFoundException {
        return productService.findById(id);
    }

    @GetMapping("/fromCategory")
    public List<ProductDTO> getProductsFromCategory(@RequestParam int categoryId) {
        return productService.findByCategory(categoryId);
    }

    @PostMapping("/add")
    public void createNewProduct(@RequestBody ProductDTO product){
        productService.createProduct(product);
    }
}