package com.ift.concurrency.cap03;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/**
 * @author liufei
 * @date 2020/10/13
 */
public class DbSemaphore {
    private static final int POOL_SIZE = 10;
    private static Semaphore usefull = new Semaphore(POOL_SIZE);
    private static Semaphore useless = new Semaphore(0);

    private static LinkedList<Connection> pool = new LinkedList<>();

    static {
        for (int i = 0; i < POOL_SIZE; i++) {
            pool.addLast(ConnectionImpl.fetchConnection());
        }
    }

    public Connection takeConnection() throws InterruptedException {
        Connection connection;
        usefull.acquire();
        synchronized (pool) {
            connection = pool.removeFirst();
        }
        useless.release();
        return connection;
    }

    public void releaseConnection(Connection connection) throws InterruptedException {
        if (connection == null) {
            return;
        }
        System.out.println("当前有 [" + usefull.getQueueLength() + "] 个线程等待获取连接，有 ["
                + usefull.availablePermits() + "] 个连接......");
        useless.acquire();
        synchronized (pool) {
            pool.addLast(connection);
        }
        usefull.release();
    }
}
