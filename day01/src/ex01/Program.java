package ex01;

public class Program {

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            User user = new User("Name", 1000);
            user.print();
        }
    }
}
