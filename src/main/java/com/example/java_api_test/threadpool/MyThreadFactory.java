package com.example.java_api_test.threadpool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class MyThreadFactory implements ThreadFactory {

    private final AtomicInteger count = new AtomicInteger(1);
    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setName("线程" + count.getAndIncrement() +"号");
        return t;
    }
}
