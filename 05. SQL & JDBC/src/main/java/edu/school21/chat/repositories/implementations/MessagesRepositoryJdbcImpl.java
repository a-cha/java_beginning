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
			if (!res.next())
				return Optional.empty();

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
		if (!new ChatroomsRepositoryJdbcImpl(ds).findById(message.getRoom().getId()).isPresent() ||
				!new UsersRepositoryJdbcImpl(ds).findById(message.getAuthor().getId()).isPresent()) {
			throw new NotSavedSubEntityException();
		}

		try {
			ResultSet sequence = ds.getConnection()
					.createStatement()
					.executeQuery("select nextval('public.messages_id_seq');");
//					.executeQuery("select setval('public.messages_id_seq', 5);");
			sequence.next();
			long id = sequence.getLong(1);

			ds.getConnection().createStatement().executeUpdate(
					"insert into public.messages (id, author, chatroom, text, datetime) " +
							"overriding system value values (" +
							id + ", " +
							message.getAuthor().getId() + ", " +
							message.getRoom().getId() + ", " +
							"'" + message.getText() + "'" + ", " +
							"'" + message.getDateTime() + "'" +
							");"
			);
			message.setId(id);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

	}

	@Override
	public void update(Message message) {
		if (!new ChatroomsRepositoryJdbcImpl(ds).findById(message.getRoom().getId()).isPresent() ||
				!new UsersRepositoryJdbcImpl(ds).findById(message.getAuthor().getId()).isPresent()) {
			throw new NotSavedSubEntityException();
		}

		try {
			ds.getConnection().createStatement().executeUpdate(
					"update public.messages " +
							"set author = " + message.getAuthor().getId() + ", " +
							"chatroom = " + message.getRoom().getId() + ", " +
							"text = " + (message.getText().isEmpty() ? null : "'" + message.getText() + "'") + ", " +
							"datetime = " + (message.getDateTime() == null ? null : "'" + message.getDateTime() + "'") + " " +
							"where id = " + message.getId() + ";"
			);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

	}

	private static class NotSavedSubEntityException extends RuntimeException {
	}
}
