package com.ift.concurrency.cap02;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * @author liufei
 * @date 2020/10/11
 */
public class DBPool {

    private static LinkedList<Connection> pool = new LinkedList<>();

    public DBPool(int initialSize) {
        if (initialSize > 0) {
            for (int i = 0; i < initialSize; i++) {
                pool.addLast(ConnectionImpl.fetchConnection());
            }
        }
    }

    public Connection fetchConnection(long millis) throws InterruptedException {
        synchronized (pool) {
            if (millis < 0) {
                while (pool.isEmpty()) {
                    wait();
                }
                return pool.removeFirst();
            } else {
                long overTime = System.currentTimeMillis() + millis;
                long remain = millis;
                while (pool.isEmpty() && remain > 0) {
                    pool.wait(remain);
                    remain = overTime - System.currentTimeMillis();
                }
                if (pool.isEmpty()) {
                    return null;
                }
                return pool.removeFirst();
            }
        }
    }

    public void releaseConnection(Connection connection) {
        synchronized (pool) {
            if (connection == null) {
                return;
            }
            pool.addLast(connection);
            pool.notifyAll();
        }
    }
}
