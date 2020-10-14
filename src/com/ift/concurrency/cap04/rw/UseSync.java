package com.ift.concurrency.cap04.rw;

/**
 * @author liufei
 * @date 2020/10/14
 */
public class UseSync implements IGoodsService {

    private GoodsInfo goodsInfo;

    public UseSync(GoodsInfo goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    @Override
    public synchronized int getNum() throws InterruptedException {
        Thread.sleep(5);
        return goodsInfo.getNum();
    }

    @Override
    public synchronized void setNum(int sealNum) throws InterruptedException {
        Thread.sleep(5);
        goodsInfo.changeNum(sealNum);
    }
}
