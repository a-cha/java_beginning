package edu.school21.chat.models;

import java.util.List;

public class Chatroom {
	private Long			id;
	private String			name;
	private Long			ownerID;
	private List<Message>	messages;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Chatroom)) return false;

		Chatroom chatroom = (Chatroom) o;

		if (!id.equals(chatroom.id)) return false;
		if (!name.equals(chatroom.name)) return false;
		if (!ownerID.equals(chatroom.ownerID)) return false;
		return messages.equals(chatroom.messages);
	}

	@Override
	public int hashCode() {
		int result = id.hashCode();
		result = 31 * result + name.hashCode();
		result = 31 * result + ownerID.hashCode();
		result = 31 * result + messages.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "Chatroom{" +
				"id=" + id +
				", name='" + name + '\'' +
				", ownerID=" + ownerID +
				", messages=" + messages +
				'}';
	}
}
