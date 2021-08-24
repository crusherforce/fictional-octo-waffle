package com.java.synchronization;

public class Producer implements Runnable {
    Q q;
    Thread t;
    Producer(Q q) {
        this.q = q;
        this.t = new Thread(this, "Producer");
    }
    @Override
    public void run() {
        int i = 0;
        while (true) {
            q.put(i++);
        }
    }
}
