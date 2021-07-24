package com.example.java_api_test.threadpool;

/**
 * 无返回值任务
 */
public class MyRunnable implements Runnable {
    @Override
    public void run() {
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println(System.currentTimeMillis());
        System.out.println("my name is myRunnable");
    }
}
