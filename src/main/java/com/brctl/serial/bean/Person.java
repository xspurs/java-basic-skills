package com.brctl.serial.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * Person类<br/>
 * 使用默认序列化／反序列方法
 * @author duanxiaoxing
 * @created 2018/5/30
 */
@Data
public class Person implements Serializable {

    private String name;
    private Integer age;
    private String career;
    /**
     * transient关键字修饰<br/>
     * 不参与序列化及反序列化
     */
    private transient String phone;

}
