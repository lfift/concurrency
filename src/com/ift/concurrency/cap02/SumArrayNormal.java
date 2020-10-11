package com.ift.concurrency.cap02;

import java.util.concurrent.TimeUnit;

/**
 * @author liufei
 * @date 2020/10/11
 */
public class SumArrayNormal {

    public static void main(String[] args) throws InterruptedException {
        int[] arrays = MakeArray.makeArray();
        int count = 0;
        long startTime = System.currentTimeMillis();
        for (int array : arrays) {
            TimeUnit.MILLISECONDS.sleep(1);
            count = count + array;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("结果为：" + count);
        System.out.println("耗时：" + (endTime - startTime) + " ms.");
    }
}
