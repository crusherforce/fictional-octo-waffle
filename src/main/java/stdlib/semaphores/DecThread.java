package com.java.semaphores;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class DecThread implements Runnable {
    String name;
    Semaphore sem;

    DecThread(String name, Semaphore sem) {
        this.name = name;
        this.sem = sem;
    }

    @Override
    public void run() {
        System.out.println("Starting " + name);
        try {
            sem.acquire();
            System.out.println(name + " waiting to for permit");
            for (int i=0; i<5; i++) {
                Shared.count--;
                System.out.println(name + ":" + Shared.count);
                TimeUnit.MILLISECONDS.sleep(250);
            }
        } catch (InterruptedException iex) {
            System.out.println(iex.getMessage());
        }
        System.out.println(name + " releasing permit");
        sem.release();
    }
}
