package ex04;

public class User {
	private final					Integer id;
	private final					String  name;
	private							Integer balance;
	private final TransactionsList	transactions;

	User(
		String  name,
		Integer balance
	) {
		this.id = UserIdsGenerator.getInstance().generateId();
		this.name = name;
		this.balance = balance;
		this.transactions = new TransactionsLinkedList();
	}

	public int getBalance() { return balance; }

	public Integer getId() { return id; }

	public TransactionsList getTransactions() { return transactions; }

	public void setBalance(int balance) { this.balance = balance; }

	void performTransaction(Transaction transaction) {
		transactions.addTransaction(transaction);
		if (transaction.getCategory() == Transaction.Category.INCOME) {
			balance += transaction.getAmount();
		} else {
			balance -= transaction.getAmount();
		}
	}

	public void print() {
		System.out.printf("User %d, %s, balance: %d\n",
				id,
				name,
				balance);
	}
}
