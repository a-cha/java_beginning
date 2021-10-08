package school21.spring.service.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {
	private final DataSource ds;

	public UsersRepositoryJdbcTemplateImpl(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public User findById(Long id) {
		ResultSetExtractor<User> extractor = rs -> {
			if (!rs.next())
				throw new IllegalStateException("No user with id " + id);

			User user = new User();
			user.setId(rs.getLong("id"));
			user.setEmail(rs.getString("login"));

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
						userList.add(user);
					}

					return userList;
				});
	}

	@Override
	public void save(User entity) {
		new JdbcTemplate(ds).update(
				"insert into public.users (login, password) values (?, ?)",
				new Object[]{entity.getEmail(), "empty"},
				new int[]{Types.VARCHAR, Types.VARCHAR});
	}

	@Override
	public void update(User entity) {
		new JdbcTemplate(ds).update(
				"update public.users set login = ?, password = ? where id = ?",
				new Object[]{entity.getEmail(), "empty", entity.getId()},
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

			return Optional.of(user);
		};

		return new JdbcTemplate(ds).query(
				"select * from users where login = ?",
				new Object[]{email},
				new int[]{Types.VARCHAR},
				extractor);
	}
}
