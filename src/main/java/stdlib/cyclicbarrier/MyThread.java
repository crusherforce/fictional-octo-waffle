package com.java.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class MyThread implements Runnable{
    CyclicBarrier cbar;
    String name;

    MyThread(CyclicBarrier c, String n) {
        this.cbar = c;
        this.name = n;
    }

    @Override
    public void run() {
        System.out.println(name);
        try {
            cbar.await();
            // TimeUnit.SECONDS.sleep(1);
        } catch (BrokenBarrierException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
