package com.brctl.multithread.future.guava;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFutureTask;
import com.google.common.util.concurrent.MoreExecutors;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author duanxiaoxing
 * @created 2017/8/12
 */
@Slf4j
@SuppressWarnings("unchecked")
public class CallbackListenableFutureBarrierDemo {

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        final CyclicBarrier barrier = new CyclicBarrier(4);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        ListenableFutureTask<String> listenableFutureTask = ListenableFutureTask.create(() -> {
            log.info("listenable future begin to execute");
            TimeUnit.SECONDS.sleep(5);
            return "listenable future task done";
        });
        // !!!!! barrier貌似并不能实现，对比下CountDownLatch，两种控制的应用场景
        Futures.addCallback(listenableFutureTask, new BarrieredSimpleFutureCallback(0, barrier), executorService);
        Futures.addCallback(listenableFutureTask, new BarrieredSimpleFutureCallback(1, barrier), executorService);
        Futures.addCallback(listenableFutureTask, new BarrieredSimpleFutureCallback(2, barrier), executorService);
        executorService.execute(listenableFutureTask);

        barrier.await();
        executorService.shutdown();
    }
}

@Slf4j
@AllArgsConstructor
class BarrieredSimpleFutureCallback implements FutureCallback {
    private int number;
    @NonNull
    private CyclicBarrier barrier;
    @Override
    public void onSuccess(Object result) {
        log.info("listenable future task(number {}) execute successfully, result: {}", number, result);
        barrierAwait();
    }
    @Override
    public void onFailure(Throwable t) {
        log.error("listenable future task(number {}) execute failed, cause: {}", number, t.getMessage());
        barrierAwait();
    }

    private void barrierAwait() {
        try {
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            log.error("barrier await error", e);
        }
    }
}
