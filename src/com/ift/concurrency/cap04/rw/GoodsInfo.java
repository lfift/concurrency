package com.ift.concurrency.cap04.rw;

/**
 * @author liufei
 * @date 2020/10/14
 */
public class GoodsInfo {

    private String name;
    private int num;
    private double mony;

    public GoodsInfo(String name, int num, double mony) {
        this.name = name;
        this.num = num;
        this.mony = mony;
    }

    public int getNum() {
        return num;
    }

    public double getMony() {
        return mony;
    }

    public void changeNum(int sealNum) {
        this.mony += sealNum * 50;
        this.num -= sealNum;
    }
}
