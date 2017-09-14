package com.laojizhang.kotlin.clazz

import java.util.function.Predicate

fun isOdd(x: Int) = x % 2 == 0

var x = 1

fun main(args: Array<String>) {
    val list = listOf<Int>(1, 2, 3)
    println(list.filter(::isOdd))


    // 属性引用(Property Reference)
    println("x = $x")
    println("x = " + ::x.get())

    x = 2
    println("x = " + ::x.get())
    ::x.set(3)
    println("x = " + ::x)


//    final class SuperiorMethodDemoKt$main$4 extends MutablePropertyReference0 {
//        public static final KMutableProperty0 INSTANCE = new SuperiorMethodDemoKt$main$4();
//
//        public String getName() {
//            return "x";
//        }
//
//        public String getSignature() {
//            return "getX()I";
//        }
//
//        public KDeclarationContainer getOwner() {
//            return Reflection.getOrCreateKotlinPackage(SuperiorMethodDemoKt.class, "production sources for module KotlinForJava");
//        }
//
//        @Nullable
//        public Object get() {.
//            return SuperiorMethodDemoKt.getX();
//        }
//
//        public void set(@Nullable Object value) {
//            SuperiorMethodDemoKt.setX(((Number)value).intValue());
//        }
//    }
}

