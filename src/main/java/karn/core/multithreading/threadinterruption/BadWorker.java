package karn.core.multithreading.threadinterruption;

public class BadWorker implements Runnable {
    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Working...");
                Thread.sleep(1000); // blocking call
            } catch (InterruptedException e) {
                // ❌ Swallowing the interrupt
                System.out.println("Interrupted, but ignoring it...");
                break;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Thread t = new Thread(new BadWorker());
        t.start();

        Thread.sleep(3000);
        System.out.println("Main thread: interrupting worker");
        t.interrupt(); // request cancellation
    }
}
