package project.Product;

import java.util.List;
import java.util.Optional;

public interface ProductsRepository {
	Optional<ProductDTO> findById(int id);

	Optional<ProductDTO> findByName(String name);

	List<ProductDTO> findByNameQuery(String query);

	List<ProductDTO> getProductsFromCategory(int categoryId);

	void createProduct(ProductDTO product);
}
