package project.Purchase;

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
import project.cart.CartElementDTO;

@Repository
public class PurchaseRepository implements PurchaseRepositoryInterface {

	private static final String findPurchasesByUser = "select id, status from purchases where users_id=?";
	private static final String findProductsInPurchase = "select products.id, category_id, products.name, description, price, amount from purchses_has_products join products on(products.id=purchses_has_products.products_id) where purchases_id=?";
	private static final String getLastPurchase = "select max(id) from purchases where users_id=?";
	private static final String createPurchase = "insert into purchases(status,users_id) values(?,?)";
	private static final String addProductsToPurchase = "insert into purchses_has_products(purchases_id,products_id,amount) values(?,?,?)";
	private static final Logger LOG = Logger.getLogger(PurchaseRepository.class);

	@Autowired
	private DataSource dataSource;

	@Override
	public List<Purchase> getPurchases(int userId) {
		List<Purchase> purchases = new ArrayList<>();
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(findPurchasesByUser);
			statement.setInt(1, userId);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Purchase purchase = new Purchase();
				purchase.setId(resultSet.getInt(1));
				purchase.setStatus(resultSet.getString(2));
				purchases.add(purchase);
			}

			for (Purchase purchase : purchases) {
				PreparedStatement statement2 = connection.prepareStatement(findProductsInPurchase);
				statement2.setInt(1, purchase.getId());
				List<CartElementDTO> products = new ArrayList<>();
				ResultSet resultSet2 = statement2.executeQuery();
				while (resultSet2.next()) {
					ProductDTO prod = new ProductDTO(resultSet2.getInt("products.id"), resultSet2.getInt("category_id"),
							resultSet2.getString("products.name"), resultSet2.getString("description"),
							resultSet2.getBigDecimal("price"));
					CartElementDTO cartElement = new CartElementDTO(prod, resultSet2.getInt("amount"));
					products.add(cartElement);
				}
				purchase.setProducts(products);
			}
			connection.close();
		} catch (SQLException e) {
			LOG.warn(e.getMessage());
		}
		return purchases;
	}

	@Override
	public void addPurchase(Purchase purchase) {
		Connection connection;
		try {
			connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(createPurchase);
			statement.setString(1, purchase.getStatus());
			statement.setInt(2, purchase.getUserId());
			statement.executeUpdate();
			statement = connection.prepareStatement(getLastPurchase);
			statement.setInt(1, purchase.getUserId());
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			int purchaseId = resultSet.getInt(1);
			for (CartElementDTO element : purchase.getProducts()) {
				statement = connection.prepareStatement(addProductsToPurchase);
				statement.setInt(1, purchaseId);
				statement.setInt(2, element.getProduct().getId());
				statement.setInt(3, element.getAmount());
				statement.executeUpdate();
			}
			connection.close();
		} catch (SQLException e) {
			LOG.warn(e.getMessage());
		}

	}

}
