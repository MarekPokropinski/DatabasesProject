package project.Product;

import java.util.List;

public interface ProductService {
	List<Product> findByName(String name);

	Product findById(int id) throws ProductNotFoundException;

	List<Product> findByCategory(int categoryId);
}
