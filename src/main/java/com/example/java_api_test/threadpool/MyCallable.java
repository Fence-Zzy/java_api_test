package com.example.java_api_test.threadpool;

import java.util.concurrent.Callable;

/**
 * 有返回值任务
 */
public class MyCallable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("my name is MyCallable..");
        return 1;
    }
}
