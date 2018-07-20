package com.brctl.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * PermGen OOM模拟<br>
 * VM Args: -XX:PermSize=1M -XX:MaxPermSize=1M
 * @author duanxiaoxing
 * @created 2018/4/19
 */
public class PermGenSimulate {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        int i = 0;
        while(true) {
            list.add(String.valueOf(i++).intern());
        }
    }

}
