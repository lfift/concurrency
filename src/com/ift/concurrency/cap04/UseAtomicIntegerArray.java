package com.ift.concurrency.cap04;

import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * @author liufei
 * @date 2020/10/13
 */
public class UseAtomicIntegerArray {

    private static final int[] arr = {1, 2, 3};
    private static final AtomicIntegerArray aia = new AtomicIntegerArray(arr);

    public static void main(String[] args) {

    }
}
