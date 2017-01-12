package com.brctl.classload;

/**
 * @See: 《深入理解Java虚拟机》 P225
 * <clinit>()
 * Created by duanxiaoxing on 16/12/17.
 */
public class Main {

    static class Parent {
        static {
            A = 2;
            // Illegal forward reference
            // System.out.println(A);
        }
        public static int A = 1;
    }

    static class Sub extends Parent {
        public static int B = A;
    }


    public static void main(String[] args) {
        System.out.println(Parent.A);
        System.out.println(Sub.B);
    }

}


