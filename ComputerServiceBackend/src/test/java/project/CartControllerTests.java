package project;

import static org.mockito.Mockito.verify;

import java.security.Principal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import project.cart.CartController;
import project.cart.CartException;
import project.cart.CartServiceInterface;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartControllerTests {
	@Autowired
	private CartController cartController;
	@MockBean
	private CartServiceInterface cartServiceMock;

	@Test
	public void addOneToEmptyCart() throws CartException {
		Principal user = ()->"user";
		int productId = 22341;
		int amount = 2;
		cartController.addToCart(user, productId, amount);
		verify(cartServiceMock).addToCart("user", productId, amount);
	}	
}