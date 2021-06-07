package ex01;

import java.util.LinkedList;
import java.util.Queue;

public class Program {
    public static void main(String[] args) throws InterruptedException {
        if (args.length != 1) {
            System.out.println("Need 1 arguments!");
            System.exit(-1);
        }
        if (!(args[0].contains("--count="))) {
            System.out.println("Syntax arguments error");
            System.exit(-1);
        }
        String count = args[0].replaceAll("[^0-9]", "");
        if (count.equals("")) {
            System.out.println("Error valid arguments!");
            System.exit(-1);
        }
        int i = Integer.parseInt(count);
        Queue<Integer> commonQueue = new LinkedList<>();
        Thread Egg = new Producer(commonQueue, i, "Egg");
        Thread Hen = new Consumer(commonQueue, i, "Hen");
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

}
    class Producer extends Thread {

        public Queue<Integer> producerQueue;
        public int size;
        private String name;

        Producer(Queue<Integer> queue, int size, String name) {
            producerQueue = queue;
            this.size = size;
            this.name = name;
        }

        public void produce() throws InterruptedException {
            synchronized (producerQueue) {
                while (producerQueue.size() != 0) {
                    producerQueue.wait();
                }
                System.out.println(name);
                producerQueue.add(1);
                producerQueue.notify();
                // Thread.sleep(100);
            }
        }

        public void run() {
            try {
                while (size != 0) {
                    produce();
                    size--;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    class Consumer extends Thread {
        public Queue<Integer> consumerQueue;
        public int size;
        private String name;

        Consumer(Queue<Integer> queue, int size, String name) {
            consumerQueue = queue;
            this.size = size;
            this.name = name;
        }

        public void consume() throws InterruptedException {
            synchronized (consumerQueue) {
                while (consumerQueue.size() == 0) {
                    consumerQueue.wait();
                }
                System.out.println(name);
                consumerQueue.poll();
                consumerQueue.notify();
                // Thread.sleep(100);
            }
        }

        public void run() {
            try {
                while (size != 0) {
                    consume();
                    size--;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

}
