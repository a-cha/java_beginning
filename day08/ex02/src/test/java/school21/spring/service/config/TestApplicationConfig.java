package school21.spring.service.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestApplicationConfig {
	@Bean(name = "hikariDS")
	HikariDataSource hikariDS() {
		HikariConfig config = new HikariConfig();

//todo add in-memory setting
		config.setJdbcUrl("jdbc:h2:~/test");
		config.setUsername("sa");
		config.setPassword("");
		config.setDriverClassName("org.h2.Driver");

		return new HikariDataSource(config);
	}
}
