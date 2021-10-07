package school21.spring.service.models;

import java.util.Objects;

public class User {
	Long	id;
	String	email;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", eMail='" + email + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof User)) return false;
		User user = (User) o;
		return getId() == user.getId() && Objects.equals(getEmail(), user.getEmail());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getEmail());
	}
}
