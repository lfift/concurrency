package com.ift.concurrency.cap01;

/**
 * @author liufei
 * @date 2020/10/9
 */
public class ThreadLocalUse {

    static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 1);

    static class TestThreadLocal implements Runnable {
        @Override
        public void run() {
            Integer integer = threadLocal.get();
            integer = integer + 1;
            threadLocal.set(integer);
            System.out.println(threadLocal.get());
        }
    }
    public static void main(String[] args) {
        Thread thread1 = new Thread(new TestThreadLocal(), "thread1");
        Thread thread2 = new Thread(new TestThreadLocal(), "thread2");
        Thread thread3 = new Thread(new TestThreadLocal(), "thread3");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
