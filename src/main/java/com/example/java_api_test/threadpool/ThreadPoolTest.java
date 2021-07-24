package com.example.java_api_test.threadpool;

import java.util.concurrent.*;

/**
 * 介绍几种线程池以及比较他们的区别，以及优缺点
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
//        testThreadPoolExecutor();           //测试线程池执行器的构造方法
//        testnewCachedThreadPool();          //测试可缓存线程池(不推荐使用)
//        testNewSingleThreadExecutor();      //测试单个线程池的情况(不推荐使用)
//        testNewFixedThreadPool();               //测试固定个数线程池的情况(不推荐使用)
//        testNewScheduledThreadPool();           //测试轮询线程池（轮询线程根据自定义的核心线程数来，即传入的值）
//        testNewSingleThreadScheduledExecutor();     //测试单个轮询线程池
        testNewWorkStealingPool();
    }

    public static void testNewWorkStealingPool(){
        ExecutorService es = Executors.newWorkStealingPool();
    }

    /**
     * 测试单个轮询线程池：ScheduledThreadPoolExecutor（该方法可能存在资源耗尽的问题，因为最大线程数为Integer.Max_Value）
     * new ThreadPoolExecutor(1,Integer.MAX_VALUE, 0,NANOSECONDS, new DelayedWorkQueue())
     */
    public static void testNewSingleThreadScheduledExecutor(){
        ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();        //只有单个轮询的线程池
        System.out.println("开始一：" + System.currentTimeMillis());
        ses.schedule(new MyRunnable(), 3000, TimeUnit.MILLISECONDS);
        System.out.println("开始二：" + System.currentTimeMillis());
        ses.schedule(new MyRunnable(), 3000, TimeUnit.MILLISECONDS);
        ses.shutdown();
    }

    /**
     * 测试轮询线程池：ScheduledThreadPoolExecutor
     * new ThreadPoolExecutor(int corePoolSize,Integer.MAX_VALUE, 0,NANOSECONDS, new DelayedWorkQueue())
     */
    public static void testNewScheduledThreadPool(){
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(6);     //核心线程数根据方法传入的值
        System.out.println("开始一：" + System.currentTimeMillis());
        ses.schedule(new MyRunnable(), 3000, TimeUnit.MILLISECONDS);
        System.out.println("开始二：" + System.currentTimeMillis());
        ses.schedule(new MyRunnable(), 3000, TimeUnit.MILLISECONDS);
        ses.shutdown();
    }

    /**
     * 测试固定个数核心线程池（该方法可能存在资源耗尽的问题，因为任务队列最大长度为Integer.Max_Value）
     * new ThreadPoolExecutor(nThreads, nThreads,0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>())
     */
    public static void testNewFixedThreadPool(){
        ExecutorService es = Executors.newFixedThreadPool(6);   //6为传入的核心线程数（该模式下全部都是核心线程数）
        es.submit(new MyRunnable());
        es.shutdown();
    }

    /**
     * 测试只有单个线程的线程池（该方法可能存在资源耗尽的问题，因为任务队列最大长度为Integer.Max_Value）
     * 默认生成的线程池：new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>())
     */
    public static void testNewSingleThreadExecutor(){
       ExecutorService es =  Executors.newSingleThreadExecutor();
       es.submit(new MyRunnable());
       es.shutdown();
    }

    /**
     *  测试newCacheThreadPool：可缓存线程池（该方法可能存在资源耗尽的问题，因为任务队列最大长度为Integer.Max_Value）
     *  默认生成的线程池：new ThreadPoolExecutor(0, Integer.MAX_VALUE,60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
     */
    public static void testnewCachedThreadPool(){
        ExecutorService es = Executors.newCachedThreadPool();
        es.submit(new MyRunnable());
        es.shutdown();
    }

    /**
     * ThreadPoolExecutor构造方法参数简介
     *  1.corePooleSize:    核心线程数（线程池不关闭，他就不会被销毁）
     *  2.maximumPoolSize：  总的线程数量（空闲（非核心）线程+核心线程）
     *  3.keppAliveTime:    存活时间（空闲线程存活时间）
     *  4.unit:             单位
     *  5.workQueue：        线程等待队列
     *  6.threadFactory:     线程工厂(生产线程)
     *  7.handler：          任务拒绝策略（同时满足一下四种条件会拒绝任务）
     *                      1.线程池中线程满了
     *                      2.无法扩容
     *                      3.没有空闲线程
     *                      4.任务队列已满
     */
    public static void testThreadPoolExecutor(){
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(2,8,60L,TimeUnit.SECONDS,new LinkedBlockingQueue<>());
        tpe.execute(new MyRunnable());
        tpe.shutdown();
    }


}
