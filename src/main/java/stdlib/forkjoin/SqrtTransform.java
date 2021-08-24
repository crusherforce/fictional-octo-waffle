package com.java.forkjoin;

import java.util.concurrent.RecursiveAction;

public class SqrtTransform extends RecursiveAction {
    final int seqThreshold = 1000;
    double[] data;
    int start, end;
    SqrtTransform(double[] values, int s, int e) {
        data = values;
        start = s;
        end = e;
    }
    @Override
    protected void compute() {
        if ((end-start)<seqThreshold) {
            for (int i=start; i<end; i++) {
                data[i] = Math.sqrt(data[i]);
            }
        } else {
            int middle = (start + end)/2;
            invokeAll(new SqrtTransform(data, start, middle), new SqrtTransform(data, middle, end));
        }
    }
}
