package project.Category;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Category not found")
public class CategoryNotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1032138586181790315L;
	private int id;

	public CategoryNotFoundException(int id) {
		super();
		this.id = id;
	}

	@Override
	public String getMessage() {
		return String.format("Category with id: %d not found", id);
	}
}
