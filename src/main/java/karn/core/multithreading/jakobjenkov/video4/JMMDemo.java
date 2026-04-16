package karn.core.multithreading.jakobjenkov.video4;

public class JMMDemo {
    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread thread1 = new Thread(myRunnable);
        Thread thread2 = new Thread(myRunnable);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

class MyRunnable implements Runnable {
    private int counter = 0;

    @Override
    public void run() {
        for (int i = 0; i < 1000000; i++) {
            synchronized (this) {
                counter++;
            }
        }
        System.out.println("Thread " + Thread.currentThread().getName() + " finished" + counter);
    }


}