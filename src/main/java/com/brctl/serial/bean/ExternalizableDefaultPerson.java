package com.brctl.serial.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import util.SerializeUtil;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

/**
 * ExternalizableDefaultPerson类<br/>
 * 使用{@code writeExternal} {@code readExternal}自定义序列化／反序列化方法<br/>
 * 同时定义了{@code writeObject} {@code readObject}方法，可以看出在实现Externalizable接口的情况下，{@code writeObject} {@code readObject}方法被忽略
 * @author duanxiaoxing
 * @created 2018/5/30
 */
@Slf4j
@ToString
public class ExternalizableDefaultPerson implements Externalizable {

    private static final long serialVersionUID = 1866740372404660450L;

    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private Integer age;
    @Setter
    @Getter
    private String career;
    @Setter
    @Getter
    private transient String phone;

    /**
     * 自定义序列化方式
     * @param out
     * @throws IOException
     */
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(name);
        out.writeInt(age);
        out.writeUTF(career);
    }

    /**
     * 自定义反序列化方式
     * @param in
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = in.readUTF();
        age = in.readInt();
        career = in.readUTF();
    }


    private void writeObject(ObjectOutputStream output) throws IOException {
        output.defaultWriteObject();
        // manual serialize transient field
        output.writeUTF(phone);
    }


    private void readObject(ObjectInputStream input) throws IOException, ClassNotFoundException {
        input.defaultReadObject();
        // manual deserialize transient field
        phone = input.readUTF();
    }


    public static void main(String[] args) {
        ExternalizableDefaultPerson person = new ExternalizableDefaultPerson();
        person.setName("Hanks");
        person.setPhone("15201612666");
        person.setAge(27);
        person.setCareer("engineer");

        byte[] bytes = SerializeUtil.serialize(person);
        ExternalizableDefaultPerson deserializedPerson = SerializeUtil.deserialize(bytes);
        log.info("deserialized person: {}", deserializedPerson);
    }

}
