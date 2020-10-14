package com.ift.concurrency.cap04.rw;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author liufei
 * @date 2020/10/14
 */
public class UseReadWriteLock implements IGoodsService {

    private GoodsInfo goodsInfo;
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock getLock = lock.readLock();
    private Lock setLock = lock.writeLock();

    public UseReadWriteLock(GoodsInfo goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    @Override
    public int getNum() throws InterruptedException {
        getLock.lock();
        try {
            Thread.sleep(5);
            return goodsInfo.getNum();
        } finally {
            getLock.unlock();
        }
    }

    @Override
    public void setNum(int sealNum) throws InterruptedException {
        setLock.lock();
        try {
            Thread.sleep(5);
            goodsInfo.changeNum(sealNum);
        } finally {
            setLock.unlock();
        }
    }
}
