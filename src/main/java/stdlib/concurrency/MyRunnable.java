package com.java.concurrency;

public class MyRunnable implements Runnable{

    Thread t;

    public MyRunnable(int i) {
        t = new Thread(this, "MyRunnable " + i);
    }

    @Override
    public void run() {
        try {
            for (int i=0; i<5; i++) {
                System.out.println("Child Thread: " + i);
                Thread.sleep(500);
            }
        } catch (InterruptedException iex) {
            System.out.println("Child thread Interrupted");
        }
        System.out.println("Existing child thread");
    }
}
