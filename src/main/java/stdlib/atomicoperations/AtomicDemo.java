package com.java.atomicoperations;

public class AtomicDemo {
    public static void main(String[] args) {
        new Thread(new AtomicThread("A")).start();
        new Thread(new AtomicThread("B")).start();
        new Thread(new AtomicThread("C")).start();
    }
}
