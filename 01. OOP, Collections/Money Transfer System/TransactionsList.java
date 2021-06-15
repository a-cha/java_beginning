package ex04;

public interface TransactionsList {
	void            addTransaction(Transaction transaction);
	void			removeTrByID(String id);
	Transaction[]   toArray();
	Transaction		getTrByID(String id);
}
