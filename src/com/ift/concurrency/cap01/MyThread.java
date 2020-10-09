package com.ift.concurrency.cap01;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author liufei
 * @date 2020/9/22
 */
public class MyThread {

    private static class NewRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println(this.getClass().getName());
        }
    }

    private static class NewThread extends Thread {
        @Override
        public void run() {
            System.out.println(this.getClass().getName());
        }
    }

    private static class NewCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            return this.getClass().getName();
        }
    }

    public static void main(String[] args) throws Exception {
        Thread thread = new NewThread();
        thread.start();

        Runnable runnable = new NewRunnable();
        Thread runThread = new Thread(runnable);
        runThread.start();


        Callable<String> callable = new NewCallable();
        FutureTask<String> futureTask = new FutureTask<>(callable);
        new Thread(futureTask).start();
        System.out.println(futureTask.get());
    }
}
