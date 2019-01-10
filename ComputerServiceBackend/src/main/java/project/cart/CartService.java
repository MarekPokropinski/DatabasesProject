package project.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.user.UserRepositoryInterface;

@Service
public class CartService implements CartServiceInterface {

	@Autowired
	private CartRepositoryInterface cartRepository;

	@Autowired
	private UserRepositoryInterface userRepository;

	@Override
	public List<CartElementDTO> getCart(String username) {
		return cartRepository.getUserCart(username);
	}

	@Override
	public void addToCart(String username, int productId, int amount) throws CartException {
		int userId = userRepository.getUser(username).orElseThrow(CartException::new).getId();
		int oldAmount = cartRepository.getProductAmount(userId, productId);
		System.out.println("Old amount was: " + oldAmount);
		if (oldAmount == 0) {
			cartRepository.addToCart(userId, productId, amount);
		} else if (amount != 0) {
			cartRepository.updateCart(userId, productId, amount);
		} else {
			cartRepository.removeFromCart(userId, productId);
		}
	}
}
