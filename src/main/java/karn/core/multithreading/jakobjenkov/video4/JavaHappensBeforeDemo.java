package karn.core.multithreading.jakobjenkov.video4;

public class JavaHappensBeforeDemo {
    public static void main(String[] args) throws InterruptedException {
        FrameExchanger frameExchanger = new FrameExchanger();

        Runnable runnable1 = () -> {
            Frame frame = new Frame();
            frameExchanger.storeFrame(frame);
            System.out.println("Stored frame " + frame);
            waitASec();
            frame = new Frame();
            frameExchanger.storeFrame(frame);
            System.out.println("Stored frame " + frame);
            waitASec();
            frame = new Frame();
            frameExchanger.storeFrame(frame);
            System.out.println("Stored frame " + frame);


        };
        Runnable runnable2 = () -> {
            long now = System.currentTimeMillis();
            long future = now+3000;
            while(now<=future){
                Frame frame1 = frameExchanger.takeFrame();
                System.out.println("got frame " + frame1);
                now = System.currentTimeMillis();
            }

        };
        Thread t1 = new Thread(runnable1);
        Thread t2 = new Thread(runnable2);
        t2.start();
        t1.start();


    }

    private static void waitASec() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}

class FrameExchanger {
    private long frameTakenCount;
    private long frameStoredCount;
    private volatile boolean hasNewFrame;//try adding removing volatile here.
    private Frame frame;

    public void storeFrame(Frame frame) {
        this.frame = frame;
        this.frameStoredCount++;
        this.hasNewFrame = true;// shift it up to see behaviour.
        //if moved upward(line 57), take frame would pick old value
    }

    public Frame takeFrame() {
        while (!this.hasNewFrame) {
        }
        Frame newFrame = this.frame;
        this.frameTakenCount++;
        this.hasNewFrame = false;
        return newFrame;
    }
}

class Frame {

}