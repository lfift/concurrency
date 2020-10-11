package com.ift.concurrency.cap02;

import java.util.Random;

/**
 * @author liufei
 * @date 2020/10/11
 */
public class MakeArray {

    public static final int SIZE = 10000;

    public static int[] makeArray() {
        int[] array = new int[SIZE];
        Random random = new Random();
        for (int i = 0; i < SIZE; i++) {
            array[i] = random.nextInt(SIZE * 3);
        }
        return array;
    }
}
