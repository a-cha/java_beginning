package ex03;

import java.util.UUID;

public class Transaction {
	String      id;
	int         recipientId;
	int         senderId;
	Category    category;
	int         amount;

	public enum Category {
		INCOME,
		OUTCOME

	}

	Transaction(
			Integer     senderId,
			Integer     recipientId,
			Category    category,
			int         amount
	) {
		id = UUID.randomUUID().toString();
		this.recipientId = recipientId;
		this.senderId = senderId;
		this.category = category;
		this.amount = amount;
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
				category == Transaction.Category.INCOME ? "+" : "",
				amount,
				category == Transaction.Category.INCOME ? "INCOME" : "OUTCOME",
				id);
	}
}
