package com.java.ThreadLifecycle;

import java.util.concurrent.TimeUnit;

public class SuspendResume {
    public static void main(String[] args) {
        NewThread ob1 = new NewThread("One");
        NewThread ob2 = new NewThread("Two");

        ob1.t.start();
        ob2.t.start();

        try {
            long TIMEOUT_IN_MILLISECONDS = 3000;

            TimeUnit.MILLISECONDS.sleep(TIMEOUT_IN_MILLISECONDS);

            ob1.mySuspend();
            System.out.println("Suspending Thread One");

            TimeUnit.MILLISECONDS.sleep(TIMEOUT_IN_MILLISECONDS);

            ob1.myResume();
            System.out.println("Resuming Thread One");

            TimeUnit.MILLISECONDS.sleep(TIMEOUT_IN_MILLISECONDS);

            ob2.mySuspend();
            System.out.println("Suspending Thread Two");

            TimeUnit.MILLISECONDS.sleep(TIMEOUT_IN_MILLISECONDS);

            ob2.myResume();
            System.out.println("Resuming Thread Two");
        } catch (InterruptedException iex) {
            System.out.println(iex.getMessage());
        }

        try {
            System.out.println("Waiting for threads to finish");
            ob1.t.join();
            ob2.t.join();
        } catch (InterruptedException iex) {
            System.out.println(iex.getMessage());
        }
    }
}
