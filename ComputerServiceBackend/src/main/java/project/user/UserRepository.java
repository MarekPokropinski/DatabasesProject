package project.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import javax.sql.DataSource;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository implements UserRepositoryInterface {
	private static final String findUserByUsername = "select id, authority from users where username=?";
	private static final String insertUser = "insert into users (username, password, authority) values (?,?,?)";
	private static final Logger LOG = Logger.getLogger(UserRepository.class);

	private DataSource dataSource;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public UserRepository(PasswordEncoder passwordEncoder, DataSource dataSource) {
		this.passwordEncoder = passwordEncoder;
		this.dataSource = dataSource;

		try {
			addUser(new User("user", "user", "user"));
		} catch (UserException e) {
			LOG.info("default user already exists");
		}
	}

	@Override
	public void addUser(User user) throws UserException {
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(insertUser);
			statement.setString(1, user.username);
			statement.setString(2, passwordEncoder.encode(user.password));
			statement.setString(3, user.autority);
			statement.executeUpdate();

		} catch (SQLException e) {
			LOG.warn(e.getMessage());
			throw new UserException("Username not availible");
		}
	}

	@Override
	public Optional<User> getUser(String username) {
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(findUserByUsername);
			statement.setString(1, username);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			User user = new User();
			user.id = resultSet.getInt("id");
			user.autority = resultSet.getString("authority");
			user.username = username;
			connection.close();
			return Optional.of(user);
		} catch (SQLException e) {
			LOG.warn(e.getMessage());
			return Optional.empty();
		}
	}

}
