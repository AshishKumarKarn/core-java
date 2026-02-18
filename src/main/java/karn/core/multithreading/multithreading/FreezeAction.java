package karn.core.multithreading.multithreading;

public class FreezeAction {
    // without final keyword
    //Thread 1:
    //    .
    //    .
    //  instance = <allocate>
    //  instance.<init> this is a constructor call
    //
    //Thread 2:
    //  if(instance!=null)
    //     assert instance.foo == 42; //might fail

    // with final keyword
    //Thread 1:
    //    .
    //    .
    //  instance = <allocate>
    //  instance.foo = 42
    //  <freeze instance.foo>
    //
    //Thread 2:
    //  if(instance!=null)
    //     assert instance.foo == 42;

    //The final fields are freezed and all threads can see what is being set in constructor


    // Without final (or other synchronization/volatile/atomic constructs)
    // the publication is unsafe: another thread can observe publication != null
    // while seeing a stale/partially-initialized object (e.g. foo == 0)
    // because of reordering/visibility rules in the Java Memory Model.
    // It often "works" due to timing, prints, and cache coherence,
    // but it is not guaranteed.
    //Fixes (pick one): make foo final, or declare publication volatile,
    // or synchronize access / use AtomicReference. Example below uses final and
    // joins threads to ensure deterministic completion.
    static void main() throws InterruptedException {
        for (int i = 0; i < 10000; i++) {
            executePublication();
        }
    }

    private static void executePublication() throws InterruptedException {
        UnsafePublication publication = new UnsafePublication();
        Thread t1 = new Thread(
                () -> {
                    publication.work1();
                }
        );
        Thread t2 = new Thread(
                () -> {
                    publication.work2();
                }
        );

        t1.start();
        t2.start();

    }

    static class UnsafePublication {
        int foo; //change it to final

        UnsafePublication() {
            foo = 42;
        }

        static UnsafePublication publication;

        public static void work1() {
            publication = new UnsafePublication();
        }

        public static void work2() {
            if (publication != null) {
                assert publication.foo == 42;
            }
        }
    }
}
