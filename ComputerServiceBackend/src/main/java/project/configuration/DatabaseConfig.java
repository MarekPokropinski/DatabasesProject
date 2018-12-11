package project.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

@Configuration
public class DatabaseConfig {

	@Value("${jdbc.url}")
	private String jdbcUrl;

	@Value("${jdbc.username}")
	private String jdbcUsername;

	@Value("${jdbc.password}")
	private String jdbcPassword;

	@Bean
	public DataSource getDataSource() {
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setURL(jdbcUrl);
		dataSource.setUser(jdbcUsername);
		dataSource.setPassword(jdbcPassword);
		return dataSource;
	}
}
