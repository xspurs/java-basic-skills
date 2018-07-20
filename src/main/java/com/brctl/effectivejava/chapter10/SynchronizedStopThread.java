package com.brctl.effectivejava.chapter10;

import java.util.concurrent.TimeUnit;

/**
 * @author duanxiaoxing
 * @created 2017/9/3
 */
public class SynchronizedStopThread {

    private static boolean stopRequest = false;

    private static synchronized void setStopRequest(boolean stop) {
        stopRequest = stop;
    }

    private static synchronized boolean getStopRequest() {
        return stopRequest;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread backgroudThread = new Thread(new Runnable() {
            @Override
            public void run() {
                @SuppressWarnings("unused")
                int i = 0;
                while(!getStopRequest()) {
                    i++;
                }
            }
        });
        backgroudThread.start();

        TimeUnit.SECONDS.sleep(1);
        setStopRequest(true);
    }
}
