package section20;

/**
 * This class demonstrates a simple example of mutual exclusion between threads by using synchronized statement.
 */
public class MutualExclusionDemo {

    public static void main(String[] argv) throws InterruptedException {
        /* Show the result from SyncedPrinterThread -- each should be - and | interleaving perfectly */
        for (int i = 0; i < 10; i++) {
            Thread t1 = new SyncedPrinterThread();
            Thread t2 = new SyncedPrinterThread();

            t1.start();
            t2.start();
            t1.join();
            t2.join();
            System.out.println();
        }

        System.out.println();

        /**
         * Show the result from UnsyncedPrinterThread -- should show continuous -'s and/or |'s due to lack of
         * mutual exclusion inside the for loop with prints.
         */
        for (int i = 0; i < 10; i++) {
            Thread t1 = new UnsyncedPrinterThread();
            Thread t2 = new UnsyncedPrinterThread();

            t1.start();
            t2.start();
            t1.join();
            t2.join();
            System.out.println();
        }
    }

}

/**
 * Each SyncedPrinterThread prints "-|" 15 times, and uses a mutex to make sure that only one thread can print at a time.
 */
class SyncedPrinterThread extends Thread {

    /**
     * Mutex object that guarantees mutual exclusion inside the for loop between different PrinterThreads.
     * This guarantees that while one thread prints "-|", no other SyncedPrinterThread can interrupt and
     * print something else.
     */
    final static Object mutex = new Object();
    final static int PRINT_COUNT = 15;

    public void run() {
        for (int i = 0; i < PRINT_COUNT; i++) {
            synchronized (mutex) {  // attempt to grab the mutex (lock)
                System.out.print("-");
                System.out.print("|");
            }
        }
    }
}

/**
 * Each UnsyncedPrinterThread still prints "-|" 15 times, but this time without the synchronized statement inside
 * the for loop. This can lead to unpredictable behaviors.
 */
class UnsyncedPrinterThread extends Thread {

    final static int PRINT_COUNT = 15;

    public void run() {
        for (int i = 0; i < PRINT_COUNT; i++) {
            // Warning: this is bad since multiple threads can try to print at the same time
            // and thus create unpredictable patterns!
            System.out.print("-");
            System.out.print("|");
        }
    }
}