package ex04;

public class Program {

    public static void main(String[] args) {
        TransactionsService service = new TransactionsService();

        service.addUser("John", 1000);
        service.addUser("Jack", 500);
        service.addUser("Jinn", 2000);
        service.addUser("Jackie", 700);
        service.addUser("Jane", 12000);
        service.addUser("Jerry", 3000);

        System.out.println("Original balance");
        for (int i = 1; i <= 6; i++) {
            System.out.printf("User %d has %d\n", i, service.getUserBalance(i));
        }

        System.out.println();
        System.out.println("Performing transactions...");
        service.performTransaction(1, 2, 200);
        service.performTransaction(1, 2, 100);
        service.performTransaction(1, 3, 400);
        service.performTransaction(5, 5, 500);
        service.performTransaction(6, 4, 100);
        service.performTransaction(6, 4, 100);
        service.performTransaction(1, 4, 800);
        service.performTransaction(2, 3, 200);

        System.out.println();
        System.out.println("Current balance");
        for (int i = 1; i <= 6; i++) {
            System.out.printf("User %d has %d\n", i, service.getUserBalance(i));
        }

        System.out.println();
        System.out.println("Performed transactions");
        Transaction[] transactions;
        for (int i = 1; i <= 6; i++) {
            transactions = service.retrieveUsersTransactions(i);
            for (Transaction transaction : transactions) {
                transaction.print();
            }
        }

        System.out.println();
        System.out.println("Unpaired transactions");
        Transaction[] unpaired = service.getUnpairedTransactions();
        for (Transaction transaction : unpaired) {
            transaction.print();
        }
        System.out.println();
        System.out.println("Remove some transactions...");
        service.removeFirstUsersTransaction(1);
//        service.removeFirstUsersTransaction(2);
        service.removeFirstUsersTransaction(5);
        service.removeFirstUsersTransaction(4);
        System.out.println();
        System.out.println("Unpaired transactions");
        unpaired = service.getUnpairedTransactions();
        for (Transaction transaction : unpaired) {
            transaction.print();
        }

        System.out.println();
        System.out.println("Current list of transactions");
        for (int i = 1; i <= 6; i++) {
            transactions = service.retrieveUsersTransactions(i);
            for (Transaction transaction : transactions) {
                transaction.print();
            }
        }

    }
}
