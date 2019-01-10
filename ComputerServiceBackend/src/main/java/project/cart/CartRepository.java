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

}
