package project.Category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriesServiceImpl implements CategoriesService {

	private CategoriesRepository categoriesRepository;

	@Autowired
	public CategoriesServiceImpl(CategoriesRepository categoriesRepository) {
		this.categoriesRepository = categoriesRepository;
	}

	@Override
	public List<Category> getRootCategories() {
		return categoriesRepository.findRootCategories();
	}

	@Override
	public List<Category> getChildrenOfCategory(int id) {
		return categoriesRepository.findChildren(id);
	}

}
