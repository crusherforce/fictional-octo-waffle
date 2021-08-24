package com.java.threading;

import java.util.ArrayList;
import java.util.List;

public class SumUpExample {
    long startRange;
    long endRange;
    long counter = 0;

    static long MAX_NUM = 60000000;
    static int threads = 4;

    public SumUpExample(long startRange, long endRange) {
        this.startRange = startRange;
        this.endRange = endRange;
    }

    public void add() {
        for (long i=startRange; i<=endRange; i++)
            counter += i;
    }

    static public void multiThreads() throws InterruptedException {
        long begin = 1, step = MAX_NUM/ threads;

        long start = System.currentTimeMillis();

        List<SumUpExample> sumUpExamples = new ArrayList<>();

        for (int i = 0; i< threads; i++) {
            sumUpExamples.add(new SumUpExample(begin, begin+step-1));
            begin = begin+step;
        }

        List<Thread> threads = new ArrayList<>();
        for (SumUpExample s : sumUpExamples)
            threads.add(new Thread(s::add));

        for (Thread t : threads)
            t.start();

        for (Thread t : threads)
            t.join();

        long finalCount = 0;
        for (SumUpExample s : sumUpExamples)
            finalCount += s.counter;

        long end = System.currentTimeMillis();
        System.out.println(SumUpExample.threads + " threads final count = " + finalCount + " took " + (end - start));
    }

    static public void oneThread() throws InterruptedException {
        long start = System.currentTimeMillis();
        SumUpExample s = new SumUpExample(1, MAX_NUM);
        s.add();
        long end = System.currentTimeMillis();
        System.out.println("Single thread final count = " + s.counter + " took " + (end - start));
    }

    static public void runTest() throws InterruptedException {
        oneThread();
        multiThreads();
    }
}
