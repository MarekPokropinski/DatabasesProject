package project.Product;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
		return productService.findByName(productName).stream().map((product) -> this.convertToDTO(product))
				.collect(Collectors.toList());
	}

	@GetMapping("/get")
	public ProductDTO getProduct(@RequestParam int id) throws ProductNotFoundException {
		return convertToDTO(productService.findById(id));
	}

	@GetMapping("/fromCategory")
	public List<ProductDTO> getProductsFromCategory(@RequestParam int categoryId) {
		return productService.findByCategory(categoryId).stream().map((product) -> this.convertToDTO(product))
				.collect(Collectors.toList());
	}

	private ProductDTO convertToDTO(Product product) {
		return new ProductDTO(product.getId(), product.getCategory().getId(), product.getName(),
				product.getDescription(), product.getPrice());
	}
}
