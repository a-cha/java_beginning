package edu.school21.chat.repositories;

import edu.school21.chat.models.Message;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static edu.school21.chat.app.Program.statement;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
	@Override
	public Optional<Message> findById(Long id) throws SQLException {
		ResultSet res = statement.executeQuery("select * from users where user_id = " + id);
		Message message = new Message();

		message.setId(res.getLong("message_id"));
		message.setAuthorID(res.getLong("author_id"));
		message.setChatroomID(res.getLong("chatroom_id"));
		message.setMessageText(res.getString("text"));

		return Optional.of(message);
	}
}
