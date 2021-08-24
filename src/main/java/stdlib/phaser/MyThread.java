package com.java.phaser;

import java.sql.Time;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class MyThread implements Runnable{
    Phaser phaser;
    String name;
    MyThread(Phaser phaser, String name) {
        this.phaser = phaser;
        this.name = name;
        this.phaser.register();
    }
    @Override
    public void run() {
        System.out.println("Thread " + name + " Beginning Phase One");
        phaser.arriveAndAwaitAdvance();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Thread " + name + " Beginning Phase Two");
        phaser.arriveAndAwaitAdvance();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Thread " + name + " Beginning Phase Three");
        phaser.arriveAndDeregister();
    }
}
