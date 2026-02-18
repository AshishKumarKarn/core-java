package karn.core.multithreading.multithreading;

//Thread divergence actions are guaranteed to not be reordered.
//This prevents surprising outcome of actions that might never be reached.
public class ThreadDivergenceActions {
    static void main() throws InterruptedException {
        // enable assertions to see the effect of thread divergence actions. in vm arguments add -ea


        ThreadDivergence threadDivergence = new ThreadDivergence();
        Thread t1 = new Thread(
                () ->{
                    threadDivergence.thread1();
                }
        );
        Thread t2 = new Thread(
                ()->{
                    threadDivergence.thread2();
                }
        );
        t1.start();
        Thread.sleep(10);//to ensure t1 executes first
        t2.start();
    }

    static class ThreadDivergence{
        int foo=0;
        void thread1(){
            while(true){
                foo = 42;//this will never be executed.
            }
        }
        void thread2(){
            for (int i = 0; i < 100 ; i++) {
                assert foo == 0;
                System.out.println("checked "+foo);
            }
        }
    }
}
