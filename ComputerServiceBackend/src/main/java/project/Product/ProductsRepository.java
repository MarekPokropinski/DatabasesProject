package project.Product;

import java.util.List;
import java.util.Optional;

public interface ProductsRepository {
	Optional<Product> findById(int id);

	Optional<Product> findByName(String name);

	List<Product> findByNameQuery(String query);

	List<Product> getProductsFromCategory(int categoryId);
}
