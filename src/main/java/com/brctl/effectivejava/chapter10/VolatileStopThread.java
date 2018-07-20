package com.brctl.effectivejava.chapter10;

import java.util.concurrent.TimeUnit;

/**
 * @author duanxiaoxing
 * @created 2017/9/3
 */
public class VolatileStopThread {

    private static volatile boolean stopRequest = false;

    public static void main(String[] args) throws InterruptedException {
        Thread backgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                @SuppressWarnings("unused")
                int i = 0;
                while (!stopRequest) {
                    i++;
                }
            }
        });

        backgroundThread.start();
        TimeUnit.SECONDS.sleep(1);
        stopRequest = true;
    }
}
