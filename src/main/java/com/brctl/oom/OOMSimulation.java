package com.brctl.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * OutOfMemory
 * VM args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * @author duanxiaoxing
 * @created 2018/4/19
 */
public class OOMSimulation {

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
    }

    static class OOMObject {

    }

}
