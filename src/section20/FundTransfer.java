package section20;

import java.util.ArrayList;
import java.util.List;

/**
 * This class demonstrates the use of synchronized method to guarantee mutual exclusion
 * between potentially conflicting threads.
 */
public class FundTransfer {

    /**
     * Spawns 30 tellers that transfer fund from Account A to Account B and executes them concurrently.
     *
     * When allSynced is true, all 30 tellers invoke synchronized transferAtoB method;
     * When allSynced is false, 15 invoke synced transferAtoB method, and another 15 invoke unsynced rogueTransferAtoB.
     */
    public static void testSyncedTellers(boolean allSynced) {
        System.out.println("Initializing a new bank...");
        Bank bank = new Bank();

        List<Thread> tellers = new ArrayList<>();   // Keep 30 [Rogue]Teller threads that transfer funds
        for (int i = 0; i < 30; i++) {
            Thread t = (i < 15 || allSynced) ? new Teller(bank) : new RogueTeller(bank);    // first 15 always synced
            tellers.add(t);
            t.start();                              // Thread.start() for asynchronous execution, as opposed to .run()
        }

        System.out.println("Waiting for all tellers to finish...");
        tellers.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) { }
        });

        System.out.println(bank);
    }


    public static void main(String[] argv) {
        testSyncedTellers(true);    // total amount is always 100000000
        testSyncedTellers(false);   // total amount is likely to be different from 100000000
    }

}

/**
 * Bank represents a bank with two accounts, and their balances are tracked.
 *
 * The `transfer` methods withdraw fund from Account A and add it to Account B; however, one method uses
 * synchronization and the other does not.
 */
class Bank {
    private int acctA = 100000000;
    private int acctB = 0;

    synchronized public void transferAtoB(int amount) {
        acctA -= amount;
        sleep(25);
        acctB += amount;
    }

    public void rogueTransferAtoB(int amount) {
        acctA -= amount;
        sleep(25);
        acctB += amount;
    }

    public String toString() {
        return String.format("Bank (acctA = %d, acctB = %d, total = %d)", acctA, acctB, acctA + acctB);
    }

    // helper method that sleeps for some milliseconds
    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            return;
        }
    }
}

/**
 * This class represents a bank teller who transfers fund from Account A to B for 10000 times.
 * Multiple Tellers can execute at the same time, but their operations are protected by synchronization.
 */
class Teller extends Thread {

    private Bank bank;      // Bank that the clerk is working with

    public Teller(Bank b) {
        this.bank = b;
    }

    public void run() {
        for (int i = 0; i < 30; i++) {
            bank.transferAtoB(1);
        }
    }
}

/**
 * This class represents a rogue bank teller who transfers account balance without synchronization.
 * Multiple RogueTellers can execute at the same time, leading to unexpected behaviors.
 */
class RogueTeller extends Thread {

    private Bank bank;      // Bank that the clerk is working with

    public RogueTeller(Bank b) {
        this.bank = b;
    }

    public void run() {
        for (int i = 0; i < 30; i++) {
            bank.rogueTransferAtoB(1);  // this method does not have `synchronized` modifier!
        }
    }
}