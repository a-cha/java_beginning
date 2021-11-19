package school21.spring.service.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import school21.spring.service.config.TestApplicationConfig;
import school21.spring.service.repositories.UsersRepository;
import school21.spring.service.repositories.UsersRepositoryJdbcImpl;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
		UsersServiceImpl.class,
		UsersRepositoryJdbcImpl.class,
		TestApplicationConfig.class
})
public class UsersServiceImplTest {
	@Autowired
	private UsersServiceImpl service;
	@Autowired
	private UsersRepository repository;
	@Autowired
	private DataSource ds;
	private static final String EMAIL = "test@email.com";

	@Before
	public void initDB() throws SQLException {
		ds.getConnection().createStatement().execute(
				"create table if not exists users" +
						"(id      bigint generated always as identity primary key," +
						"login    varchar(20) not null," +
						"password varchar(20) not null" +
						");");
	}

	@Test
	public void checkPassword() {
		String password = service.signUp(EMAIL);
		assertEquals("changed", password);
	}

	@Test
	public void addRecordToDB() {
		assertNotNull(repository.findByEmail(EMAIL).orElse(null));
	}
}
