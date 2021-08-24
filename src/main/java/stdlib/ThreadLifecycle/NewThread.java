package com.java.ThreadLifecycle;

import java.util.concurrent.TimeUnit;

public class NewThread implements Runnable {
    String name;
    Thread t;
    boolean suspendFlag;

    NewThread(String threadName) {
        name = threadName;
        t = new Thread(this, name);
        System.out.println("New Thread: " + t);
        suspendFlag = false;
    }

    @Override
    public void run() {
        try {
            for (int i = 15; i > 0; i--) {
                System.out.println(name + ": " + i);
                long TIMEOUT_IN_MILLISECONDS = 1000;
                TimeUnit.MILLISECONDS.sleep(TIMEOUT_IN_MILLISECONDS);
                synchronized (this) {
                    while (suspendFlag)
                        wait();
                }
            }
        } catch (InterruptedException e) {
            System.out.println(name + ": " + e.getMessage());
        }
        System.out.println(name + " exiting");
    }

    synchronized void mySuspend() {
        suspendFlag = true;
    }

    synchronized void myResume() {
        suspendFlag = false;
        notify();
    }
}
