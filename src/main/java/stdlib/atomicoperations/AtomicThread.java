package com.java.atomicoperations;

public class AtomicThread implements Runnable {
    String name;
    AtomicThread(String name) {
        this.name = name;
    }
    @Override
    public void run() {
        System.out.println("Starting " + name);
        for (int i=1; i<=3; i++)
            System.out.println(name + " got : " + Shared.ai.getAndSet(i));
    }
}
