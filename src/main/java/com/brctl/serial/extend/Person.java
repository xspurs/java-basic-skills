package com.brctl.serial.extend;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * Person类，为Student类的父类<br/>
 * 1. 父类不实现Serializable接口时，在子类反序列化阶段父类字段值为null
 * 2. 子类在反序列化过程中，不需要调用子类构造方法，但会调用父类无参构造方法(此时若没有无餐构造方法<b>不会</b>出现异常)
 * @author duanxiaoxing
 * @created 2018/7/5
 */
@Data
@AllArgsConstructor
//public class Person {  // implements Serializable {
public class Person implements Serializable {

    private String name;
    private Integer age;
    private String career;
    private String phone;

    public Person() {
        System.out.println("in Person's no arg constructor");
    }

}
