package com.java.exchanger;

import java.util.concurrent.Exchanger;

public class UseString implements Runnable {
    Exchanger<String> ex;
    String str;
    UseString(Exchanger<String> c) {
        ex = c;
    }
    @Override
    public void run() {
        for (int i=0; i<3; i++) {
            try {
                str = ex.exchange("");
                System.out.println("Got: " + str);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
