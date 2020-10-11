package com.ift.concurrency.cap02;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liufei
 * @date 2020/10/11
 */
public class DBPoolTest {

    private static DBPool pool = new DBPool(10);
    private static CountDownLatch end;

    private static class Worker implements Runnable {
        private int count;
        private AtomicInteger got;
        private AtomicInteger notGot;
        public Worker(int count, AtomicInteger got, AtomicInteger notGot) {
            this.count = count;
            this.got = got;
            this.notGot = notGot;
        }

        @Override
        public void run() {
            while (count > 0) {
                try {
                    Connection connection = pool.fetchConnection(1000);
                    if (connection != null) {
                        try {
                            connection.createStatement();
                            connection.commit();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        } finally {
                            pool.releaseConnection(connection);
                            got.incrementAndGet();
                        }
                    } else {
                        notGot.incrementAndGet();
                        System.out.println("the thread [" + Thread.currentThread().getName() + "] wait timeout......");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    count--;
                }
            }
            end.countDown();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int threadCount = 50;
        int count = 10;
        AtomicInteger got = new AtomicInteger();
        AtomicInteger notGot = new AtomicInteger();
        end = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            new Thread(new Worker(count, got, notGot), "Worker_" + i).start();
        }
        end.await();
        System.out.println("总共尝试了: " + (threadCount * count));
        System.out.println("拿到连接的次数：  " + got);
        System.out.println("没能连接的次数： " + notGot);
    }


}
