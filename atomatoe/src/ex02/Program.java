package ex02;

import java.util.Random;

public class Program {
    public static void main(String[] args) throws InterruptedException {
        if (args.length != 2) {
            System.out.println("Need 2 arguments!");
            System.exit(-1);
        }
        if (!(args[0].contains("--arraySize=")) || !(args[1].contains("--threadsCount="))) {
            System.out.println("Syntax arguments error");
            System.exit(-1);
        }
        String count_size = args[0].replaceAll("[^0-9]", "");
        String count_thread = args[1].replaceAll("[^0-9]", "");
        if (count_size.equals("") && count_thread.equals("")) {
            System.out.println("Error valid arguments!");
            System.exit(-1);
        }
        int array_size = Integer.parseInt(count_size);
        int thread_count = Integer.parseInt(count_thread);
        if(array_size > 2000000) {
            System.out.println("Array size is full!!");
            System.exit(-1);
        }
        if(thread_count > array_size) {
            System.out.println("Thread must not be larger than an array!");
            System.exit(-1);
        }
        int[] msv_array = new int[array_size];
        int[] sum_thread = new int[thread_count];

        int min = 1;
        int max = 1000;
        int diff = max - min;
        Random random = new Random();
        int rand = random.nextInt(diff + 1);
        int sum_main = 0;
        for(int i = 0; i != msv_array.length; i++) {
            msv_array[i] = rand;
            sum_main += rand;
        }

        Thread[] msv_thread = new Thread[thread_count];
        System.out.println("Sum: " + sum_main);
        int from = 0;
        int to = 0;
        for(int i = 0; i != thread_count; i++) {
                if((i + 1) == thread_count) {
                    if((from + (array_size / thread_count - 1) + thread_count) != array_size) {
                        int a = array_size - (from + (array_size / thread_count - 1) + thread_count);
                        to = (from + (array_size / thread_count - 1) + thread_count) + a;
                    }
                    else
                        to = from + (array_size / thread_count - 1) + thread_count;
                }
                else
                    to = from + (array_size / thread_count - 1);
//            }
            msv_thread[i] = new Thread(new MyThread(msv_array, from, to, sum_thread, i), ("Thread " + i));
            msv_thread[i].start();
            from = to;
        }
        try {
            for(int i = 0; i != thread_count; i++) {
                msv_thread[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int finally_sum_thread = 0;
        for(int i = 0; i != msv_thread.length; i++) {
            finally_sum_thread += sum_thread[i];
        }
        System.out.println("Sum by threads: " + finally_sum_thread);
    }
    public static boolean is_prime(int number)
    {
        boolean isComposite = false;

        for (int i = 2; i < number; i++)
        {
            if (number % i == 0)
            {
                isComposite = true;
                break;
            }
        }
        return(isComposite);
    }
}

class MyThread implements Runnable {
    private int[] array;
    private int[] sum;
    private int min;
    private int max;
    private int id;

    MyThread(int[] array, int min, int max, int[] sum, int id) {
        this.array = array;
        this.max = max;
        this.min = min;
        this.sum = sum;
        this.id = id;
    }
    public void run() {
        for(int i = min; i != max; i++) {
            this.sum[id] += array[i];
        }
        System.out.println(Thread.currentThread().getName() + ": from " + min + " to " + (max - 1) + " sum is " + this.sum[id]);
    }
}