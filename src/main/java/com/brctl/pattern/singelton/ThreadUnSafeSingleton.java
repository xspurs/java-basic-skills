package com.brctl.pattern.singelton;

/**
 * ThreadUnSafeSingleton
 * lazy-load, but NOT thread-safe.
 * Created by duanxiaoxing on 17/1/21.
 */
public class ThreadUnSafeSingleton {

    private static ThreadUnSafeSingleton instance = null;

    // private constructor
    private ThreadUnSafeSingleton() {

    }

    // class method to get the singleton
    // NOT thread-safe.
    public static ThreadUnSafeSingleton getInstance() {
        if(instance == null) {
            instance = new ThreadUnSafeSingleton();
        }
        return instance;
    }

}
