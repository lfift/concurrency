package com.ift.concurrency.cap03;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author liufei
 * @date 2020/10/13
 */
public class UseFuture {

    private static class UseCallable implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            Thread.sleep(100);
            System.out.println("开始计算......");
            int result = 0;
            for (int i = 0; i < 5000; i++) {
                result += i;
            }
            return result;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        UseCallable useCallable = new UseCallable();
        FutureTask<Integer> futureTask = new FutureTask<>(useCallable);
        new Thread(futureTask).start();
        Random random = new Random();
        Thread.sleep(200);
        if (random.nextBoolean()) {
            System.out.println("Result is " + futureTask.get());
        } else {
            System.out.println("中断计算......");
            futureTask.cancel(true);
        }
    }
}
