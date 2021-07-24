package com.example.java_api_test.threadpool;

import java.util.concurrent.*;

/**
 * 该类用于验证executor和submitt提交方式的区别
 */
public class ExecutorAndSubmitDiff {
    public static void main(String[] args) {
//        testExecute();              //execute只能提交runnable任务
        testSubmit();               //submit可以runnable，也可也callable

    }

    /**
     * execure返回没有返回值
     */
    public static void testExecute(){
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(2,8,60L,TimeUnit.SECONDS,new SynchronousQueue<Runnable>());
        tpe.execute(new MyRunnable());
        tpe.shutdown();
    }

    /**
     * submit，执行的任务可以有返回值
     */
    public static void testSubmit(){
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(2,8,60L,TimeUnit.SECONDS,new SynchronousQueue<Runnable>());
        tpe.execute(new MyRunnable());
        Future<Integer> result = tpe.submit(new MyCallable());
        try {
            System.out.println("输出结果为：" + result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        tpe.shutdown();
    }
}
