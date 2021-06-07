package ex03;

import java.util.Random;

public class Program {

    public static void main(String[] args) {
        Random rand = new Random();
        TransactionsLinkedList transactions = new TransactionsLinkedList();

        for (int i = 0; i < 5; i++) {
            transactions.addTransaction(new Transaction(
                    rand.nextInt(10),
                    rand.nextInt(10),
                    rand.nextInt(2) == 1 ? Transaction.Category.INCOME : Transaction.Category.OUTCOME,
                    rand.nextInt(1000)));
        }
        System.out.println("Original transactions");
        Transaction[] trArray = transactions.toArray();
        for (Transaction transaction : trArray) {
            transaction.print();
        }

        transactions.removeTransactionByID(transactions.toArray()[4].getId());
        transactions.removeTransactionByID(transactions.toArray()[2].getId());
        transactions.removeTransactionByID(transactions.toArray()[0].getId());

        System.out.println();
        System.out.println("After deleting");
        trArray = transactions.toArray();
        for (Transaction transaction : trArray) {
            transaction.print();
        }
    }
}
