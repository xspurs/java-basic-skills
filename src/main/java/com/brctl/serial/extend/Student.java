package com.brctl.serial.extend;

import lombok.ToString;

import java.io.Serializable;

/**
 * @author duanxiaoxing
 * @created 2018/7/5
 */
@ToString(callSuper = true)
public class Student extends Person implements Serializable {

    private int studentNumber;
    private String grade;

    public Student(String name, int age, String career, String phone, int studentNumber, String grade) {
        super(name, age, career, phone);
        this.studentNumber = studentNumber;
        this.grade = grade;
    }

}
