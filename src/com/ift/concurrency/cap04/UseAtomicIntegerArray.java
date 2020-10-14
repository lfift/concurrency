package com.ift.concurrency.cap04;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author liufei
 * @date 2020/10/13
 */
public class UseAtomicIntegerArray {

    private static final int[] arr = {1, 2, 3};
    private static final AtomicIntegerArray aia = new AtomicIntegerArray(arr);

    public static void main(String[] args) {
        aia.compareAndSet(0, 1, 3);
        System.out.println(aia.get(0));
    }
}
