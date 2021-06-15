package ex01;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        String count = validateArgs(args);
        Sync sync = new Sync();
        Thread egg = new Thread(new Egg("egg", sync, Integer.parseInt(count)));
        Thread hen = new Thread(new Hen("hen", sync, Integer.parseInt(count)));

        egg.start();
        hen.start();

        egg.join();
        hen.join();
    }

    private static String validateArgs(String[] args) {
        if (args.length != 1) {
            System.out.println("Need 1 argument.");
            System.exit(-1);
        }
        if (!(args[0].contains("--count="))) {
            System.out.println("Syntax argument's error. \nFormat: --count=*number of display times*");
            System.exit(-1);
        }
        String count = args[0].replaceAll("[^0-9]", "");
        if (count.equals("")) {
            System.out.println("Error valid arguments!");
            System.exit(-1);
        }
        return count;
    }

    public static class Sync {
        public int n = 0;
    }

    static class Egg implements Runnable {
        String message;
        final Sync sync;
        int count;

        Egg(String m, Sync s, int c) {
            message = m;
            sync = s;
            count = c;
        }

        private void print() throws InterruptedException {
            synchronized (sync) {
                while (sync.n != 0) {
                    sync.wait();
                }
                System.out.println(message);
                sync.n++;
                sync.notify();
            }
        }

        @Override
        public void run() {
            for (int i = 0; i < count; i++) {
                try {
                    print();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Hen implements Runnable {
        String message;
        final Sync sync;
        int count;

        Hen(String m, Sync s, int c) {
            message = m;
            sync = s;
            count = c;
        }

        private void print() throws InterruptedException {
            synchronized (sync) {
                while (sync.n == 0) {
                    sync.wait();
                }
                System.out.println(message);
                sync.n--;
                sync.notify();
            }
        }

        @Override
        public void run() {
            for (int i = 0; i < count; i++) {
                try {
                    print();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
