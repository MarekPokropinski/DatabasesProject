package project.Product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
	private ProductsRepository productsRepository;

	@Autowired
	public ProductServiceImpl(ProductsRepository productsRepository) {
		this.productsRepository = productsRepository;
	}

	@Override
	public List<Product> findByName(String name) {
		return productsRepository.findByNameQuery(name);
	}

	@Override
	public Product findById(int id) throws ProductNotFoundException {
		return productsRepository.findById(id).orElseThrow(ProductNotFoundException::new);
	}

}
