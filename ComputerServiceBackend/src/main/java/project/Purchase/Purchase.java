package project.Purchase;

import java.util.List;

import project.cart.CartElementDTO;

public class Purchase {
	private int id;
	private String status;
	private int userId;
	List<CartElementDTO> products;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public List<CartElementDTO> getProducts() {
		return products;
	}

	public void setProducts(List<CartElementDTO> products) {
		this.products = products;
	}

}
