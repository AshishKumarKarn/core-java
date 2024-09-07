package karn.core.multithreading;

public class ThreadStates {
    public static void main(String[] args) throws InterruptedException {

        Thread thread  = new Thread(()->{
            System.out.println("RUNNING");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println(thread.getState());
        thread.start();
        System.out.println(thread.getState());
        Thread.sleep(100);
        System.out.println(thread.getState());
        thread.join();
        System.out.println(thread.getState());
    }

}
