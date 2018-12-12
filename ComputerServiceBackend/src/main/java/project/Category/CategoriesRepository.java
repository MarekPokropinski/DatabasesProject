package project.Category;

import java.util.List;
import java.util.Optional;

public interface CategoriesRepository {
	Optional<Category> findById(int id);

	Optional<Category> findByName(String name);

	List<Category> findChildren(int parentId);

	List<Category> findRootCategories();
}