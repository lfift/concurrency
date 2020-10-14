package com.ift.concurrency.cap04;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liufei
 * @date 2020/10/13
 */
public class UseAtomicInteger {

    private static final AtomicInteger ai = new AtomicInteger(10);

    public static void main(String[] args) {
        //10
        System.out.println("初始值为：" + ai.get());
        //10 ---> 11 return 10
        System.out.println(ai.getAndIncrement());
        //11 ---> 12 return 12
        System.out.println(ai.incrementAndGet());

    }
}
