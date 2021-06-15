package ex04;

public class TransactionsLinkedList implements TransactionsList {
	private LinkedList  head;
	private LinkedList  tail;
	private Integer     length;


	TransactionsLinkedList() {
		this.head = null;
		this.tail = null;
		this.length = 0;
	}

	@Override
	public void addTransaction(Transaction transaction) {
		LinkedList newNode = new LinkedList(transaction);

		if (this.head == null) {
			head = tail = newNode;
		} else {
			tail.next = newNode;
			newNode.prev = tail;
			tail = newNode;
		}
		length++;
	}

	@Override
	public void removeTrByID(String id) {
		LinkedList iter = head;

		if (head.next == null && head.transaction.getId().equals(id)) {
			head = tail = null;
			length--;
			return;
		} else {
			while (iter != null) {
				if (iter.transaction.getId().equals(id)) {
					if (iter.next == null) {
						tail = iter.prev;
						tail.next = null;
					} else if (iter.prev == null) {
						head = iter.next;
						head.prev = null;
					} else {
						iter.prev.next = iter.next;
						iter.next.prev = iter.prev;
					}
					length--;
					return;
				}
				iter = iter.next;
			}
		}
		throw new TransactionNotFoundException("removeTransactionByID: no transaction with such id");
	}

	@Override
	public Transaction getTrByID(String id) {
		LinkedList iter = head;

		while (iter != null) {
			if (iter.transaction.getId().equals(id)) {
				return iter.transaction;
			}
			iter = iter.next;
		}
		return null;
	}

	@Override
	public Transaction[] toArray() {
		Transaction[]   trArray = new Transaction[length];
		LinkedList      iter = head;
		Integer         i = 0;

		while (iter != null) {
			trArray[i++] = iter.transaction;
			iter = iter.next;
		}
		return trArray;
	}
	private static class LinkedList {
		LinkedList(Transaction t) {
			this.transaction = t;
			this.next = null;
			this.prev = null;
		}
		private final Transaction transaction;
		private LinkedList  next;
		private LinkedList  prev;

	}

	static class TransactionNotFoundException extends RuntimeException {
		public TransactionNotFoundException(String m) {
			message = m;
		}

		public String toString() { return ("TransactionsLinkedList:" + message); }

		private static String message;
	}
}
