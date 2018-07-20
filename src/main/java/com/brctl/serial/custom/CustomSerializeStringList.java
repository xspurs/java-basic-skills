package com.brctl.serial.custom;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 自定义StringList中的序列化／反序列化方式
 * @author duanxiaoxing
 * @created 2018/5/30
 */
public class CustomSerializeStringList<E> implements Serializable {

    private transient int size = 0;
    private transient Entry head = null;

    transient Object[] elementData;

    public boolean add(E e) {
        elementData[size++] = e;
        return true;
    }

    @SuppressWarnings("unchecked")
    E elementData(int index) {
        return (E) elementData[index];
    }

    public E get(int index) {
        return elementData(index);
    }

    /**
     * No need to implement {@link Serializable}
     */
    private static class Entry {
        String data;
        Entry next;
        Entry previous;
    }


    /**
     * 自定义序列化
     * @param s
     * @throws IOException
     * @see java.io.ObjectStreamClass
     */
    private void writeObject(ObjectOutputStream s) throws IOException {
        // default
        s.defaultWriteObject();
        s.writeInt(size);
        for (Entry e = head; e != null; e = e.next) {
            s.writeObject(e);
        }
    }


    /**
     * 自定义反序列化
     * @param s
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        size = s.readInt();
        for (Entry e = head; e != null; e = e.next) {
            @SuppressWarnings("unchecked")
            E deserializedE = (E) s.readObject();
            add(deserializedE);
        }
    }

}
