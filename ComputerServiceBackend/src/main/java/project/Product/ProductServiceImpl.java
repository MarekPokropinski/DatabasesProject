package project.Product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.Category.CategoriesService;
import project.Category.Category;

@Service
public class ProductServiceImpl implements ProductService {
	private ProductsRepository productsRepository;
	private CategoriesService categoriesService;

	@Autowired
	public ProductServiceImpl(ProductsRepository productsRepository, CategoriesService categoriesService) {
		this.productsRepository = productsRepository;
		this.categoriesService = categoriesService;
	}

	@Override
	public List<Product> findByName(String name) {
		return productsRepository.findByNameQuery(name);
	}

	@Override
	public Product findById(int id) throws ProductNotFoundException {
		return productsRepository.findById(id).orElseThrow(ProductNotFoundException::new);
	}

	@Override
	public List<Product> findByCategory(int categoryId) {
		List<Product> products = productsRepository.getProductsFromCategory(categoryId);
		for (Category category : categoriesService.getChildrenOfCategory(categoryId)) {
			List<Product> childProducts = findByCategory(category.getId());
			products.addAll(childProducts);
		}
		return products;
	}

}
