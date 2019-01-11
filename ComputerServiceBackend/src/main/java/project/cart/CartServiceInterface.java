package project.cart;

import java.math.BigDecimal;
import java.util.List;

public interface CartServiceInterface {
	List<CartElementDTO> getCart(String username);

	void addToCart(String username, int productId, int amount) throws CartException;

	void clear(String username) throws CartException;

	BigDecimal getPrice(String username) throws CartException;
}
