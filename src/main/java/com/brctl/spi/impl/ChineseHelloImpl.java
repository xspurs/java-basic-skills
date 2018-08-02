package com.brctl.spi.impl;

import com.brctl.spi.HelloInterface;

/**
 * Hello in Chinese
 * @author duanxiaoxing
 * @created 2018/8/2
 */
public class ChineseHelloImpl implements HelloInterface {

    @Override
    public void sayHello() {
        System.out.println("Nihao.");
    }

}
