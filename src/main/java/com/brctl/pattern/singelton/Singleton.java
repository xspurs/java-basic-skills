package com.brctl.pattern.singelton;

/**
 * Singleton
 * thread-safe but NOT lazy-load
 * Created by duanxiaoxing on 17/1/21.
 */
public class Singleton {

    private static final Singleton instance = new Singleton();

    // private constructor
    private Singleton() {

    }

    // class method to get the singleton
    public static Singleton getInstance() {
        return instance;
    }

}
