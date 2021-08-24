package com.java.exchanger;

import java.util.concurrent.Exchanger;

public class MakeString implements Runnable{
    Exchanger<String> ex;
    String str;
    MakeString(Exchanger<String> c) {
        ex = c;
        str = "";
    }
    @Override
    public void run() {
        char ch = 'A';
        for (int i=0; i<3; i++) {
            for (int j=0; j<5; j++) {
                str = str + ch++;
            }
            try {
                str = ex.exchange(str);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
