package com.brctl.multithread.control;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CyclicBarrier, CountDownLatch示例
 * {@link CyclicBarrier}
 * {@link CountDownLatch}
 * @author duanxiaoxing
 * @created 2017/8/13
 */
@Slf4j
public class RunnerWithBarrierAndLatch {

    private static final int TOTAL_RUNNER_COUNT = 5;

    public static void main(String[] args) {
        final CyclicBarrier barrier = new CyclicBarrier(TOTAL_RUNNER_COUNT);
        final CountDownLatch latch = new CountDownLatch(TOTAL_RUNNER_COUNT);
        // corePoolSize == TOTAL_RUNNER_COUNT
        ExecutorService executorService = Executors.newFixedThreadPool(TOTAL_RUNNER_COUNT);
        for (int i = 0; i < TOTAL_RUNNER_COUNT; i++) {
            executorService.execute(new Runner(i + 1, barrier, latch));
        }
        // ensure all the runners complete running
        if (latch.getCount() > 0) {
            try {
                latch.await();
            } catch (InterruptedException e) {
                log.error("latch await error", e);
            }
        }
        // competition complete
        log.info("all runners have run, competition complete");
        // ExecutorService.shutdown()
        executorService.shutdown();
    }
}

@Slf4j
@AllArgsConstructor
class Runner implements Runnable {

    private int number;
    @NonNull
    private CyclicBarrier barrier;
    @NonNull
    private CountDownLatch latch;

    @Override
    public void run() {
        log.info("runner {} ready to run", number);
        try {
            barrier.await();  // control sub thread
        } catch (InterruptedException | BrokenBarrierException e) {
            log.error("barrier await error", e);
        }
        log.info("runner {} is running", number);
        latch.countDown();  // control main thread
    }
}
