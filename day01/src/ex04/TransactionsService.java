package ex04;

public class TransactionsService {
	private final UsersList			users;
	private final TransactionsList	outcomes;
	private final TransactionsList	incomes;
	private final TransactionsList	unpaired;

	TransactionsService() {
		users = new UsersArrayList();
		outcomes = new TransactionsLinkedList();
		incomes = new TransactionsLinkedList();
		unpaired = new TransactionsLinkedList();
	}

	public void addUser(String name, Integer balance) {
		users.addUser(new User(name, balance));
	}

	public Integer getUserBalance(Integer userID) {
		return users.getUserByID(userID).getBalance();
	}

	public void performTransaction(Integer senderID,
								   Integer recipientID,
								   Integer amount) {
		if (users.getUserByID(senderID).getBalance() < amount) {
			throw new IllegalTransactionException("performTransaction: sender's balance too low");
		}

		Transaction outcome = new Transaction(
											users.getUserByID(senderID).getId(),
											users.getUserByID(recipientID).getId(),
											amount);
		Transaction income = outcome.makeIncomeTransaction(outcome);

		users.getUserByID(senderID).performTransaction(outcome);
		users.getUserByID(recipientID).performTransaction(income);

		outcomes.addTransaction(outcome);
		incomes.addTransaction(income);
	}

	Transaction[] retrieveUsersTransactions(Integer userID) {
		return users.getUserByID(userID).getTransactions().toArray();
	}

	void removeUsersTransaction(Integer userID, String trID) {
		Transaction toRemove = users.getUserByID(userID).getTransactions().getTrByID(trID);

		if (unpaired.getTrByID(trID) != null) {
			unpaired.removeTrByID(trID);
		} else {
			unpaired.addTransaction(
				toRemove.getCategory() == Transaction.Category.INCOME ? outcomes.getTrByID(trID) : incomes.getTrByID(trID)
			);
		}
		users.getUserByID(userID).getTransactions().removeTrByID(trID);
	}

	Transaction[] getUnpairedTransactions() {
		return unpaired.toArray();
	}

	void removeFirstUsersTransaction(Integer userID) {
		this.removeUsersTransaction(userID, users.getUserByID(userID).getTransactions().toArray()[0].getId());
	}

	static class IllegalTransactionException extends RuntimeException {
		public IllegalTransactionException(String m) { message = m; }

		public String toString() { return("TransactionsService:" + message); }

		private static String message;
	}

}
