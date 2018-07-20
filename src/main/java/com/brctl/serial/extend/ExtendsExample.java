package com.brctl.serial.extend;

import com.brctl.serial.util.SerializeUtil;

import java.io.IOException;

/**
 * 存在父类、子类继承关系的序列化及反序列化示例
 * @author duanxiaoxing
 * @created 2018/7/5
 */
public class ExtendsExample {

    public static void main(String[] args)
            throws IOException, ClassNotFoundException {
        Student student = new Student("Lucy", 20, "student", "15201821192", 100, "A");
        byte[] bytes = SerializeUtil.serialize(student);
        Student deserializeStudent = SerializeUtil.deserialize(bytes);
        System.out.println(deserializeStudent);
    }

}
