package com.ift.concurrency.cap03;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @author liufei
 * @date 2020/10/12
 */
public class CounDownLatchTest {

    private static CountDownLatch latch = new CountDownLatch(5);

    private static class CountDown extends Thread {
        @Override
        public void run() {
            Random random = new Random();
            long millis = random.nextInt(2000) + 1000;
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread [" + Thread.currentThread().getId() + "] ended......");
            latch.countDown();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            new CountDown().start();
        }
        latch.await();
        System.out.println("The Main Thread is ended......");
    }
}
