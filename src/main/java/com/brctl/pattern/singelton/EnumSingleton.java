package com.brctl.pattern.singelton;

/**
 * EnumSingleton<br/>
 * <Effective Java> for Enum Singleton:<br/>
 * If you have to write a serializable instance-controlled class whose instances are not known at compile time,
 * you will not be able to represent the class as an enum type.
 * @author duanxiaoxing
 * @created 2018/6/6
 */
public enum EnumSingleton {

    INSTANCE;

    // you can add methods :)
}
