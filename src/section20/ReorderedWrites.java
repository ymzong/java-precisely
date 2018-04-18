package section20;

import java.util.Random;

/**
 * This example shows that non-synchronized writes may be "delayed" / in a surprising order.
 */
public class ReorderedWrites {

    public static void main(String[] argv) {
        int[][] tally = { {0, 0}, {0, 0} };  // keep track of winning stats of Threads A and B
        final int ITERATIONS = 5000;

        for (int i = 0; i < ITERATIONS; i++) {
            ThreadCompetition tc = new ThreadCompetition();
            Thread threadA = new Thread(tc::ThreadA);
            Thread threadB = new Thread(tc::ThreadB);
            threadA.start();
            threadB.start();
            try {
                threadA.join();
                threadB.join();
            } catch (InterruptedException e) { }

            tally[tc.AWon][tc.BWon]++;
        }

        // Sample result: 3136, 1824, 1, 39 (!)
        // If proper synchronization is added to A and B fields, the two thread should *never* win at the same time.
        System.out.format("Final stats: a=%d, b=%d, neither=%d, both=%d%n",
                tally[1][0], tally[0][1], tally[0][0], tally[1][1]);
    }
}

/**
 * This class keeps tracks of the state of "competition" between two threads.
 */
class ThreadCompetition {

    static Random seed = new Random();

    private boolean A = false, B = false;      // These two fields are intentionally non-volatile

    public int AWon = 0, BWon = 0;

    public void ThreadA() {
        try {
            Thread.sleep(seed.nextInt(3));
        } catch (InterruptedException e) { }
        A = true;   // A marks territory
        if (!B) {
            AWon = 1;
        }
    }

    public void ThreadB() {
        try {
            Thread.sleep(seed.nextInt(3));
        } catch (InterruptedException e) { }
        B = true;   // B marks territory
        if (!A) {
            BWon = 1;
        }
    }
}