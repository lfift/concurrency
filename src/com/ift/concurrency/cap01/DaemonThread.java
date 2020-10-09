package com.ift.concurrency.cap01;

/**
 * @author liufei
 * @date 2020/9/23
 */
public class DaemonThread {
    private static class MyDaemonThread extends Thread {
        public MyDaemonThread(String name) {
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
        MyDaemonThread thread = new MyDaemonThread("InterruptEx");
        thread.start();
        Thread.sleep(50);
        thread.interrupt();
    }
}
