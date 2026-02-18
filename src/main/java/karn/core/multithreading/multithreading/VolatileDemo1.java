package karn.core.multithreading.multithreading;

public class VolatileDemo1 {

    static void main() throws InterruptedException {
        Task task1 = new Task();
        Thread t1 = new Thread(
                task1::increaseCounter
        );
        Thread t2 = new Thread(
                task1::setStopFlagTrue
        );

        t1.start();
        Thread.sleep(100);//to ensure t1 starts first
        t2.start();
    }

    /**
     * An eventual inconsistency optimization : the stopFlag is at line 33 is replaced with true literal
     * stopFlag is not volatile (nor otherwise synchronized).
     * Without volatile/synchronization there is no guaranteed visibility
     * between threads; the loop may never see the update (possible infinite loop).
     * It often "works" because of timing, thread scheduling, Thread.sleep / System.out.println
     * side-effects and CPU cache coherence which frequently make the write
     * visible in practice, but this is not guaranteed by the Java Memory Model.
     */
    static class Task {
        private boolean stopFlag;

        public void increaseCounter() {
            int count = 0;
            while (!stopFlag) {
                count++;
                //sout(count)
            }
        }

        public void setStopFlagTrue() {
            stopFlag = true;
        }
    }
}

