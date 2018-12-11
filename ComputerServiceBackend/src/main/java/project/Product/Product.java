package project.Product;

import java.math.BigDecimal;

import project.Category.Category;

public class Product {
	private int id;
	private Category category;
	private String name;
	private String description;
	private BigDecimal price;

	public Product() {
	}

	public Product(int id, Category category, String name, String description, BigDecimal price) {
		this.id = id;
		this.category = category;
		this.name = name;
		this.price = price;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
