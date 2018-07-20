package com.brctl.serial.bean;

import lombok.Data;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * ComplexPerson类(复杂的人类？)<br/>
 * 包含 writeReplace / writeObject / readObject / readResolve 方法
 * @author duanxiaoxing
 * @created 2018/5/30
 */
@Data
public class ComplexPerson implements Serializable {

    private String name;
    private Integer age;
    private String career;
    /**
     * transient关键字修饰<br/>
     * 不参与序列化及反序列化
     */
    private transient String phone;


    private Object writeReplace() throws ObjectStreamException {
        ComplexPerson p = new ComplexPerson();
        p.name = "Custom Name";
        p.age = 18;
        p.career = "Custom Career";
//        p.phone = "13988213498";
        return p;
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        // 默认序列化，针对非transient及static字段
        s.defaultWriteObject();
        s.writeUTF(phone);
    }
}
