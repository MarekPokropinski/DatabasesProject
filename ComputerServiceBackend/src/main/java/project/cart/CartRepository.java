package project.cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.Product.ProductDTO;

@Repository
public class CartRepository implements CartRepositoryInterface {

	private static final String removeFromCart = "delete from carts where productsId=? and usersId=?";
	private static final String addToCart = "insert into carts (usersId,productsId,amount) values (?,?,?)";
	private static final String updateCart = "update carts set amount = ? where productsId=? and usersId=?";
	private static final String findElementByProductId = "select amount from carts where productsId=? and usersId=?";
	private static final String findCartByUsername = "select products.id, category_id, products.name, description, price, carts.productsId, carts.amount from carts join users on (carts.usersId=users.id) join products on(carts.productsId=products.id) where users.username=?";
	private static final Logger LOG = Logger.getLogger(CartRepositoryInterface.class);

	@Autowired
	private DataSource dataSource;

	private CartElementDTO createCartFromResultSet(ResultSet resultSet) throws SQLException {
		CartElementDTO cart = new CartElementDTO();
		ProductDTO prod = new ProductDTO(resultSet.getInt("products.id"), resultSet.getInt("category_id"),
				resultSet.getString("products.name"), resultSet.getString("description"),
				resultSet.getBigDecimal("price"));

		cart.setProduct(prod);
		cart.setAmount(resultSet.getInt("amount"));
		return cart;
	}

	@Override
	public List<CartElementDTO> getUserCart(String username) {
		List<CartElementDTO> elements = new ArrayList<>();
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(findCartByUsername);
			statement.setString(1, username);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				elements.add(createCartFromResultSet(resultSet));
			}
			connection.close();
		} catch (SQLException e) {
			LOG.warn(e.getMessage());
		}
		return elements;
	}

	@Override
	public void addToCart(int userId, int productId, int amount) {
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(addToCart);
			statement.setInt(1, userId);
			statement.setInt(2, productId);
			statement.setInt(3, amount);
			statement.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			LOG.warn(e.getMessage());
		}
	}

	@Override
	public int getProductAmount(int userId, int productId) {
		int amount = 0;
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(findElementByProductId);
			statement.setInt(1, productId);
			statement.setInt(2, userId);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			amount = resultSet.getInt(1);
			connection.close();
		} catch (SQLException e) {
			LOG.warn(e.getMessage());
		}
		return amount;
	}

	@Override
	public void updateCart(int userId, int productId, int amount) {
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(updateCart);
			statement.setInt(1, amount);
			statement.setInt(2, productId);
			statement.setInt(3, userId);

			statement.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			LOG.warn(e.getMessage());
		}
	}

	@Override
	public void removeFromCart(int userId, int productId) {
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(removeFromCart);
			statement.setInt(2, userId);
			statement.setInt(1, productId);
			statement.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			LOG.warn(e.getMessage());
		}
	}

}
