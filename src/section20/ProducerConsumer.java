package section20;

import java.util.ArrayList;
import java.util.List;

/**
 * This class demonstrates a simplistic producer-consumer model using wait() and notifyAll().
 */
public class ProducerConsumer {

    public static void main(String[] argv) {
        // Initialize a new Buffer and its Reader and Writer threads
        Buffer buffer = new Buffer();
        BufferReader br = new BufferReader(buffer);
        br.start();

        List<BufferWriter> bwList = new ArrayList<>();
        int bufferWriterCount = BufferReader.READ_COUNT / BufferWriter.WRITE_COUNT;
        for (int i = 0; i < bufferWriterCount; i++) {
            BufferWriter bw = new BufferWriter(buffer, 100000 * i);
            bwList.add(bw);
            bw.start();
        }

        // Wait for all threads spawn above to finish
        try {
            br.join();
            for (BufferWriter bw : bwList) {
                bw.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


/**
 * This monitor allows multiple readers and writers to access the buffer with room for one integer.
 */
class Buffer {

    private int value;
    private boolean empty = true;

    /* Attempt to put a value into the buffer when it's empty; block otherwise */
    synchronized public void put(int value) {
        // Wait for buffer to become empty
        while (!empty) {
            try { this.wait(); } catch (InterruptedException e) { }
        }

        // From this point, running thread has exclusive access to the buffer
        empty = false;
        this.value = value;
        this.notifyAll();       // notify all threads in the waiting set (actually only reader threads)
    }

    /* Attempt to read the value from buffer and clear it; block when empty */
    synchronized public int get() {
        // Wait for a value in the buffer first
        while (empty) {
            try { this.wait(); } catch (InterruptedException e) { }
        }

        // From this point, running thread has exclusive access to the buffer
        empty = true;
        this.notifyAll();       // notify all threads in waiting set (actually only writer threads)
        return value;
    }
}


/**
 * The reader thread reads values from the Buffer above and prints them
 */
class BufferReader extends Thread {

    Buffer buffer;                          // buffer to read from
    public static final int READ_COUNT = 1024;     // number of reads attempted per thread

    public BufferReader(Buffer b) {
        buffer = b;
    }

    public void run() {
        for (int i = 0; i < READ_COUNT; i++) {
            System.out.format("Read value from buffer: %d%n", buffer.get());
        }
    }
}

/**
 * The writer thread writes values to the buffer above
 */
class BufferWriter extends Thread {

    Buffer buffer;                          // buffer to write to
    int offset;                             // offset for all values written to the buffer (for uniqueness)
    public static final int WRITE_COUNT = 64;      // number of writers attempted per thread

    public BufferWriter(Buffer b, int offset) {
        buffer = b;
        this.offset = offset;
    }

    public void run() {
        for (int i = 0; i < WRITE_COUNT; i++) {
            buffer.put(offset + i);
            System.out.format("Wrote value to buffer: %d%n", offset + i);
        }
    }
}