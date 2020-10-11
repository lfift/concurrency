package com.ift.concurrency.cap02;

/**
 * @author liufei
 * @date 2020/10/11
 */
public class Express {

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

    public synchronized void changeKm() {
        this.km = 101;
        notifyAll();
    }

    public synchronized void changeSite() {
        this.site = "ShangHai";
        notifyAll();
    }

    public synchronized void waitKm() {
        while (this.km < 100) {
            try {
                wait();
                System.out.println("check km thread ["
                        + Thread.currentThread().getId()  + "] is not be notified");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("the km is " + this.km + " i will running");
    }

    public synchronized void waitSite() {
        while (CITY.equals(this.site)) {
            try {
                wait();
                System.out.println("check site thread ["
                        + Thread.currentThread().getId() + "] is not be notified");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("the site is " + this.site + " i will running");
    }

}
