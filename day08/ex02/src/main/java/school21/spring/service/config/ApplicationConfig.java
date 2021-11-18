package school21.spring.service.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class ApplicationConfig {
	@Value("${db.url}")
	String dbUrl;
	@Value("${db.user}")
	String dbUsername;
	@Value("${db.password}")
	String dbPassword;
	@Value("${db.driver.name}")
	String dbDriverName;

	@Bean(name = "hikariDS")
	HikariDataSource hikariDS() {
		HikariConfig config = new HikariConfig();

		config.setJdbcUrl(dbUrl);
		config.setUsername(dbUsername);
		config.setPassword(dbPassword);
		config.setDriverClassName(dbDriverName);

		return new HikariDataSource(config);
	}

	@Bean(name = "driverManagerDS")
	DriverManagerDataSource driverManagerDS() {
		return new DriverManagerDataSource(dbUrl, dbUsername, dbPassword);
	}
}
