package edu.school21.chat.models;

import java.util.List;

public class User {
	private Long id;
	private String login;
	private String password;
	private List<Chatroom> createdRooms;
	private List<Chatroom> memberRooms;

	public User() {
	}

	public User(Long id,
				String login,
				String password,
				List<Chatroom> createdRooms,
				List<Chatroom> memberRooms
	) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.createdRooms = createdRooms;
		this.memberRooms = memberRooms;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Chatroom> getCreatedRooms() {
		return createdRooms;
	}

	public void setCreatedRooms(List<Chatroom> createdRooms) {
		this.createdRooms = createdRooms;
	}

	public List<Chatroom> getMemberRooms() {
		return memberRooms;
	}

	public void setMemberRooms(List<Chatroom> memberRooms) {
		this.memberRooms = memberRooms;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof User)) return false;

		User user = (User) o;

		if (!id.equals(user.id)) return false;
		if (!login.equals(user.login)) return false;
		if (!password.equals(user.password)) return false;
		if (!createdRooms.equals(user.createdRooms)) return false;
		return memberRooms.equals(user.memberRooms);
	}

	@Override
	public int hashCode() {
		int result = id.hashCode();
		result = 31 * result + login.hashCode();
		result = 31 * result + password.hashCode();
		result = 31 * result + createdRooms.hashCode();
		result = 31 * result + memberRooms.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", login='" + login + '\'' +
				", password='" + password + '\'' +
				", createdRooms=" + createdRooms +
				", memberRooms=" + memberRooms +
				'}';
	}
}
