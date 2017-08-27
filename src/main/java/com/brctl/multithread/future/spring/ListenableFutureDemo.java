package com.brctl.multithread.future.spring;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.util.concurrent.ListenableFutureTask;

import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * ListenableFutureTask示例
 * {@link org.springframework.util.concurrent.org.springframework.util.concurrent.ListenableFutureTask}
 * @author duanxiaoxing
 * @created 2017/8/12
 */
@Slf4j
@SuppressWarnings("unchecked")
public class ListenableFutureDemo {

    public static void main(String[] args) {
        ListenableFutureTask listenableFutureTask = new ListenableFutureTask(() -> {
            log.info("listenable future begin to execute");
            TimeUnit.SECONDS.sleep(5);
            return "listenable future task done";
        });

        listenableFutureTask.addCallback(new ListenableFutureCallback() {
            @Override
            public void onFailure(Throwable ex) {
                log.info("listenable future task execute failed, cause: {}", ex.getMessage());
            }

            @Override
            public void onSuccess(Object result) {
                log.info("listenable future task execute successfully, result: {}", result);
            }
        });

        ExecutorService executor = Executors.newSingleThreadExecutor();
        try {
            executor.execute(listenableFutureTask);
            //log.info(listenableFutureTask.get());
        } finally {
            executor.shutdown();
        }
    }
}
