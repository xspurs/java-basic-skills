package com.brctl.multithread.future.guava;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.JdkFutureAdapters;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * ListenableFutureTask示例
 * {@link ListenableFutureTask}
 * @author duanxiaoxing
 * @created 2017/8/11
 */
@Slf4j
@SuppressWarnings("unchecked")
public class ListenableFutureDemo {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        // create ListenableFutureTask instance
        ListenableFutureTask<String> listenableFutureTask = ListenableFutureTask.create(() -> {
            log.info("listenable future begin to execute");
            TimeUnit.SECONDS.sleep(5);  // monitor
            return "listenable future task done";
        });
        // add three listeners, use MoreExecutors.directExecutor() to execute Runnable in ExecutorServcie
        listenableFutureTask.addListener(()-> log.info("listener one execute"), MoreExecutors.directExecutor());
        listenableFutureTask.addListener(() -> log.info("listener two execute"), MoreExecutors.directExecutor());
        listenableFutureTask.addListener(() -> log.info("listener three execute"), MoreExecutors.directExecutor());
        // execute listenable future task
        executorService.execute(listenableFutureTask);
        // ExecutorService.shutdown()
        executorService.shutdown();
    }

}
