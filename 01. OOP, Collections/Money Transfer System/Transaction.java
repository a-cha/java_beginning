package ex04;

import java.util.UUID;

public class Transaction {
	private 			String id;
	private final int	senderId;
	private final int	recipientId;
	private Category	category;
	private final int	amount;

	public enum Category {
		INCOME,
		OUTCOME

	}

	Transaction(
		Integer senderId,
		Integer recipientId,
		int		amount
	) {
		id = UUID.randomUUID().toString();
		this.senderId = senderId;
		this.recipientId = recipientId;
		this.category = Category.OUTCOME;
		this.amount = amount;
	}

	Transaction makeIncomeTransaction(Transaction outcome) {
		Transaction income = new Transaction(outcome.senderId, outcome.recipientId, outcome.amount);

		income.id = outcome.getId();
		income.category = Category.INCOME;
		return income;
	}

	public String getId() { return id; }

	public Category getCategory() { return category; }

	public int getAmount() { return amount; }

	public void print() {
		System.out.printf("%s -> %s, %s%d, %s, %s\n",
				senderId,
				recipientId,
				category == Transaction.Category.INCOME ? "+" : "-",
				amount,
				category == Transaction.Category.INCOME ? "INCOME" : "OUTCOME",
				id);
	}
}
