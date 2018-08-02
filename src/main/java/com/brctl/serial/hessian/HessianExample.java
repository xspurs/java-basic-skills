package com.brctl.serial.hessian;

import com.brctl.serial.bean.Person;
import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * HessianExample类<br/>
 * @author duanxiaoxing
 * @created 2018/5/31
 */
@Slf4j
public class HessianExample {

    public static void main(String[] args) throws Exception {
        Person person = new Person();
        person.setName("Tom");
        person.setAge(25);
        person.setCareer("engineer");
        person.setPhone("15201726287");

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        // serialize
        HessianOutput hessianOutput = new HessianOutput(byteArrayOutputStream);
        hessianOutput.writeObject(person);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        log.info("serialized result: {}", bytes);
        // deserialize
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        HessianInput hessianInput = new HessianInput(byteArrayInputStream);
        Person deserializedPerson = (Person) hessianInput.readObject();
        log.info("deserialized result: {}", deserializedPerson);
    }

}
