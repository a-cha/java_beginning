package school21.spring.service.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component(value = "UsersRepositoryJdbcTemplateImpl")
public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {
	@Autowired
	@Qualifier("driverManagerDS")
	private DataSource ds;

	@Override
	public User findById(Long id) {
		ResultSetExtractor<User> extractor = rs -> {
			if (!rs.next())
				throw new IllegalStateException("No user with id " + id);

			User user = new User();
			user.setId(rs.getLong("id"));
			user.setEmail(rs.getString("login"));
			user.setPassword(rs.getString("password"));

			return user;
		};

		return new JdbcTemplate(ds).query(
				"select * from users where id = ?",
				new Object[]{id},
				new int[]{Types.BIGINT},
				extractor);
	}

	@Override
	public List<User> findAll() {
		return new JdbcTemplate(ds).query("select * from users",
				rs -> {
					List<User> userList = new LinkedList<>();

					while (rs.next()) {
						User user = new User();
						user.setId(rs.getLong("id"));
						user.setEmail(rs.getString("login"));
						user.setPassword(rs.getString("password"));
						userList.add(user);
					}

					return userList;
				});
	}

	@Override
	public void save(User entity) {
		new JdbcTemplate(ds).update(
				"insert into public.users (login, password) values (?, ?)",
				new Object[]{entity.getEmail(), entity.getPassword()},
				new int[]{Types.VARCHAR, Types.VARCHAR});
	}

	@Override
	public void update(User entity) {
		new JdbcTemplate(ds).update(
				"update public.users set login = ?, password = ? where id = ?",
				new Object[]{entity.getEmail(), entity.getPassword(), entity.getId()},
				new int[]{Types.VARCHAR, Types.VARCHAR, Types.BIGINT});
	}

	@Override
	public void delete(Long id) {
		new JdbcTemplate(ds).update(
				"delete from public.users where id = ?",
				new Object[]{id},
				new int[]{Types.BIGINT});
	}

	@Override
	public Optional<User> findByEmail(String email) {
		ResultSetExtractor<Optional<User>> extractor = rs -> {
			User user = new User();

			if (!rs.next())
				throw new IllegalStateException("No user with email " + email);

			user.setId(rs.getLong("id"));
			user.setEmail(rs.getString("login"));
			user.setPassword(rs.getString("password"));

			return Optional.of(user);
		};

		return new JdbcTemplate(ds).query(
				"select * from users where login = ?",
				new Object[]{email},
				new int[]{Types.VARCHAR},
				extractor);
	}
}
