package project.cart;

import java.util.List;

public interface CartRepositoryInterface {
	List<CartElementDTO> getUserCart(String username);

	void addToCart(int userId, int productId, int amount);

	int getProductAmount(int userId, int productId);

	void updateCart(int userId, int productId, int amount);

	void removeFromCart(int userId, int productId);
}
