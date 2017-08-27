package com.brctl.multithread.future;

import java.util.concurrent.*;

/**
 * FutureTask示例
 * {@link java.util.concurrent.RunnableFuture}
 * {@link java.util.concurrent.FutureTask} which implements RunnableFuture
 * @author duanxiaoxing
 * @created 2017/8/1
 */
public class RunnableFutureTask {

    static ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {
        runnableDemo();
        futureDemo();
    }

    static void runnableDemo() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("runnable demo : " + fibonacci(20));
            }
        }).start();
    }

    static void futureDemo() {
        Future<?> runnableResult = executorService.submit((Runnable) () -> fibonacci(20));  // Future<?>可以提供

        //runnableResult.cancel(true);  // cancel task

        executorService.execute(() -> fibonacci(20));

        Future<Integer> callableResult = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return fibonacci(20);
            }
        });

        FutureTask<Integer> futureTaskResult = new FutureTask<>(() -> fibonacci(20));
        // futureTaskResult.run();  // 可以直接运行FutureTask#run
        Future<?> futureTaskFutureResult = executorService.submit(futureTaskResult);

        try {
            //System.out.println("is canceled: " + runnableResult.isCancelled() + ", and is done: " + runnableResult.isDone());
            System.out.println("future result from runnable: " + runnableResult.get());
            System.out.println("future result from callable: " + callableResult.get());
            System.out.println("future result from futuretask: " + futureTaskResult.get());
            System.out.println("future result from futuretask + future: " + futureTaskFutureResult.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();  // shutdown executorService
        }
    }

    static int fibonacci(int num) {
        if (num < 0) {
            throw new IllegalArgumentException();
        }
        if (num <= 1) {
            return num;
        }
        return fibonacci(num - 2) + fibonacci(num - 1);
    }
}
