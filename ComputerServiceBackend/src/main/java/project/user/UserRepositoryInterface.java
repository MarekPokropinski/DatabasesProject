package project.user;

import java.util.Optional;

public interface UserRepositoryInterface {

	void addUser(User user) throws UserException;

	Optional<User> getUser(String username);
}
