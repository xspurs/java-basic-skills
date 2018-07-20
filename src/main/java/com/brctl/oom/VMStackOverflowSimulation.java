package com.brctl.oom;

/**
 * StackOverflowError模拟<br>
 * VM Args: -Xss128k
 * @author duanxiaoxing
 * @created 2018/4/19
 */
public class VMStackOverflowSimulation {

    private int stackLength = 1;

    public void stackIncrement() {
        stackLength++;
        stackIncrement();
    }

    public static void main(String[] args) {
        VMStackOverflowSimulation sof = new VMStackOverflowSimulation();
        sof.stackIncrement();
    }

}
