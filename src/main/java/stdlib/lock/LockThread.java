package com.java.lock;

import java.sql.Time;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class LockThread implements Runnable{
    ReentrantLock lock;
    String name;
    LockThread(ReentrantLock lock, String name) {
        this.lock = lock;
        this.name = name;
    }
    @Override
    public void run() {
        System.out.println("Starting " + name);
        try {
            System.out.println(name + " is waiting to stdlib.lock count");
            lock.lock();
            System.out.println(name + " is locking count");

            Shared.count++;
            System.out.println(name + " : " + Shared.count);

            System.out.println(name + " is sleeping");
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(name + " is unlocking count.");
            lock.unlock();
        }
    }
}
