package com.brctl.serial.uid;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Test main class
 * @author duanxiaoxing
 * @created 2018/7/2
 */
public class Main {

    public static void main(String[] args) throws java.io.IOException, java.lang.ClassNotFoundException {
        Person person = new Person();
        person.setName("Lucy");
        person.setAge(20);
        person.setGender("female");

        // serialize to file
        try (FileOutputStream fileOut = new FileOutputStream("./person.bin");
             ObjectOutputStream out = new ObjectOutputStream(fileOut);
        ) {
            out.writeObject(person);
        }
        // deserialize from file
        try (FileInputStream fileIn = new FileInputStream("./person.bin");
             ObjectInputStream in = new ObjectInputStream(fileIn);
        ) {
            @SuppressWarnings("unchecked")
            Person deserializedPerson = (Person) in.readObject();
            System.out.println("deserializedPerson: " + deserializedPerson);
        }

    }

}
