package com.brctl.multithread.future.guava;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFutureTask;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ListenableFutureTask示例(use callback, use CountDownLatch)
 * {@link ListenableFutureTask}
 * {@link Futures}
 * {@link CountDownLatch}
 * @author duanxiaoxing
 * @created 2017/8/12
 */
@Slf4j
@SuppressWarnings("unchecked")
public class CallbackListenableFutureLatchDemo {

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        final CountDownLatch latch = new CountDownLatch(3);
        //ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newSingleThreadExecutor());
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        ListenableFutureTask<String> listenableFutureTask = ListenableFutureTask.create(() -> {
            log.info("listenable future begin to execute");
            TimeUnit.SECONDS.sleep(5);
            return "listenable future task done";
        });
        // add three callbacks, use executorService instance to execute
        Futures.addCallback(listenableFutureTask, new LatchedSimpleFutureCallback(0, latch), executorService);
        Futures.addCallback(listenableFutureTask, new LatchedSimpleFutureCallback(1, latch), executorService);
        Futures.addCallback(listenableFutureTask, new LatchedSimpleFutureCallback(2, latch), executorService);
        // execute listenable future task
        executorService.execute(listenableFutureTask);
        // ensure listener execution before ExecutorService#shutdown
        if (latch.getCount() > 0) {
            latch.await();
        }
        // ExecutorService.shutdown()
        executorService.shutdown();
    }

}

@Slf4j
@AllArgsConstructor
class LatchedSimpleFutureCallback implements FutureCallback {
    private int number;
    @NonNull
    final private CountDownLatch latch;
    @Override
    public void onSuccess(Object result) {
        log.info("listenable future task(number {}) execute successfully, result: {}", number, result);
        latch.countDown();
    }
    @Override
    public void onFailure(Throwable t) {
        log.error("listenable future task(number {}) execute failed, cause: {}", number, t.getMessage());
        latch.countDown();
    }
}
