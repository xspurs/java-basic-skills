package com.brctl.serial.proxy;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Example
 * @author duanxiaoxing
 * @created 2018/5/31
 */
@Slf4j
public class ProxyExample {

    public static void main(String[] args) throws Exception {
        Person person = new Person("Lily", 25, "Singer", "18829918729");
        // serialize
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("file.bin"));
        out.writeObject(person);
        out.flush();
        out.close();
        // deserialize
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("file.bin"));
        Person deserializedPerson = (Person) in.readObject();
        log.info("deserialized result: {}", deserializedPerson);
        in.close();

        if (person == deserializedPerson) {
            log.info("pre and post are the same object");
        } else {
            log.info("pre and post are not the same object");
        }
    }

}
