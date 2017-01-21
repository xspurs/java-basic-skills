package com.brctl.pattern.singelton;

/**
 * ThreadSafeSynchronizedMethodSingeleton
 * Use method level synchronized to guarantee thread safe.
 * Created by duanxiaoxing on 17/1/21.
 */
public class ThreadSafeSynchronizedMethodSingeleton {

    private static ThreadSafeSynchronizedMethodSingeleton instance;

    // private constructor
    private ThreadSafeSynchronizedMethodSingeleton() {

    }

    // class method to get the singleton
    public synchronized static ThreadSafeSynchronizedMethodSingeleton getInstance() {
        if(instance == null) {
            instance = new ThreadSafeSynchronizedMethodSingeleton();
        }
        return instance;
    }

}
