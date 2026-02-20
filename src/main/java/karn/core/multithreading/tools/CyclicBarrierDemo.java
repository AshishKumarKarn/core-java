package karn.core.multithreading.tools;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier barrier = new CyclicBarrier(4);
        Hiker hiker1 = new Hiker("First Hiker ", barrier);
        Hiker hiker2 = new Hiker("Second Hiker ", barrier);
        Hiker hiker3 = new Hiker("Third Hiker ", barrier);
        Hiker hiker4 = new Hiker("Fourth Hiker ", barrier);
        hiker1.start();
        hiker2.start();
        hiker3.start();
        hiker4.start();

//        hiker2.join();
        System.out.println("Hiking ends..");
    }
}

class Hiker extends Thread {
    private final CyclicBarrier barrier;

    Hiker(String name, CyclicBarrier barrier) {
        super(name);
        this.barrier = barrier;
    }

    @Override
    public void run() {
        System.out.println(this.getName() + " started hiking.");
        try {
            Thread.sleep(1000);
            System.out.println(this.getName() + " reached barrier point. Waiting for others to join");
            this.barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
        try {
            Thread.sleep(1000);
            System.out.println(this.getName() + " finishing hiking. Let others come too.");
            this.barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
        System.out.println(this.getName() + " Finished hiking.");

    }
}
