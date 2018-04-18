package section20;

import java.io.IOException;

/**
 * This example notes that field writes can remain forever invisible without proper synchronization.
 */
public class InvisibleWrites {

    public static void main(String[] argv) throws IOException {
        MutableInt mi = new MutableInt(0);

        Thread worker = new Thread(new Runnable() {     // Recall the shorthand syntax for implementing an interface
            public void run() {
                while (mi.get() == 0) { }               // Hangs until value is changed
            }
        });
        worker.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) { }

        System.out.println("Writing value field to 42");
        mi.set(42);

        /* The worker may never finish since the write to value field from main thread is never visible from worker. */
        try {
            worker.join();
        } catch (InterruptedException e) { }
    }
}

/**
 * Trivial non thread-safe wrapper for an integer. Since value field is not volatile, invoking set() from one thread
 * does not guarantee that the new value will be visible to another thread invoking get().
 */
class MutableInt {
    private int value;  // Intentionally leaving out `volatile`

    public MutableInt(int value) {
        this.value = value;
    }

    public void set(int newValue) {
        this.value = newValue;
    }

    public int get() {
        return value;
    }
}