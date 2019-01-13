package project;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import project.cart.CartException;
import project.cart.CartRepositoryInterface;
import project.cart.CartServiceInterface;
import project.user.User;
import project.user.UserRepositoryInterface;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartServiceTests {
	@Autowired
	private CartServiceInterface cartService;
	@MockBean
	private CartRepositoryInterface cartRepository;
	@MockBean
	private UserRepositoryInterface userRepository;

	@Test
	public void addOneToEmptyCart() throws CartException {
		given(cartRepository.getProductAmount(1, 1)).willReturn(0);
		given(userRepository.getUser("user")).willReturn(
				Optional.of(new User(1, "user")));
		cartService.addToCart("user", 1, 1);
		verify(cartRepository).addToCart(1, 1, 1);
	}

	@Test
	public void addOneToExistingCart() throws CartException {
		given(cartRepository.getProductAmount(1, 1)).willReturn(10);
		given(userRepository.getUser("user")).willReturn(
				Optional.of(new User(1, "user")));
		cartService.addToCart("user", 1, 1);
		verify(cartRepository).updateCart(1, 1, 11);
	}

	@Test
	public void removeOneFromCartWithOne() throws CartException {
		given(cartRepository.getProductAmount(1, 1)).willReturn(1);
		given(userRepository.getUser("user")).willReturn(
				Optional.of(new User(1, "user")));
		cartService.addToCart("user", 1, -1);
		verify(cartRepository).removeFromCart(1, 1);
	}

	@Test
	public void removeOneFromCartWithTen() throws CartException {
		given(cartRepository.getProductAmount(1, 1)).willReturn(10);
		given(userRepository.getUser("user")).willReturn(
				Optional.of(new User(1, "user")));
		cartService.addToCart("user", 1, -1);
		verify(cartRepository).updateCart(1, 1, 9);
	}

	@Test
	public void shouldReturnPrice() throws CartException {
		for (int i = 0; i < 1000; i++) {
			given(cartRepository.getPrice(1)).willReturn(new BigDecimal(i));
			given(userRepository.getUser("user")).willReturn(
					Optional.of(new User(1, "user")));
			BigDecimal price = cartService.getPrice("user");
			assertEquals(price, new BigDecimal(i));
		}
	}

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Test
	public void addToWrongUser() throws CartException {
		given(userRepository.getUser("user")).willReturn(Optional.empty());
		
		exception.expect(CartException.class);
		cartService.addToCart("user", 1, 1);
	}
	
	@Test
	public void removeFromEmptyCart() throws CartException {
		given(cartRepository.getProductAmount(1, 1)).willReturn(0);
		given(userRepository.getUser("user")).willReturn(
				Optional.of(new User(1, "user")));
		exception.expect(CartException.class);
		cartService.addToCart("user", 1, -1);
	}
}
