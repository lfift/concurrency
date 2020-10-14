package com.ift.concurrency.cap04.condition;

import java.util.concurrent.TimeUnit;

/**
 * @author liufei
 * @date 2020/10/11
 */
public class TestExpress {

    private static Express express = new Express(0, Express.CITY);
    
    private static class CheckKm extends Thread {
        @Override
        public void run() {
            express.waitKm();
        }
    }
    private static class CheckSite extends Thread {

        @Override
        public void run() {
            express.waitSite();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            new CheckKm().start();
        }

        for (int i = 0; i < 3; i++) {
            new CheckSite().start();
        }

        TimeUnit.MILLISECONDS.sleep(1000);
        express.changeKm();
    }
}
