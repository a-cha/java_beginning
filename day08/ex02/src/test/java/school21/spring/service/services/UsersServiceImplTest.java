package school21.spring.service.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import school21.spring.service.config.TestApplicationConfig;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;
import school21.spring.service.repositories.UsersRepositoryJdbcImpl;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
	private static final Logger LOG = Logger.getLogger("");

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

		LOG.log(Level.INFO, "Generated password: \n" + password);

		assertNotNull(password);
	}

	@Test
	public void addRecordToDB() {
		User user = repository.findByEmail(EMAIL).orElse(null);

		LOG.log(Level.INFO, "New record in users table: \n" + user);

		assertNotNull(user);
	}
}
