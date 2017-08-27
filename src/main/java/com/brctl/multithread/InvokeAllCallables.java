package com.brctl.multithread;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ExecutorService.invokeAll(Collection<? extends Callable> callable)示例
 * {@link java.util.concurrent.ExecutorService}
 * @author duanxiaoxing
 * @created 2017/8/13
 */
public class InvokeAllCallables {

    private static final int TASK_COUNT = 5;

    public static void main(String[] args) throws InterruptedException {
        List<Callable<String>> callables = Lists.newArrayList();
        for (int i = 0; i < TASK_COUNT; i++) {
            callables.add(() -> "callable execute successfully");
        }
        ExecutorService executorService = Executors.newFixedThreadPool(TASK_COUNT);
        List<Future<String>> futures = executorService.invokeAll(callables);
    }
}
