package project.Product;

import java.util.List;

public interface ProductService {
	List<ProductDTO> findByName(String name);

	ProductDTO findById(int id) throws ProductNotFoundException;

	List<ProductDTO> findByCategory(int categoryId);

	void createProduct(ProductDTO product);
}
