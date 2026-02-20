package karn.core.multithreading.threadinterruption;

public class GoodWorker implements Runnable {
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                System.out.println("Working...");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // ✅ Restore interrupt flag
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Worker exiting gracefully");
    }

    public static void main(String[] args) throws Exception {
        Thread t = new Thread(new GoodWorker());
        t.start();

        Thread.sleep(3000);
        System.out.println("Main thread: interrupting worker");
        t.interrupt();
    }
}
