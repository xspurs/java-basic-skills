package com.brctl.oom;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Direct Memory OOM模拟
 * VM Args: -Xmx20m -XX:MaxDirectMemorySize=10m
 * @author duanxiaoxing
 * @created 2018/4/19
 */
public class DirectMemoryOOMSimulation {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws Exception {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }

}
