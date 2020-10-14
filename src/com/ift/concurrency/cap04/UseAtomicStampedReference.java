package com.ift.concurrency.cap04;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author liufei
 * @date 2020/10/13
 */
public class UseAtomicStampedReference {

    private static final AtomicStampedReference<String> asr = new AtomicStampedReference<>("init", 0);

    public static void main(String[] args) throws InterruptedException {
        String reference = asr.getReference();
        int stamp = asr.getStamp();
        System.out.println("初始值：" + reference + "，版本戳：" + stamp);
        Thread thread1 = new Thread(() -> {
            asr.compareAndSet(reference, "Thread1", stamp, stamp + 1);
            System.out.println("Thread1修改后值：" + asr.getReference() + "，Thread1修改后版本戳：" + asr.getStamp());
        });

        Thread thread2 = new Thread(() -> {
            asr.compareAndSet(reference, "Thread2", stamp, stamp + 1);
            System.out.println("Thread2修改后值：" + asr.getReference() + "，Thread2修改后版本戳：" + asr.getStamp());
        });

        thread1.start();
        thread1.join();
        thread2.start();
        thread2.join();
        System.out.println("最终值：" + asr.getReference() + "，版本戳：" + asr.getStamp());
    }
}
