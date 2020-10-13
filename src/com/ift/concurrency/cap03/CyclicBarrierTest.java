package com.ift.concurrency.cap03;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author liufei
 * @date 2020/10/12
 */
public class CyclicBarrierTest {

    private static final CyclicBarrier barrier = new CyclicBarrier(3);


    private static class CyclicThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                Random random = new Random();
                long millis = random.nextInt(2000) + 1000;
                try {
                    Thread.sleep(millis);
                    System.out.println("The Thread [" + this.getId() + "] is ended [" + (i + 1) + "] with [" + millis + "] ms......");
                    barrier.await();
                }catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new CyclicThread().start();
        }
        System.out.println("The Main Thread is ended......");
    }
}
