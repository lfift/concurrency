package com.ift.concurrency.cap04.rw;

/**
 * @author liufei
 * @date 2020/10/14
 */
public interface IGoodsService {

    int getNum() throws InterruptedException;

    void setNum(int sealNum) throws InterruptedException;
}
