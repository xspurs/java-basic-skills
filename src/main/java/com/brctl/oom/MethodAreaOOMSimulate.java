package com.brctl.oom;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Method Area OOM模拟<br>
 * VM Args(JDK7): -XX:PermSize=10M -XX:MaxPermSize=10M
 * VM Args(JDK8): -XX:MetaspaceSize=10M -XX:MaxMetaspaceSize=10M
 * @author duanxiaoxing
 * @created 2018/4/19
 */
public class MethodAreaOOMSimulate {

    public static void main(String[] args) {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invokeSuper(o, objects);
                }
            });
            enhancer.create();
        }
    }

    static class OOMObject {

    }

}
