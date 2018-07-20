package com.brctl.serial.protobuf;

import com.brctl.serial.protobuf.bean.PersonOuterClass;
import lombok.extern.slf4j.Slf4j;

/**
 * @author duanxiaoxing
 * @created 2018/5/31
 */
@Slf4j
public class ProtobufExample {

    public static void main(String[] args) throws Exception {
        PersonOuterClass.Person.Builder  personBuilder = PersonOuterClass.Person.newBuilder();
        personBuilder.setName("Jerry");
        personBuilder.setAge(20);
        personBuilder.setCareer("teacher");
        personBuilder.setPhone("15112933840");
        PersonOuterClass.Person person = personBuilder.build();
        log.info("build result: {}", person);

        // serialize
        byte[] bytes = person.toByteArray();
        log.info("serialized result: {}", bytes);
        // deserialize
        PersonOuterClass.Person deserializePerson = PersonOuterClass.Person.parseFrom(bytes);
        log.info("deserialized result: {}", deserializePerson);
    }

}
