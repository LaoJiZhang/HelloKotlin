package com.laojizhang.kotlin.base

fun main(args: Array<String>) {
    val result = breakControl()

    println("result = $result")
}

// outter@  inner@   ==> label 标签
fun breakControl(): Int {
    outter@ for (x in 1..100) {
        inner@ for (y in 1..100) {
            if (x == 10 && y == 10) {
                return x * y
            }
            if (y > 10) {
                break@inner
            }
            if (x > 10) {
                break@outter
            }
            println("x = $x and y = $y")
        }
    }
    return -1
}