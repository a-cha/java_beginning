package ex03;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

public class Program {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Need 1 arguments!");
            System.exit(-1);
        }
        if (!(args[0].contains("--threadsCount="))) {
            System.out.println("Syntax arguments error");
            System.exit(-1);
        }
        String count_thread = args[0].replaceAll("[^0-9]", "");
        if (count_thread.equals("")) {
            System.out.println("Error valid arguments!");
            System.exit(-1);
        }
        int thread_count = Integer.parseInt(count_thread);
        System.out.println(thread_count);
        String filePath = "./files_urls.txt";
        HashMap<String, String> map = new HashMap<String, String>();
        String key = "", value = "";
        int count_file = 0;
        try(FileInputStream fin = new FileInputStream(filePath))
        {
            int i = -1;
            char cymbal = 0;
            int index = 0;
            while((i = fin.read()) != -1) {
                cymbal = (char)i;
                if(index > 1 && cymbal != '\n') {
                    value += cymbal;
                }
                else if (index < 2 && cymbal != '\n') {
                    key += cymbal;
                }
                else {
                    key = key.replaceAll("\\s+","");
                    value = value.replaceFirst("\\s+","");
                    map.put(key, value);
                    count_file++;
                    index = 0;
                    key = "";
                    value = "";
                }
                index++;
            }
            key = key.replaceAll("\\s+","");
            value = value.replaceFirst("\\s+","");
            map.put(key, value);
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
        int[] msv_array = new int[count_file + 1];
        Thread[] msv_thread = new Thread[thread_count];
        for(int i = 0; i != thread_count; i++) {
            msv_thread[i] = new Thread(new MyThread(msv_array, map, i), ("Thread " + i));
            msv_thread[i].start();
        }

        try {
            for(int i = 0; i != thread_count; i++) {
                msv_thread[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyThread implements Runnable {
    private int[] index;
    private HashMap<String, String> map;
    private int name;

    MyThread(int[] array, HashMap<String, String> map, int name) {
        this.index = array;
        this.map = map;
        this.name = name;
    }

    public void run() {
        for(int i = 0; i != index.length; i++) {
            if(index[i] == 0) {
                index[i] = 1;
                System.out.println("Thread-" + name + " start download file number " + i);
                try {
                    Random random = new Random();
                    Thread.sleep(random.nextInt(5000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread-" + name + " finish download file number " + i);
            }
        }
    }
}