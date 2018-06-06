package com.brctl.pattern.singelton;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * InnerClassSingletonBanSerialize<br/>
 * implements java.io.Serializable, use readResolve() to prevent ruining singleton
 * BEST practice for the Singleton Pattern.
 * Created by duanxiaoxing on 17/1/21.
 */
@Slf4j
public class InnerClassSingletonBanSerialize implements Serializable {

    // private constructor
    private InnerClassSingletonBanSerialize() {

    }

    // inner class to hold the singleton instance
    private static class InnerClassSingletonHolder {
        private static final InnerClassSingletonBanSerialize SINGLETON = new InnerClassSingletonBanSerialize();
    }

    // class method to get the singleton
    public static InnerClassSingletonBanSerialize getInstance() {
        return InnerClassSingletonHolder.SINGLETON;
    }


    private Object readResolve() {
        return InnerClassSingletonHolder.SINGLETON;
    }


    public static void main(String[] args) throws Exception {
        InnerClassSingletonBanSerialize singleton = InnerClassSingletonBanSerialize.getInstance();
        InnerClassSingletonBanSerialize deserializeSingleton = null;

        // serialize
        try (FileOutputStream fos = new FileOutputStream("object.txt")) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(singleton);
            oos.close();
        }

        // deserialize
        try (FileInputStream fis = new FileInputStream("object.txt")) {
            ObjectInputStream ois = new ObjectInputStream(fis);
            deserializeSingleton = (InnerClassSingletonBanSerialize) ois.readObject();
        }

        log.info("is the same instance: {}", singleton == deserializeSingleton);

    }

}
