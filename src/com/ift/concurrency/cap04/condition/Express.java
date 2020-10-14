package com.ift.concurrency.cap04.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liufei
 * @date 2020/10/11
 */
public class Express {

    private Lock kmLock = new ReentrantLock();
    private Lock siteLock = new ReentrantLock();
    private Condition kmCondition = kmLock.newCondition();
    private Condition siteCondition = siteLock.newCondition();

    public static final String CITY = "ChongQing";
    private int km;
    private String site;

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Express(int km, String site) {
        this.km = km;
        this.site = site;
    }

    public void changeKm() {
        kmLock.lock();
        try {
            this.km = 101;
            kmCondition.signalAll();
        } finally {
            kmLock.unlock();
        }
    }

    public void changeSite() {
        siteLock.lock();
        try{
            this.site = "ShangHai";
            siteCondition.signal();
        } finally {
            siteLock.unlock();
        }
    }

    public void waitKm() {
        kmLock.lock();
        try {
            while (this.km < 100) {
                try {
                    kmCondition.await();
                    System.out.println("check km thread ["
                            + Thread.currentThread().getId()  + "] is be notified");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("the km is " + this.km + " i will running");
        } finally {
            kmLock.unlock();
        }
    }

    public void waitSite() {
        siteLock.lock();
        try {
            while (CITY.equals(this.site)) {
                try {
                    siteCondition.await();
                    System.out.println("check site thread ["
                            + Thread.currentThread().getId() + "] is be notified");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("the site is " + this.site + " i will running");
        } finally {
            siteLock.unlock();
        }
    }

}
