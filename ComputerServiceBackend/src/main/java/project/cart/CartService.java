package project.cart;

import java.math.BigDecimal;
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
		int newAmount = oldAmount + amount;
		if (oldAmount == 0) {
			cartRepository.addToCart(userId, productId, newAmount);
		} else if (newAmount != 0) {
			cartRepository.updateCart(userId, productId, newAmount);
		} else {
			cartRepository.removeFromCart(userId, productId);
		}
	}

	@Override
	public void clear(String username) throws CartException {
		int userId = userRepository.getUser(username).orElseThrow(CartException::new).getId();
		cartRepository.clear(userId);
	}

	@Override
	public BigDecimal getPrice(String username) throws CartException {
		int userId = userRepository.getUser(username).orElseThrow(CartException::new).getId();
		return cartRepository.getPrice(userId);
	}
}
