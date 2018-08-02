package com.brctl.spi.impl;

import com.brctl.spi.HelloInterface;

/**
 * Hello in English
 * @author duanxiaoxing
 * @created 2018/8/2
 */
public class EnglishHelloImpl implements HelloInterface {

    @Override
    public void sayHello() {
        System.out.println("Hello.");
    }

}
