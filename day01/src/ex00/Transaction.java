package ex00;

import java.util.UUID;

public class Transaction {
	String      id;
	int         senderId;
	int         recipientId;
	Category    category;
	int         amount;

	public enum Category {
		INCOME,
		OUTCOME

	}

	Transaction(
		Integer     senderId,
		Integer     recipientId,
		int         amount
	) {
		id = UUID.randomUUID().toString();
		this.senderId = senderId;
		this.recipientId = recipientId;
		this.category = Category.OUTCOME;
		this.amount = amount;
	}

	Transaction(Transaction other) {
		this.id = other.id;
		this.senderId = other.senderId;
		this.recipientId = other.recipientId;
		this.category = Category.INCOME;
		this.amount = other.amount;
	}

	public Category isCategory() {
		return category;
	}

	public String getId() {
		return id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void print() {
		System.out.printf("%s -> %s, %s%d, %s, %s\n",
				senderId,
				recipientId,
				category == Category.INCOME ? "+" : "",
				amount,
				category == Category.INCOME ? "INCOME" : "OUTCOME",
				id);
	}
}
