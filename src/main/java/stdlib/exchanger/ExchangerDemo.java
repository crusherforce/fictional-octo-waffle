package com.java.exchanger;

import java.util.concurrent.Exchanger;

public class ExchangerDemo {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();
        new Thread(new UseString(exchanger)).start();
        new Thread(new MakeString(exchanger)).start();
    }
}
