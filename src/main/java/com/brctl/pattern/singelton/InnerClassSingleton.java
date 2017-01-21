package com.brctl.pattern.singelton;

/**
 * InnerClassSingleton
 * BEST practice for the Singleton Pattern.
 * Created by duanxiaoxing on 17/1/21.
 */
public class InnerClassSingleton {

    // private constructor
    private InnerClassSingleton() {

    }

    // inner class to hold the singleton instance
    private static class InnerClassSingletonHolder {
        private static final InnerClassSingleton SINGLETON = new InnerClassSingleton();
    }

    // class method to get the singleton
    public static InnerClassSingleton getInstance() {
        return InnerClassSingletonHolder.SINGLETON;
    }
}
