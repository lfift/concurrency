package com.ift.concurrency.cap04;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author liufei
 * @date 2020/10/13
 */
public class UseAtomicReference {

    private static final AtomicReference<User> ar = new AtomicReference<>();

    private static class User {
        private String name;
        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    public static void main(String[] args) {
        ar.set(new User("Mark", 15));
        System.out.println("初始值为，姓名：" + ar.get().name + " 年龄：" + ar.get().age);
        ar.compareAndSet(ar.get(), new User("ZhangSan", 20));
        System.out.println("修改后为，姓名：" + ar.get().name + " 年龄：" + ar.get().age);
        ar.getAndUpdate(user -> new User("", 1));
    }
}
