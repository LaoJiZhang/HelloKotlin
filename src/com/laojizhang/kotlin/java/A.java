package com.laojizhang.kotlin.java;


import com.laojizhang.kotlin.base.ControlUtils;

public class A {

    public static void main(String[] args) {
        printFoo(new MyD());

//        ControlDemoKt.printRange();
        ControlUtils.printRange();
    }

    public static void printFoo(AbsC c) {
        c.foo();
    }
}
