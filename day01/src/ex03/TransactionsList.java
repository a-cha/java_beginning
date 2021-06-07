package ex03;

public interface TransactionsList {
	void            addTransaction(Transaction transaction);
	void            removeTransactionByID (String id) throws TransactionsLinkedList.TransactionNotFoundException;
	Transaction[]   toArray();
}
