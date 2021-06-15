package edu.school21.chat.models;

import java.time.LocalDateTime;

public class Message {
	private Long			id;
	private Long			authorID;
	private Long			chatroomID;
	private String			messageText;
	private LocalDateTime	dateTime;

	public void setId(Long id) {
		this.id = id;
	}

	public void setAuthorID(Long authorID) {
		this.authorID = authorID;
	}

	public void setChatroomID(Long chatroomID) {
		this.chatroomID = chatroomID;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Message)) return false;

		Message message = (Message) o;

		if (!id.equals(message.id)) return false;
		if (!authorID.equals(message.authorID)) return false;
		if (!chatroomID.equals(message.chatroomID)) return false;
		if (!messageText.equals(message.messageText)) return false;
		return dateTime.equals(message.dateTime);
	}

	@Override
	public int hashCode() {
		int result = id.hashCode();
		result = 31 * result + authorID.hashCode();
		result = 31 * result + chatroomID.hashCode();
		result = 31 * result + messageText.hashCode();
		result = 31 * result + dateTime.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "Message{" +
				"id=" + id +
				", authorID=" + authorID +
				", chatroomID=" + chatroomID +
				", messageText='" + messageText + '\'' +
				", dateTime=" + dateTime +
				'}';
	}
}
