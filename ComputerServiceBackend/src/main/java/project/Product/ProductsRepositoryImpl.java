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
    private static final String findByCategory = "select id, category_id, name, description, price from products where category_id=?";
    private static final String addProduct = "insert into products (category_id, name, description, price) values (?,?,?,?)";
    private static final Logger LOG = Logger.getLogger(ProductsRepository.class);

    @Autowired
    private DataSource dataSource;

    @Autowired
    private CategoriesRepository categoriesRepository;

    private ProductDTO createProductFromResultSet(ResultSet resultSet) throws SQLException, CategoryNotFoundException {
        ProductDTO product = new ProductDTO(
                resultSet.getInt("id"),
                resultSet.getInt("category_id"),
                resultSet.getString("name"),
                resultSet.getString("description"),
                resultSet.getBigDecimal("price"));
        return product;
    }

    @Override
    public Optional<ProductDTO> findById(int id) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(findByIdQuery);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            ProductDTO product = createProductFromResultSet(resultSet);
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
    public Optional<ProductDTO> findByName(String name) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(findByNameQuery);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            ProductDTO product = createProductFromResultSet(resultSet);
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
    public List<ProductDTO> findByNameQuery(String query) {
        List<ProductDTO> products = new ArrayList<>();
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

    @Override
    public List<ProductDTO> getProductsFromCategory(int categoryId) {
        List<ProductDTO> products = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(findByCategory);
            statement.setInt(1, categoryId);
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

    @Override
    public void createProduct(ProductDTO product) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(addProduct);
            statement.setInt(1, product.getCategoryId());
            statement.setString(2, product.getName());
            statement.setString(3, product.getDescription());
            statement.setBigDecimal(4, product.getPrice());
            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            LOG.warn(e.getMessage());
        }
    }
}
