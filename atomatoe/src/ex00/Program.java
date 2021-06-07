package ex00;

public class Program {
    public static void main(String[] args) {
        if(args.length != 1) {
            System.out.println("Need 1 arguments!");
            System.exit(-1);
        }
        if (!(args[0].contains("--count="))) {
            System.out.println("Syntax arguments error");
            System.exit(-1);
        }
        String count = args[0].replaceAll("[^0-9]", "");
        if(count.equals("")) {
            System.out.println("Error valid arguments!");
            System.exit(-1);
        }
        int i = Integer.parseInt(count);
        Thread Egg = new MyThread("Egg", i);
        Thread Hen = new MyThread("Hen", i);
        Egg.start();
        Hen.start();
        try {
            Egg.join();
            Hen.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(int it = 0; it != i; it++) {
            System.out.println("Human");
        }
    }

    public static class MyThread extends Thread {
        private String name;
        private int count;
        MyThread(String name, int count) { this.name = name; this.count = count; }
        @Override
        public void run() {
            for(int i = 0; i != count; i++)
                System.out.println(name);
        }
    }
}
