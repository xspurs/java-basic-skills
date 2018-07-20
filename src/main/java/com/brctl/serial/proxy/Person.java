package com.brctl.serial.proxy;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 使用Serialize Pattern Proxy自定义序列化
 * @author duanxiaoxing
 * @created 2018/5/31
 */
@Data
@Slf4j
public class Person implements Serializable {

    private static final long serialVersionUID = -5136349449959230497L;

    private String name;
    private Integer age;
    private String career;
    private String phone;

    public Person(String name, Integer age, String career, String phone) {
        this.name = name;
        // check validate
        this.age = age;
        this.career = career;
        this.phone = phone;
    }

    /**
     * 序列化代理类
     */
    private static class PersonProxy implements Serializable {

        private static final long serialVersionUID = -2902051239103230395L;

        private String name;
        private Integer age;
        private String career;
        private String phone;

        public PersonProxy(Person p) {
            log.info("PersonProxy(Person original)");
            this.name = p.getName();
            this.age = p.getAge();
            this.career = p.getCareer();
            this.phone = p.getPhone();
        }

        private Object readResolve() {
            log.info("PersonProxy.readResolve()");
            Person person = new Person(name, age, career, phone);
            return person;
        }

        private void readObject(ObjectInputStream in) throws Exception {
            log.info("PersonProxy.readObject");
            in.defaultReadObject();
        }
    }

    private Object writeReplace() {
        log.info("Person.writeReplace()");
        return new PersonProxy(this);
    }

    /**
     * 本方法不会执行，因为序列化已经由PersonProxy实例代理
     * @param out
     */
    private void writeObject(ObjectOutputStream out) {
        log.info("Person.writeObject()");
    }

    /**
     * 防止攻击者伪造数据(age < 0 || age >> 100)
     * @param in
     * @return
     * @throws InvalidObjectException
     */
    private void readObject(ObjectInputStream in) throws InvalidObjectException {
        throw new InvalidObjectException("Proxy required");
    }

}
