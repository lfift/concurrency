package com.ift.concurrency.cap01;

/**
 * @author liufei
 * @date 2020/9/23
 */
public class EndRunnable {
    ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 1);

    private static class UseRunnable implements Runnable {

        @Override
        public void run() {
            Thread thread = Thread.currentThread();
            while (!thread.isInterrupted()) {
                System.out.println(thread.getName());
            }
            System.out.println(thread.isInterrupted());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        UseRunnable useRunnable = new UseRunnable();
        Thread thread = new Thread(useRunnable);
        thread.start();
        Thread.sleep(20);
        thread.interrupt();

    }
}
