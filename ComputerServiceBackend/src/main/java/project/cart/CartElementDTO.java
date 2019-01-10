package project.cart;

import project.Product.ProductDTO;

public class CartElementDTO {
	private ProductDTO product;
	private int amount;

	public CartElementDTO(ProductDTO product, int amount) {
		this.product = product;
		this.amount = amount;
	}

	public CartElementDTO() {
	}

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
