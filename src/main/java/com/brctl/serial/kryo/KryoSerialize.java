package com.brctl.serial.kryo;

import com.brctl.serial.bean.Person;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @author duanxiaoxing
 * @created 2018/5/30
 */
@Slf4j
public class KryoSerialize {

    public static void main(String[] args) throws Exception {
        Kryo kryo = new Kryo();
        Output output = new Output(new FileOutputStream("file.bin"));
        Person person = new Person();
        person.setName("Tom");
        person.setAge(25);
        person.setCareer("engineer");
        person.setPhone("15201726287");
        kryo.writeObject(output, person);
        output.close();
        Input input = new Input(new FileInputStream("file.bin"));
        Person deserializePerson = kryo.readObject(input, Person.class);
        log.info("deserialize result: {}", deserializePerson);
        input.close();
    }

}
