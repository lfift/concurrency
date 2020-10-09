package com.ift.concurrency.cap01;

/**
 * @author liufei
 * @date 2020/9/23
 */
public class EndThread {

    private static class RunThread extends Thread {
        public RunThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            while (!isInterrupted()) {
                System.out.println(Thread.currentThread().getName());
            }
            System.out.println(Thread.currentThread().isInterrupted());
        }
    }
    public static void main(String[] args) throws InterruptedException {
        RunThread thread = new RunThread("Test");
        thread.start();
        Thread.sleep(10);
        thread.interrupt();
    }
}
