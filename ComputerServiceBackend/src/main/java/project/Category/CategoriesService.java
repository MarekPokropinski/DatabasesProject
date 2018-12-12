package project.Category;

import java.util.List;

public interface CategoriesService {
	List<Category> getRootCategories();

	List<Category> getChildrenOfCategory(int id);
}
