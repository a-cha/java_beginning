package ex00;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        String count = validateArgs(args);
        Thread egg = new Thread(new Printer("Egg", Integer.parseInt(count)));
        Thread hen = new Thread(new Printer("Hen", Integer.parseInt(count)));

        egg.start();
        hen.start();

        egg.join();
        hen.join();

        for (int i = 0; i < Integer.parseInt(count); i++) {
            System.out.println("Human");
        }
    }

    private static String validateArgs(String[] args) {
        if (args.length != 1) {
            System.out.println("Need 1 argument.");
            System.exit(-1);
        }
        if (!(args[0].contains("--count="))) {
            System.out.println("Syntax argument's error. Format: --count=*number of display times*");
            System.exit(-1);
        }
        String count = args[0].replaceAll("[^0-9]", "");
        if (count.equals("")) {
            System.out.println("Syntax argument's error. Format: --count=*number of display times*");
            System.exit(-1);
        }
        return count;
    }

    static class Printer implements Runnable {
        String message;
        final int count;

        Printer (String m, int c) {
            message = m;
            count = c;
        }

        @Override
        public void run() {
            for (int i = 0; i < count; i++) {
                System.out.println(message);
            }
        }
    }

}
