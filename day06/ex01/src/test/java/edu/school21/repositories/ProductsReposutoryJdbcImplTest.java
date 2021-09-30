package edu.school21.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.sql.SQLException;

public class ProductsReposutoryJdbcImplTest {
	private static DataSource db;

	@BeforeEach
	private void init() throws SQLException {
		db = new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.HSQL)
				.addScript("schema.sql")
				.addScript("data.sql")
				.build();

		db.getConnection();
	}

	@ParameterizedTest
	@ValueSource(longs = {0, 1, 5})
	void testFindByIdCorrect() {

	}

	@ParameterizedTest
	@ValueSource(longs = {-5, 20, 4245})
	void testFindByIdIncorrect() {

	}

	@Test
	void testFindAll() {

	}

	@Test
	void testSave() {

	}

}
