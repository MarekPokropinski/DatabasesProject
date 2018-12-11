package project.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CategoriesRepositoryImpl implements CategoriesRepository {

	private static final String findByIdQuery = "select id, parent_category_id, name from categories where id=?";
	private static final String findByNameQuery = "select id, parent_category_id, name from categories where name=?";
	private static final String findChildrenQuery = "select id, parent_category_id, name from categories where parent_category_id=?";

	@Autowired
	private DataSource dataSource;

	private Category createCategoryFromResultSet(ResultSet resultSet) throws SQLException {
		Category category = new Category();
		category.setId(resultSet.getInt("id"));
		category.setParent_category_id(resultSet.getInt("parent_category_id"));
		category.setName(resultSet.getString("name"));
		return category;
	}

	@Override
	public Optional<Category> findById(int id) {
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(findByIdQuery);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			Category category = createCategoryFromResultSet(resultSet);
			connection.close();
			return Optional.of(category);
		} catch (SQLException e) {
			return Optional.empty();
		}
	}

	@Override
	public Optional<Category> findByName(String name) {
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(findByNameQuery);
			statement.setString(1, name);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			Category category = new Category();
			category.setId(resultSet.getInt("id"));
			category.setParent_category_id(resultSet.getInt("parent_category_id"));
			category.setName(resultSet.getString("name"));
			connection.close();
			return Optional.of(category);
		} catch (SQLException e) {
			return Optional.empty();
		}
	}

	@Override
	public List<Category> findChildren(Category parent) {
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(findChildrenQuery);
			statement.setInt(1, parent.getId());
			ResultSet resultSet = statement.executeQuery();

			List<Category> categories = new ArrayList<>();
			while (resultSet.next()) {
				Category category = createCategoryFromResultSet(resultSet);
				categories.add(category);
			}
			connection.close();
			return categories;
		} catch (SQLException e) {
			return Arrays.asList();
		}
	}
}
