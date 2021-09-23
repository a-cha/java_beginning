package edu.school21.chat.repositories.implementations;

import edu.school21.chat.models.User;
import edu.school21.chat.repositories.UsersRepository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {
	private final DataSource ds;

	public UsersRepositoryJdbcImpl(DataSource dataSource) {
		this.ds = dataSource;
	}

	@Override
	public Optional<User> findById(Long id) {
		User user = new User();

		try {
			ResultSet res = ds.getConnection().createStatement().
					executeQuery("select * from users where id = " + id);
			res.next();

			user.setId(res.getLong("id"));
			user.setLogin(res.getString("login"));
			user.setPassword(res.getString("password"));
			user.setCreatedRooms(null);
			user.setMemberRooms(null);
		} catch (SQLException throwable) {
			throwable.printStackTrace();
		}

		return Optional.of(user);
	}

	@Override
	public void save(User message) {
		//		todo implement here
	}
}
