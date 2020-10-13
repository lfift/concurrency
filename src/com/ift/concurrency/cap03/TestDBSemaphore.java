package com.ift.concurrency.cap03;

import java.sql.Connection;
import java.util.Random;

/**
 * @author liufei
 * @date 2020/10/13
 */
public class TestDBSemaphore {
    private static final DbSemaphore dbPool = new DbSemaphore();

    private static class Test extends Thread {
        @Override
        public void run() {
            Random random = new Random();
            long millis = random.nextInt(1000) + 1000;
            try {
                long starTime = System.currentTimeMillis();
                Connection connection = dbPool.takeConnection();
                long endTime = System.currentTimeMillis();
                System.out.println("线程 [" + getId() + "] 获取连接耗时 [" + (endTime - starTime) + "] ms......");
                Thread.sleep(millis);
                dbPool.releaseConnection(connection);
                System.out.println("归还连接......");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            new Test().start();
        }
    }
}
