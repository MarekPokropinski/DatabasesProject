package project.cart;

import java.util.List;

public interface CartServiceInterface {
	List<CartElementDTO> getCart(String username);
}
