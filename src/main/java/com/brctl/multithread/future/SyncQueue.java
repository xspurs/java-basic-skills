package com.brctl.multithread.future;

import sun.nio.ch.ThreadPool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author duanxiaoxing
 * @created 2017/8/9
 */
public class SyncQueue {
    /*
    private static ExecutorService executor = new ThreadPoolExecutor(1, 1,
            1000, TimeUnit.SECONDS,
            new SynchronousQueue<>(),
            //new ThreadPoolExecutor.DiscardPolicy());
            //new ThreadPoolExecutor.AbortPolicy());
            new ThreadPoolExecutor.CallerRunsPolicy());
            */
    /*
    虽然cached thread pool中使用了SynchronousQueue，但是由于这个构造函数中会一直创建新线程，因此不会触发Reject Policy
     */
    private static ExecutorService executor = Executors.newCachedThreadPool();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 20; i++) {
            kickOffEntry(i);
            //Thread.sleep(200);
        }

        executor.shutdown();
    }

    private static void kickOffEntry(final int index) {
        try {
            executor.submit(new Callable<Void>() {
                public Void call() throws InterruptedException {
                    System.out.println("start " + index);
                    Thread.sleep(1000); // pretend to do work
                    System.out.println("stop " + index);
                    return null;
                }
            });
        } catch (RejectedExecutionException e) {
            System.out.println("rejected execution exception");
        }
    }
}
