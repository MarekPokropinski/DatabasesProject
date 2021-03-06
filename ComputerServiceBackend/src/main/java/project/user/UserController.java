package project.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserController {
	private UserRepositoryInterface userRepository;

	@Autowired
	public void setUserRepository(UserRepositoryInterface userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping("/get")
	User getUser(@RequestParam String username) throws UserException {
		return userRepository.getUser(username).orElseThrow(() -> new UserException("User doesn't exist"));
	}

	@PostMapping("/register")
	void registerUser(Principal principal, @RequestBody User user) throws UserException {
		if(principal==null) {
			user.autority = "user";
		}
		if(user.password.length()<4) {
			throw new UserException("Password too short");
		}
		userRepository.addUser(user);
	}
}