package edu.school21.chat.repositories.implementations;

import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.MessagesRepository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
	private final DataSource ds;

	public MessagesRepositoryJdbcImpl(DataSource dataSource) {
		this.ds = dataSource;
	}

	@Override
	public Optional<Message> findById(Long id) {
		Message message = new Message();

		try {
			ResultSet res = ds.getConnection().createStatement().
					executeQuery("select * from messages where id = " + id);
			res.next();

			message.setId(res.getLong("id"));
			message.setAuthor(new UsersRepositoryJdbcImpl(ds).findById(
					res.getLong("author")
			).orElse(null));
			message.setRoom(new ChatroomsRepositoryJdbcImpl(ds).findById(
					res.getLong("author")
			).orElse(null));
			message.setText(res.getString("text"));
			message.setDateTime(res.getObject("datetime", Timestamp.class));
		} catch (SQLException throwable) {
			throwable.printStackTrace();
		}

		return Optional.of(message);
	}

	@Override
	public void save(Message message) {
//		todo implement here
	}
}
