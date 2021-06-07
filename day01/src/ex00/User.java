package ex00;

public class User {
	private final   Integer id;
	private final   String  name;
	private         Integer balance;

	User(
		String  name,
		Integer balance
	) {
		this.id = 0;
		this.name = name;
		this.balance = balance;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public Integer getId() {
		return id;
	}

	public void print() {
		System.out.printf("User %d, %s, balance: %d\n",
				id,
				name,
				balance);
	}
}
