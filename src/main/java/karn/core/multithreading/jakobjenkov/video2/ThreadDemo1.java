package karn.core.multithreading.jakobjenkov.video2;

public class ThreadDemo1 {
    public static void main(String[] args) throws InterruptedException {
        StoppableRunnable stoppableRunnable = new StoppableRunnable();
        Thread thread = new Thread(stoppableRunnable, "My Thread");
        thread.start();
        Thread.sleep(5000);

        System.out.println("Requesting stop");
        stoppableRunnable.requestStop();
        System.out.println("Stop request finished");

    }
    static class StoppableRunnable implements Runnable {
        private boolean stopped = false;

        public boolean isStopped() {
            return stopped;
        }

        public void requestStop(){
            this.stopped = true;
            System.out.println("Stop requested");
        }

        @Override
        public void run() {
            System.out.println("stop runnable running ");
            while(!isStopped()){
                try {
                    System.out.println("...");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("stop runnable stopped ");

        }
    }
}
