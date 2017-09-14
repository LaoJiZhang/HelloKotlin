@file:JvmName("ControlUtils")

package com.laojizhang.kotlin.base

fun main(args: Array<String>) {

    println("max = " + max(10, 5))
    println("max2 = " + max2(1, 5))

    println(getStringLength("我是字符串"))
    println(getStringLength(10))

    printArr(arrayOf("1", "2", "3", "4", "5"))

    cases(1)
    cases("Hello")
    cases(1000L)
    cases("laojizhang")
    cases(100F)

    printRange()
}

// if 表达式 start
fun max(x: Int, y: Int): Int {
    if (x > y) {
        println("x bigger")
        return x
    } else {
        println("y bigger")
        return y
    }
}

fun max2(x: Int, y: Int): Int = if (x > y) x else y

// if 表达式 及 类型检查 、类型自动转换
fun getStringLength(obj: Any): Int? {
    // 在 `&&` 运算符的右侧, `obj` 的类型会被自动转换为 `String`
    if (obj is String && obj.length > 0) {
        return obj.length
    }

    return null
}
// if 表达式 end

// for 循环 start
fun printArr(args: Array<String>) {
    for (arg in args) {
        println("array item : " + arg)
    }

    println("-------------")
    for (i in args.indices) {
        println("array item = " + args[i])
    }
}
// for 循环 end

// when 表达式 start
fun cases(obj: Any) {
    when (obj) {
        is Int -> println("this is Int = " + obj)
        "Hello" -> println(obj)
        is Long -> println("this is Long = " + obj)
        is String -> println("this is String length = " + obj.length)
        1, 2 -> println()
        "1", 2 -> println()
        else -> println("Unknown ")
    }
}
// when 表达式 end

// 值范围 start

fun printRange() {
    for (i in 1..5) {
        println("i = $i")
    }

    println("---------")
    for (i in (30..35).reversed()) {
        println("i = $i")
    }

    println("---------")
    for (i in 20 downTo 15) {
        println("i = $i")
    }

    println("---------")
    for (i in 5..10 step 2) {
        println("i = $i")
    }
}
// 值范围 end