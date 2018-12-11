package project.Category;

public class Category {
	private int id;
	private Integer parent_category_id;
	private String name;

	public Category() {
	}

	public Category(int id, Integer parent_category_id, String name) {
		this.id = id;
		this.parent_category_id = parent_category_id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getParent_category_id() {
		return parent_category_id;
	}

	public void setParent_category_id(Integer parent_category_id) {
		this.parent_category_id = parent_category_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
