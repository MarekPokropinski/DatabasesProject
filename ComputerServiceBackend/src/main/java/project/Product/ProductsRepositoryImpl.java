package project.Product;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.Category.CategoriesRepository;
import project.Category.Category;
import project.Category.CategoryNotFoundException;

@Repository
public class ProductsRepositoryImpl implements ProductsRepository {
	private static final String findByIdQuery = "select id, category_id, name, description, price from products where id=?";
	private static final String findByNameQuery = "select id, category_id, name, description, price from products where name=?";
	private static final String findByNameLikeQuery = "select id, category_id, name, description, price from products where name like CONCAT(\'%\',?,\'%\')";
	private static final Logger LOG = Logger.getLogger(ProductsRepository.class);

	@Autowired
	private DataSource dataSource;

	@Autowired
	private CategoriesRepository categoriesRepository;

	private Product createProductFromResultSet(ResultSet resultSet) throws SQLException, CategoryNotFoundException {
		Product product = new Product();
		product.setId(resultSet.getInt("id"));
		int categoryId = resultSet.getInt("category_id");
		Category category = categoriesRepository.findById(categoryId)
				.orElseThrow(() -> new CategoryNotFoundException(categoryId));
		product.setCategory(category);
		String priceStr = resultSet.getString("price");
		BigDecimal price = new BigDecimal(priceStr);
		product.setPrice(price);
		product.setName(resultSet.getString("name"));
		product.setDescription(resultSet.getString("description"));
		return product;
	}

	@Override
	public Optional<Product> findById(int id) {
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(findByIdQuery);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			Product product = createProductFromResultSet(resultSet);
			connection.close();
			return Optional.of(product);
		} catch (SQLException e) {
			LOG.warn(e.getMessage());
			return Optional.empty();
		} catch (CategoryNotFoundException e) {
			LOG.warn("product belongs to an category that does not exist");
			LOG.warn(e.getMessage());
			return Optional.empty();
		}
	}

	@Override
	public Optional<Product> findByName(String name) {
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(findByNameQuery);
			statement.setString(1, name);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			Product product = createProductFromResultSet(resultSet);
			connection.close();
			return Optional.of(product);
		} catch (SQLException e) {
			LOG.warn(e.getMessage());
			return Optional.empty();
		} catch (CategoryNotFoundException e) {
			LOG.warn("product belongs to an category that does not exist");
			LOG.warn(e.getMessage());
			return Optional.empty();
		}
	}

	@Override
	public List<Product> findByNameQuery(String query) {
		List<Product> products = new ArrayList<>();
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(findByNameLikeQuery);
			statement.setString(1, query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				products.add(createProductFromResultSet(resultSet));
			}
			connection.close();
		} catch (SQLException e) {
			LOG.warn(e.getMessage());
		} catch (CategoryNotFoundException e) {
			LOG.warn("product belongs to an category that does not exist");
			LOG.warn(e.getMessage());
		}
		return products;
	}

}
