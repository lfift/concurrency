package com.ift.concurrency.cap01;

/**
 * @author liufei
 * @date 2020/9/23
 */
public class HasInterruptEx {
    private static class InterruptThread extends Thread {
        public InterruptThread(String name) {
            super(name);
        }
        @Override
        public void run() {
            while (!isInterrupted()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("Exception The Interrupted Flag is " + isInterrupted());
                    Thread.currentThread().interrupt();
                }
                System.out.println(Thread.currentThread().getName());
            }
            System.out.println("Ended The Interrupted Flag is " + isInterrupted());
        }
    }
    public static void main(String[] args) throws InterruptedException {
        InterruptThread thread = new InterruptThread("InterruptEx");
        thread.start();
        Thread.sleep(50);
        thread.interrupt();
    }
}
