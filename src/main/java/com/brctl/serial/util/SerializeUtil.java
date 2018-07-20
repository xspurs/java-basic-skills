package com.brctl.serial.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Serial & DeSerial Method using JDK<br/>
 * You can use below utils directly
 * @see org.apache.commons.lang3.SerializationUtils
 * @see org.springframework.util.SerializationUtils
 * @author duanxiaoxing
 * @created 2018/6/22
 */
@SuppressWarnings("unused")
public class SerializeUtil {

    private SerializeUtil() {}


    /**
     * serialize method
     * @param obj object to serialize
     * @return byte[] result
     * @throws IOException
     */
    public static byte[] serialize(Serializable obj) throws IOException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(obj);
        return byteOut.toByteArray();
    }


    /**
     * deserialize method
     * @param bytes byte[] to reconstruct object
     * @param <T> generics
     * @return object
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @SuppressWarnings("unchecked")
    public static <T> T deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteIn = new ByteArrayInputStream(bytes);
        ObjectInputStream in = new ObjectInputStream(byteIn);
        return (T) in.readObject();
    }

}
