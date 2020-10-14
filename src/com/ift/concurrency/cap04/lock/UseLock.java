package com.ift.concurrency.cap04.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liufei
 * @date 2020/10/14
 */
public class UseLock {

    private static int num = 0;

    private static class TestLock {
        private static Lock lock = new ReentrantLock();

        public static void lock() {
            lock.lock();
            try {
                num++;
                System.out.println(num);
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        TestLock.lock();
    }
}
