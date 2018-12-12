package project.Category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoriesController {
	private CategoriesService categoriesService;

	@Autowired
	public CategoriesController(CategoriesService categoriesService) {
		this.categoriesService = categoriesService;
	}

	@GetMapping("root")
	List<Category> getRootCategories() {
		return categoriesService.getRootCategories();
	}

	@GetMapping("category")
	List<Category> getChildrenCategories(@RequestParam int parentId) {
		return categoriesService.getChildrenOfCategory(parentId);
	}
}
