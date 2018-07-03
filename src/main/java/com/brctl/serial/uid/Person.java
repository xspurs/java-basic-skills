package com.brctl.serial.uid;

import lombok.Data;

/**
 * Serializable Bean with serialVersionUID
 * @author duanxiaoxing
 * @created 2018/7/2
 */
@Data
public class Person implements java.io.Serializable {

    /**
     * Attention!<br/>
     * If changed between serialization and deserialization, java.io.InvalidClassException will be thrown.
     * @see java.io.InvalidClassException
     */
    private static final long serialVersionUID = 1L;

    private String name;
    private int age;
    private String gender;

}
