package com.brctl.multithread.lock;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 哲学家就餐
 * @author duanxiaoxing
 * @created 2017/7/26
 */
public class Philosopher extends Thread {

    private Philosopher left;
    private Philosopher right;
    private boolean eating;
    private ReentrantLock table;
    private Condition condition;
    private Random random;

    public Philosopher(ReentrantLock table) {
        super();
        this.eating = false;
        this.table = table;
        this.condition = this.table.newCondition();
        this.random = new Random();
    }

    public void setLeft(Philosopher left) {
        this.left = left;
        left.right = this;
    }
    public void setRight(Philosopher right) {
        this.right = right;
        right.left = this;
    }

    @Override
    public void run() {
        try {
            while (true) {
                eat();
                think();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void think() throws InterruptedException {
        this.table.lock();
        try {
            this.eating = false;
            this.left.condition.signal();
            this.right.condition.signal();
            System.out.println(System.identityHashCode(this) + " is thinking...");
        } finally {
            this.table.unlock();
        }
        Thread.sleep(this.random.nextInt(1000));
    }

    public void eat() throws InterruptedException {
        this.table.lock();
        try {
            while (this.left.eating || this.right.eating) {
                this.condition.await();
            }
            this.eating = true;
            System.out.println(System.identityHashCode(this) + " is eating...");
        } finally {
            this.table.unlock();
        }
        Thread.sleep(this.random.nextInt(1000));
    }

}

class Test {

    public static void main(String[] args) {
        ReentrantLock table = new ReentrantLock();
        Philosopher p1 = new Philosopher(table);
        Philosopher p2 = new Philosopher(table);
        Philosopher p3 = new Philosopher(table);
        Philosopher p4 = new Philosopher(table);
        Philosopher p5 = new Philosopher(table);
        p1.setLeft(p5);
        p2.setLeft(p1);
        p3.setLeft(p2);
        p4.setLeft(p3);
        p5.setLeft(p4);

        // run
        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();
    }

}
