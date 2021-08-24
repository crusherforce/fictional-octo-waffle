package com.java;

import java.io.File;

public class ProcessorInfo {
    public static void main(String[] args) {
        /* Total number of processors or cores available to the JVM */
        System.out.println("Available processors (cores): " +
                Runtime.getRuntime().availableProcessors());

        /* Total amount of free memory available to the JVM */
        System.out.println("Free memory (megabytes): " +
                Runtime.getRuntime().freeMemory()/(1024*1024));

        /* This will return Long.MAX_VALUE if there is no preset limit */
        long maxMemory = Runtime.getRuntime().maxMemory();
        /* Maximum amount of memory the JVM will attempt to use */
        System.out.println("Maximum memory (megabytes): " +
                (maxMemory == Long.MAX_VALUE ? "no limit" : maxMemory/(1024*1024)));

        /* Total memory currently available to the JVM */
        System.out.println("Total memory available to JVM (megabytes): " +
                Runtime.getRuntime().totalMemory()/(1024*1024));

        System.getProperties().list(System.out);
    }
}
