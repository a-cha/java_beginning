package ex00;

public class Program {

    public static void main(String[] args) {
        User john, jack;
        Transaction tr, tr1;

        john = new User("John", 1000);
        jack = new User("Jack", 500);
        tr = new Transaction(jack.getId(), john.getId(), 100);
        tr1 = new Transaction(john.getId(), jack.getId(), 200);

        john.print();
        jack.print();
        tr.print();
        tr1.print();
    }
}
