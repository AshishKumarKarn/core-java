package karn.core.multithreading.multithreading;

public class ReorderingRestrictionEffectOnOtherFields {

    //when a thread writes to a volatile variable, all of its previous
    //writes are guaranteed to be visible to another thread when the thread is
    //reading the same value.
    static void main() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            execute();
        }
    }

    private static void execute() {
        Task task = new Task();
        Thread t1 = new Thread(() -> {
            task.ready();
        });
        Thread t2 = new Thread(() -> {
            task.start();
        });
        t1.start();
        t2.start();
    }

    //reordering restriction in Java Memory Model
    static class Task {
         boolean ready = false;
        int a = 0;

        public void ready() {
            ready = true;
            a = 42;

        }

        public void start() {
            assert a == 42; // this will never fail because of reordering restriction on volatile variable ready
            System.out.println("checked " + a);
        }

    }
}

