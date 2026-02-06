package karn.core.multithreading;

import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Collectors;

public class ThreadStates {
    public static void main(String[] args) throws InterruptedException {
        System.out.println((int)4e9);
        System.out.println(new SecureRandom().nextInt(100));
        StringBuilder SB=new StringBuilder();
        String substring = SB.substring(0);

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
