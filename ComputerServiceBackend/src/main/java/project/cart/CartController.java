package project.cart;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartServiceInterface cartService;

	@GetMapping("/get")
	public List<CartElementDTO> getCart(Principal user) {
		return cartService.getCart(user.getName());
	}

	@PutMapping("/add")
	public void addToCart(Principal user, @RequestParam int productId, @RequestParam int amount) throws CartException {
		cartService.addToCart(user.getName(), productId, amount);
	}
}