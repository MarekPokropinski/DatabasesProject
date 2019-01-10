package project.Purchase;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.cart.CartElementDTO;
import project.cart.CartException;
import project.cart.CartServiceInterface;
import project.user.UserRepositoryInterface;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

	@Autowired
	private PurchaseRepositoryInterface purchaseRepository;
	@Autowired
	private UserRepositoryInterface userRepository;
	@Autowired
	private CartServiceInterface cartService;

	@GetMapping("/getPurchases")
	public List<Purchase> getPurchases(Principal user) {
		int userId = userRepository.getUser(user.getName()).get().getId();
		return purchaseRepository.getPurchases(userId);
	}

	@PostMapping("/createPurchase")
	public void createPurchase(Principal user) throws CartException {
		List<CartElementDTO> cart = cartService.getCart(user.getName());
		Purchase purchase = new Purchase();
		int userId = userRepository.getUser(user.getName()).get().getId();
		purchase.setProducts(cart);
		purchase.setStatus("nowa");
		purchase.setUserId(userId);
		purchaseRepository.addPurchase(purchase);
		cartService.clear(user.getName());
	}

}
