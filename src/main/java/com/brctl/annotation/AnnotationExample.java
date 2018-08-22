package com.brctl.annotation;

import lombok.extern.slf4j.Slf4j;

import javax.inject.Named;
import javax.inject.Singleton;
import java.lang.annotation.Annotation;

/**
 * AnnotationExample
 * <p>More: https://blog.csdn.net/javazejian/article/details/71860633
 * @author duanxiaoxing
 * @created 2018/8/20
 */
@Slf4j
@Named
@Singleton
public class AnnotationExample {

    public static void main(String[] args) {
        Slf4j[] slf4js = AnnotationExample.class.getAnnotationsByType(Slf4j.class);
        System.out.println("slf4js length: " + slf4js.length);

        Named[] nameds = AnnotationExample.class.getAnnotationsByType(Named.class);
        System.out.println("nameds length: " + nameds.length);

        Annotation[] all = AnnotationExample.class.getAnnotations();
        System.out.println("all length: " + all.length);

        Annotation[] anotherAll = AnnotationExample.class.getDeclaredAnnotations();
        System.out.println("declare all length: " + anotherAll.length);
    }

}
