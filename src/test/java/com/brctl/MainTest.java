package com.brctl;

import com.brctl.collection.Main;
import org.junit.Test;

/**
 * 单元测试类
 */
public class MainTest {

    @Test
    public void testGetRandomName() {

        Main main = new Main();
        for(int i = 0; i < 20; ++i) {
            String randomName = main.getRandomName();
            System.out.println(randomName);
        }
    }
}
