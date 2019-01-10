package project.cart;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
