package com.ift.concurrency.cap03;

import java.util.concurrent.Exchanger;

/**
 * @author liufei
 * @date 2020/10/13
 */
public class TestExchange {

    private static final Exchanger<String> exchange = new Exchanger<>();

    public static void main(String[] args) {
        new Thread(() -> {
            String message = "Thread1";
            try {
                String msg = exchange.exchange(message);
                System.out.println("交换前：" + message + ", 交换后：" + msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            String message = "Thread2";
            try {
                String msg = exchange.exchange(message);
                System.out.println("交换前：" + message + ", 交换后：" + msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }
}
