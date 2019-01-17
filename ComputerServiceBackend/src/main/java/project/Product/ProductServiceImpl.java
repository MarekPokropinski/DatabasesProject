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
	public List<ProductDTO> findByName(String name) {
		return productsRepository.findByNameQuery(name);
	}

	@Override
	public ProductDTO findById(int id) throws ProductNotFoundException {
		return productsRepository.findById(id).orElseThrow(ProductNotFoundException::new);
	}

	@Override
	public List<ProductDTO> findByCategory(int categoryId) {
		List<ProductDTO> products = productsRepository.getProductsFromCategory(categoryId);
		for (Category category : categoriesService.getChildrenOfCategory(categoryId)) {
			List<ProductDTO> childProducts = findByCategory(category.getId());
			products.addAll(childProducts);
		}
		return products;
	}
	@Override
	public void createProduct(ProductDTO product){
		productsRepository.createProduct(product);
	}

}
