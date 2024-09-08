package karn.core.multithreading.volatileAndAtomic;

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileNotSolvingThisProblem {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        Thread t1=new Thread(()->{
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });
        Thread t2 = new Thread(()->{
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(counter.getCount());
    }
}

class Counter{
    private volatile int count;
    public void increment(){
        //Although, threads are reading it directly
        // from RAM but not local cache but two threads can read same value.
        this.count++;
    }
    public int getCount(){
        return this.count;
    }
}
class SafeCounter{
    private AtomicInteger count = new AtomicInteger(0);
    public void increment(){
        //Although, threads are reading it directly
        // from RAM but not local cache but two threads can read same value.
        this.count.getAndIncrement();
    }
    public int getCount(){
        return this.count.get();
    }
}

