package com.brctl.serial.bean;

import lombok.Data;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * ExternalizablePerson类<br/>
 * 使用{@code writeExternal} {@code readExternal}自定义序列化／反序列化方法
 * @author duanxiaoxing
 * @created 2018/5/30
 */
@Data
public class ExternalizablePerson implements Externalizable {

    private static final long serialVersionUID = 1866740372404660450L;

    private String name;
    private Integer age;
    private String career;
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
        out.writeUTF(phone);
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
        phone = in.readUTF();
    }

}
