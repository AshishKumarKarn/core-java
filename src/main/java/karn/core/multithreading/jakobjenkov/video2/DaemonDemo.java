package karn.core.multithreading.jakobjenkov.video2;

//JVM will keep on running
// if any of the thread is running.
// Like even if main thread exists, JVM won't stop executing other thread.
// if you set a thread as daemon, it will stop as soon as all other
//non-daemon thread stops.
//It acts as a background thread
public class DaemonDemo {

    public static void main(String[] args) {
        Runnable runnable = () -> {
                while(true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    System.out.println("Executing "+Thread.currentThread().getName());
                }
        };
        Thread thread = new Thread(runnable);
        // thread.setDaemon(true); has to be set before starting the thread.

        thread.start();
        System.out.println("Main thread end here "+Thread.currentThread().getName());
        //main threads ends here. But JVM still runs

    }
}
