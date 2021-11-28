package lesson3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TreadSafetyCounter {
    private static int TIME_LOCKING = 1000;
    private static int counter;
    private static boolean threadIsLocked;
    private static Lock lock = new ReentrantLock(true);

    public static void increment() {
        while (true) {
            try {
                lock.lock();
                threadIsLocked = true;
                System.out.println(Thread.currentThread().getName() + " : " + ++counter);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                threadIsLocked = false;
            }
        }
    }

    public static void readCounter() {
        while (true) {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " read counter... Is: " + counter);
            } finally {
                lock.unlock();
            }
        }
    }
}
