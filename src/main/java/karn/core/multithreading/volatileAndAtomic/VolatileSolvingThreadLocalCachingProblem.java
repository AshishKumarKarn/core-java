package karn.core.multithreading.volatileAndAtomic;

public class VolatileSolvingThreadLocalCachingProblem {
    public static void main(String[] args) throws InterruptedException {
        VolatileResource volatileResource = new VolatileResource();
        Thread t1 = new Thread(() -> {
            volatileResource.printFlagStatus();
        });
        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            volatileResource.setFlagTrue();

        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Program complete");

    }

}

class VolatileResource {
    private volatile boolean flag = false;

    void printFlagStatus() {
        while (!flag) {
            //infine loop
        }
        System.out.println("Flag is true.");
    }

    void setFlagTrue() {
        flag = true;
    }
}

class NonVolatileResource {
    private boolean flag = false;

    void printFlagStatus() {
        while (!flag) {
            //infine loop
        }
        System.out.println("Flag is true.");
    }

    void setFlagTrue() {
        flag = true;
    }

}
