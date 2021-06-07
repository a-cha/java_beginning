package ex02;

public class Program {

    public static void main(String[] args) {
        UsersArrayList users = new UsersArrayList();

        System.out.println("Creates 40 users");
        for (int i = 0; i < 40; i++) {
            users.addUser(new User("Kek Viktorovch", 1000));
        }
        System.out.println("Their info:");
        for (int i = 1; i <= users.length(); i++) {
            users.getUserByID(i).print();
        }
        System.out.println("Info about user with id 100");
        users.getUserByID(100).print();

        System.out.println("Creates other 30 users");
        for (int i = 0; i < 30; i++) {
            users.addUser(new User("Lol Vital'evich", 2000));
        }
        System.out.println("Their info:");
        for (int i = 40; i <= users.length(); i++) {
            users.getUserByID(i).print();
        }
        System.out.println("All info:");
        for (int i = 0; i < users.length(); i++) {
            users.getUserByIndex(i).print();
        }
//        users.getUserByIndex(100).print();
    }
}
