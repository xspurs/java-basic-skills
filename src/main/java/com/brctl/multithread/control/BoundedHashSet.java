package com.brctl.multithread.control;

import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * 使用Semaphore控制集合size
 * {@link Semapho}
 * @author duanxiaoxing
 * @created 2017/8/15
 */
public class BoundedHashSet<T> {

    private final Set<T> set;
    private final Semaphore semaphore;

    public BoundedHashSet(int bound) {
        this.set = Collections.synchronizedSet(new HashSet<T>(bound));
        this.semaphore = new Semaphore(bound);
    }

    public boolean add(T e) throws InterruptedException {
        this.semaphore.acquire();
        // this.semaphore.tryAcquire();  // 区别？acquire
        boolean wasAdded = false;
        try {
            wasAdded = this.set.add(e);
            return wasAdded;
        } finally {  // 疑问：return后，还会执行finally吗
            if (!wasAdded) {
                this.semaphore.release();
            }
        }
    }

    public boolean remove(T e) {
        boolean wasRemoved = this.set.remove(e);
        if (wasRemoved) {
            this.semaphore.release();
        }
        return wasRemoved;
    }

}

@Slf4j
class BoundedHashSetTest {

    public static void main(String[] args) throws InterruptedException {
        BoundedHashSet<Integer> boundedHashSet = new BoundedHashSet<>(5);
        for (int i = 0; i < 6; i++) {
            log.info("add {}, is success: {}", i, boundedHashSet.add(i));
        }

        log.info("can not stop");
    }

}
