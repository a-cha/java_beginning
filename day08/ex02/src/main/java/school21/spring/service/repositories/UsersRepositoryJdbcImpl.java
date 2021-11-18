package school21.spring.service.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
public class UsersRepositoryJdbcImpl implements UsersRepository {
	@Autowired
	@Qualifier("hikariDS")
	private DataSource ds;

	@Override
	public User findById(Long id) {
		User user = new User();

		try {
			ResultSet res = ds.getConnection().createStatement().
					executeQuery("select * from users where id = " + id);
			if (!res.next())
				throw new IllegalStateException("No user with id " + id);

			user.setId(res.getLong("id"));
			user.setEmail(res.getString("login"));
			user.setPassword(res.getString("password"));
		} catch (SQLException throwable) {
			throwable.printStackTrace();
		}

		return user;
	}

	@Override
	public List<User> findAll() {
		List<User> userList = new LinkedList<>();

		try {
			ResultSet res = ds.getConnection().createStatement().
					executeQuery("select * from users");

			while (res.next()) {
				User user = new User();
				user.setId(res.getLong("id"));
				user.setEmail(res.getString("login"));
				user.setPassword(res.getString("password"));
				userList.add(user);
			}
		} catch (SQLException throwable) {
			throwable.printStackTrace();
		}

		return userList;
	}

	@Override
	public void save(User entity) {
		try {
			ds.getConnection().createStatement().executeUpdate(
					"insert into public.users (login, password) " +
							"values ('" +
							entity.getEmail() + "', '" +
							entity.getPassword() + "'" +
							");"
			);
		} catch (SQLException throwable) {
			throwable.printStackTrace();
		}
	}

	@Override
	public void update(User entity) {
		try {
			ds.getConnection().createStatement().executeUpdate(
					"update public.users " +
							"set login = '" + entity.getEmail() + "', " +
							"password = '" + entity.getPassword() + "'" +
							"where id = " + entity.getId() + ";"
			);
		} catch (SQLException throwable) {
			throwable.printStackTrace();
		}
	}

	@Override
	public void delete(Long id) {
		try {
			ds.getConnection().createStatement().executeUpdate(
					"delete from public.users where id = " + id);
		} catch (SQLException throwable) {
			throwable.printStackTrace();
		}
	}

	@Override
	public Optional<User> findByEmail(String email) {
		User user = new User();

		try {
			ResultSet res = ds.getConnection().createStatement().
					executeQuery("select * from users where login = '" + email + "'");
			if (!res.next())
				throw new IllegalStateException("No user with email " + email);

			user.setId(res.getLong("id"));
			user.setEmail(res.getString("login"));
		} catch (SQLException throwable) {
			throwable.printStackTrace();
		}

		return Optional.of(user);
	}
}
