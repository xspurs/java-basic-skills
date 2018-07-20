package com.brctl.serial.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Another Serial & DeSerial Method using JDK<br/>
 * This util can serialize class which not implement <code>java.io.Serializable</code>, i.e.<br/>
 * <pre>
 *     public class Person {
 *         private String name;
 *         private String gender;
 *         private int age;
 *
 *         public Person() {}
 *         public Person(String name, String gender, int age) {
 *             this.name = name;
 *             this.gender = gender;
 *             this.age = age;
 *         }
 *         // omit getters and setters
 *         ...
 *     }
 * </pre>
 * Such <code>Person</code> instance, you can NOT use <code>SerializeUtil</code>
 * or <code>org.apache.commons.lang3.SerializationUtils</code>
 * or <code>org.springframework.util.SerializationUtils</code><br/>
 * BUT you CAN use <code>AnotherSerializeUtil</code>:
 * <pre>
 *     byte[] bytes = AnotherSerializeUtil.serialize(p);
 * </pre>
 * PREREQUISITE: extends <code>AnotherSerializeUtil.write</code> and <code>AnotherSerializeUtil.read</code> methods
 * to SUPPORT class <code>Person</code>.
 * @author duanxiaoxing
 * @created 2018/6/22
 */
@SuppressWarnings("unused")
public class AnotherSerializeUtil {

    private AnotherSerializeUtil() {}

    public static Object read(DataInput in) throws IOException {
        byte type = in.readByte();
        if (type == 0) {
            return in.readByte();
        } else if (type == 1) {
            return in.readShort();
        } else if (type == 2) {
            return in.readChar();
        } else if (type == 3) {
            return in.readInt();
        } else if (type == 4) {
            return in.readLong();
        } else if (type == 5) {
            return in.readFloat();
        } else if (type == 6) {
            return in.readDouble();
        } else if (type == 7) {
            return in.readBoolean();
        } else if (type == 8) {
            return in.readUTF();
        } else if (type == 9) {
            Map<String, Object> map = new HashMap<>();
            int size = in.readInt();
            for (int i = 0; i < size; i++) {
                map.put(in.readUTF(), read(in));
            }
            return map;
        } else if (type == 10) {
            List<Object> list = new ArrayList<>();
            int size = in.readInt();
            for (int i = 0; i < size; i++) {
                list.add(read(in));
            }
            return list;
        } else {
            throw new RuntimeException("Unsupported Type");
        }
    }


    public static void write(DataOutput out, Object obj) throws IOException {
        if (obj instanceof Byte) {
            out.writeByte(0);
            out.writeByte((Byte) obj);
        } else if (obj instanceof Short) {
            out.writeByte(1);
            out.writeShort((Short) obj);
        } else if (obj instanceof Character) {
            out.writeByte(2);
            out.writeChar((Character) obj);
        } else if (obj instanceof Integer) {
            out.writeByte(3);
            out.writeInt((Integer) obj);
        } else if (obj instanceof Long) {
            out.writeByte(4);
            out.writeLong((Long) obj);
        } else if (obj instanceof Float) {
            out.writeByte(5);
            out.writeDouble((Float) obj);
        } else if (obj instanceof Double) {
            out.writeByte(6);
            out.writeDouble((Double) obj);
        } else if (obj instanceof Boolean) {
            out.writeByte(7);
            out.writeBoolean((Boolean) obj);
        } else if (obj instanceof String) {
            out.writeByte(8);
            out.writeUTF((String) obj);
        } else if (obj instanceof Map) {
            out.writeByte(9);
            @SuppressWarnings("unchecked")
            Map<String, Object> map = (Map<String, Object>) obj;
            out.writeInt(map.size());
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                out.writeUTF(entry.getKey());
                write(out, entry.getValue());
            }
        } else if (obj instanceof List) {
            out.writeByte(10);
            @SuppressWarnings("unchecked")
            List<Object> list = (List<Object>) obj;
            out.writeInt(list.size());
            for (Object e : list) {
                write(out, e);
            }
        } else {
            throw new RuntimeException("Unsupported Type");
        }
    }


    public static byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        DataOutput out = new DataOutputStream(byteOut);
        write(out, obj);
        return byteOut.toByteArray();
    }


    @SuppressWarnings("unchecked")
    public static <T> T deserialize(byte[] bytes) throws IOException {
        ByteArrayInputStream byteIn = new ByteArrayInputStream(bytes);
        DataInput in = new DataInputStream(byteIn);
        return (T) read(in);
    }

}
