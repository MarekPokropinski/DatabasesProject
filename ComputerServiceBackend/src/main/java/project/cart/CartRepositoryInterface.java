package project.cart;

import java.util.List;

public interface CartRepositoryInterface {
	List<CartElementDTO> getUserCart(String username);
}
