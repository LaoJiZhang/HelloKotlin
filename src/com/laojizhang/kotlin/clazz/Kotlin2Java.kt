package com.laojizhang.kotlin.clazz

import com.laojizhang.kotlin.java.JavaClazz

public class Kotlin2Java {
    public fun method1() {
        println("Kotlin2Java method1")
    }
}

fun main(args: Array<String>) {

    val javaClazz = JavaClazz()

    callJavaVoidMethod(javaClazz)

    callJavaProprety(javaClazz)

    callJavaTransfreeMethod(javaClazz)

    callJavaNull(javaClazz)
}


private fun callJavaNull(javaClazz: JavaClazz) {
    // 平台数据类型(platform type) Java 中定义的类型在 Kotlin 中会被特别处理, 被称为 平台数据类型(platform type). 对于这些类型, Null 值检查会被放松
//    val list1 = javaClazz.list
//    println(list1[1])

//    Exception in thread "main" java.lang.NullPointerException
//    at com.laojizhang.kotlin.clazz.Kotlin2JavaKt.callJavaNull(Kotlin2Java.kt:21)
//    at com.laojizhang.kotlin.clazz.Kotlin2JavaKt.main(Kotlin2Java.kt:15)


    val list: List<String>? = javaClazz.list
    // ?.就是当前面的变量!= nuil 时正常调用，如果为null就为null，!!就是当变量为null时，抛出空指针异常
    println(list?.get(1)?.length)
    println(list!![1].length)
}

/**
 * 调用Java类中方法，但是方法名是Kotlin 语法关键字，需要增加转义操作
 * Kotlin 关键字在 Java 中是合法的标识符: in, object, is
 */
private fun callJavaTransfreeMethod(javaClazz: JavaClazz) {
    javaClazz.`is`()
}

private fun callJavaProprety(javaClazz: JavaClazz) {
    println(javaClazz.proprety)
    javaClazz.proprety = "我是在 Kotlin2Java 中修改了JavaClazz属性"
    println(javaClazz.proprety)
}

public fun callJavaVoidMethod(javaClazz: JavaClazz) {
    val result = javaClazz.voidMethod()
    println("result = $result")
}

fun callKotlinStaticMethod() {
    println("Kotlin中类似于Java文件中的静态方法")
}


fun bubbleSort() {
    val arr: IntArray = intArrayOf(10, 4, -45, -25, 56, 90, 12, 8, 32, 24, 43)
    println("sort befor :" + printArr(arr))
    val size = arr.size
    for (i in 0 until (size - 1)) {
        for (j in 0 until (size - 1 - i)) {
            if (arr[j] > arr[j + 1]) {
                val tmp = arr[j]
                arr[j] = arr[j + 1]
                arr[j + 1] = tmp
            }
        }
    }

    println("sort after :" + printArr(arr))
}

fun printArr(arr: IntArray) {
    for (i in 0..arr.size - 1) {
        print("${arr[i]}    ")
    }
}


