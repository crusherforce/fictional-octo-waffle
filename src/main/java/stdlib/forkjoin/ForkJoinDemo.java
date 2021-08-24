package com.java.forkjoin;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinDemo {
    public static void main(String[] args) {
        long start, end;
        int count = Integer.MAX_VALUE/16;
        /*
        Time taken for multicore program (milliseconds) = 798
        Time taken for single core program (milliseconds) = 1117
         */

        start = System.currentTimeMillis();
        ForkJoinPool fjp = new ForkJoinPool();
        double[] nums = new double[count];
        for (int i=0; i<nums.length; i++)
            nums[i] = i;
//        for (int i=0; i<10; i++)
//            System.out.print(nums[i] + " ");
//        System.out.println("\n");
        SqrtTransform task = new SqrtTransform(nums, 0, nums.length);
        fjp.invoke(task);
//        for (int i=0; i<10; i++)
//            System.out.format("%.4f ", nums[i]);
//        System.out.println("\n");
        end = System.currentTimeMillis();
        System.out.println("Time taken for multi core program (milliseconds) = " + (end-start));

        start = System.currentTimeMillis();
        double[] nums1 = new double[count];
        for (int i=0; i<nums1.length; i++)
            nums1[i] = Math.sqrt(i);
        end = System.currentTimeMillis();
        System.out.println("Time taken for single core program (milliseconds) = " + (end-start));
    }
}
