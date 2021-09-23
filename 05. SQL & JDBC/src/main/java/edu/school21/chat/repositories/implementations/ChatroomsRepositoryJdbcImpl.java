package edu.school21.chat.repositories.implementations;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.ChatroomsRepository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ChatroomsRepositoryJdbcImpl implements ChatroomsRepository {
	private final DataSource ds;

	public ChatroomsRepositoryJdbcImpl(DataSource dataSource) {
		this.ds = dataSource;
	}

	@Override
	public Optional<Chatroom> findById(Long id) {
		Chatroom chatroom = new Chatroom();
		User user = new User();

		try {
			ResultSet res = ds.getConnection().createStatement().
					executeQuery("select * from chatrooms where id = " + id);
			if (!res.next())
				return Optional.empty();

			chatroom.setId(res.getLong("id"));
			chatroom.setName(res.getString("name"));
			chatroom.setOwner(new UsersRepositoryJdbcImpl(ds).findById(
					res.getLong("owner")
			).orElse(null));
			chatroom.setMessages(null);
		} catch (SQLException throwable) {
			throwable.printStackTrace();
		}

		return Optional.of(chatroom);
	}

	@Override
	public void save(Chatroom message) {
		//		todo implement here
	}
}
