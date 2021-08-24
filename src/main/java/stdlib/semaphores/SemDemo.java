package com.java.semaphores;

import java.util.concurrent.Semaphore;

public class SemDemo {
    public static void main(String[] args) {
        Semaphore sem = new Semaphore(2);
        new Thread(new IncThread("A", sem)).start();
        new Thread(new DecThread("B", sem)).start();
    }
}
