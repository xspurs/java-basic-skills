package com.brctl.serial.defensive;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

/**
 * 不可变Period类
 * @author duanxiaoxing
 * @created 2018/5/31
 */
public final class Period implements Serializable {

    private static final long serialVersionUID = 4161472485264550724L;

    private final Date start;
    private final Date end;

    /**
     * @param start
     * @param end end不能比start时间还靠前
     */
    public Period(Date start, Date end) {
        this.start = new Date(start.getTime());
        this.end = new Date(end.getTime());
        // 检查起止时间
        if (this.start.compareTo(this.end) > 0) {
            throw new IllegalArgumentException(start + " after " + end);
        }
    }

    public Date start() {
        return new Date(start.getTime());
    }

    public Date end() {
        return new Date(end.getTime());
    }

    public String toString() {
        return start + " - " + end;
    }


    /**
     * 反序列化初始化时进行defensive check<br/>
     * 去掉注释可以对反序列化创建的实例进行业务／逻辑校验，校验不通过不得生成实例
     * @param in
     * @throws IOException
     * @throws ClassNotFoundException
     */
//    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
//        in.defaultReadObject();
//        // 检查起止时间
//        if (start.compareTo(end) > 0) {
//            throw new InvalidObjectException(start + " after " + end);
//        }
//    }

}
