package karn.core.multithreading.volatileAndAtomic;

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileNotSolvingThisProblem {
    public static void main(String[] args) throws InterruptedException {
        ICounter counter = new Counter();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });
        Thread t2 = new Thread(() -> {
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

interface ICounter {
    void increment();

    int getCount();
}

class Counter implements ICounter {
    private volatile int count;

    public void increment() {
        // The increment operation is not atomic, and the volatile keyword does not provide atomicity.
        // This can lead to race conditions where multiple threads may read the same value of count, increment it, and write it back,
        // resulting in lost updates and an incorrect final count.
        // For example, if two threads read the value of count as 0, both will increment it to 1 and write it back, resulting in a final count of 1 instead of 2.
        this.count++;
    }

    public int getCount() {
        return this.count;
    }
}

class SafeCounter implements ICounter {
    private AtomicInteger count = new AtomicInteger(0);

    public void increment() {
        //AtomicInteger provides atomicity and visibility guarantees,
        // ensuring that increments are thread-safe and
        // changes are visible across threads.
        this.count.getAndIncrement();
    }

    public int getCount() {
        return this.count.get();
    }
}

