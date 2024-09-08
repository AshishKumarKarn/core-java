package karn.core.multithreading.volatileAndAtomic;

public class VolatileSolvingThreadLocalCachingProblem {
    public static void main(String[] args) throws InterruptedException {
        Resource resource = new VolatileResource();
        Thread t1 = new Thread(() -> {
            resource.printFlagStatus();
        });
        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            resource.setFlagTrue();

        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Program complete");

    }

}
interface Resource{

    void printFlagStatus();

    void setFlagTrue();
}
class VolatileResource implements Resource {
    private volatile boolean flag = false;

    @Override
    public void printFlagStatus() {
        while (!flag) {
            //infine loop
        }
        System.out.println("Flag is true.");
    }

    @Override
    public void setFlagTrue() {
        flag = true;
    }
}

class NonVolatileResource implements Resource{
    private boolean flag = false;

    @Override
    public void printFlagStatus() {
        while (!flag) {
            //infine loop
        }
        System.out.println("Flag is true.");
    }

    @Override
    public void setFlagTrue() {
        flag = true;
    }

}
