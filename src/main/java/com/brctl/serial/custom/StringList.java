package com.brctl.serial.custom;

import java.io.Serializable;

/**
 * Use Default Serializable
 * {@link CustomSerializeStringList}
 * @author duanxiaoxing
 * @created 2018/5/30
 */
public class StringList implements Serializable {
    private int size = 0;
    private Entry head = null;

    private static class Entry implements Serializable {
        String data;
        Entry next;
        Entry previous;
    }


}
