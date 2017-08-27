package com.brctl.multithread.future;

import javafx.scene.input.InputMethodTextRun;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Callable + Future示例
 * {@link java.util.concurrent.Callable}
 * @author duanxiaoxing
 * @created 2017/8/1
 */
public class CallableDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<String>> futures = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            futures.add(executorService.submit(new TaskWithResult(i)));
        }
        for (Future<String> future: futures) {
            System.out.println(future.get());
        }
    }

}

class TaskWithResult implements Callable<String> {
    private int id;
    private static int count = 10;  // 线程间共享
    private final int time = count--;  // 看看这个是怎么实现的
    public TaskWithResult(int id){
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        TimeUnit.MILLISECONDS.sleep(100);
        return "Result of TaskWithResult : "+ id +", Time= " + time;
    }
}
