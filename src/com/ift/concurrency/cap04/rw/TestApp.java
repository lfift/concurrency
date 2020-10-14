package com.ift.concurrency.cap04.rw;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @author liufei
 * @date 2020/10/14
 */
public class TestApp {

    private static final CountDownLatch latch = new CountDownLatch(1);

    private static class ReadThread extends Thread {
        private IGoodsService goodsService;

        public ReadThread(IGoodsService goodsService) {
            this.goodsService = goodsService;
        }

        @Override
        public void run() {
            try {
                latch.await();
                long startTime = System.currentTimeMillis();
                for (int i = 0; i < 100; i++) {
                    goodsService.getNum();
                }
                long endTime = System.currentTimeMillis();
                System.out.println(getName() + " 读耗时： " + (endTime - startTime));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class WriteThread extends Thread {
        private IGoodsService goodsService;

        public WriteThread(IGoodsService goodsService) {
            this.goodsService = goodsService;
        }

        @Override
        public void run() {
            try {
                latch.await();
                Random random = new Random();
                long startTime = System.currentTimeMillis();
                for (int i = 0; i < 100; i++) {
                    Thread.sleep(50);
                    goodsService.setNum(random.nextInt(500));
                }
                long endTime = System.currentTimeMillis();
                System.out.println(getName() + "写耗时： " + (endTime - startTime));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        GoodsInfo goodsInfo = new GoodsInfo("短裤", 100, 20);
        IGoodsService goodsService = new UseReadWriteLock(goodsInfo);
//        IGoodsService goodsService = new UseSync(goodsInfo);
        for (int i = 0; i < 3; i++) {
            new WriteThread(goodsService).start();
            for (int j = 0; j < 10; j++) {
                new ReadThread(goodsService).start();
            }
        }
        latch.countDown();
    }
}
