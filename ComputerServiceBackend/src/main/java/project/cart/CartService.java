package project.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService implements CartServiceInterface {

	@Autowired
	private CartRepositoryInterface cartRepository;

	@Override
	public List<CartElementDTO> getCart(String username) {
		return cartRepository.getUserCart(username);
	}

}
