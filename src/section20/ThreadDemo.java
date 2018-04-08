package section20;

import java.io.IOException;

/**
 * This class demonstrates a simple example of Thread creation.
 */
public class ThreadDemo {

    public static void main(String[] argv) throws IOException {
        // Create a new Incrementer thread and start it
        Incrementer incThread = new Incrementer();
        incThread.start();
        System.out.println("Press ENTER to get current value of i...");

        while (true) {
            System.in.read();
            System.out.println(incThread.i);    // Get value of the counter
        }
    }
}

/**
 * A thread is created and controlled with an object of Thread class (implementing Runnable interface).
 * public void run() should be overridden.
 */
class Incrementer extends Thread {

    public volatile int i;  // Counter to be incremented

    public void run() {
        while (true) {
            i++;
            yield();        // Change the thread from Running to Enabled (to allow other threads to run)
        }
    }
}