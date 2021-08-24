package com.java.cyclicbarrier;

public class BarAction implements Runnable{
    @Override
    public void run() {
        System.out.println("Barrier Reached!");
    }
}
