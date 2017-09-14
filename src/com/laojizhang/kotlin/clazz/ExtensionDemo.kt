package com.laojizhang.kotlin.clazz

fun String.caseMutableList(): MutableList<String> {
    val arrayListOf = arrayListOf<String>()
    for (s in this) {
        arrayListOf.add(s.toString())
    }
    return arrayListOf
}

fun <T> MutableList<T>.swap(index1: Int, index2: Int) {
    val tmp = this[index1] // 'this' 指代 list 实例
    this[index1] = this[index2]
    this[index2] = tmp
}

fun main(args: Array<String>) {
    val strList = "oellh".caseMutableList()
    println("swap befor : $strList")
    strList.swap(0, 4)
    println("swap after : $strList")

    println("lastIndex = " + strList.lastIndex)

    printFoo(D())

    println(null.printString())
    println("this is string".printString())

    MyCompanionClass.Companion.method()
    MyCompanionClass.method()

//    MyA().methodA()
    val myB = MyB()
    myB.caller(MyA())


    val names = listOf<String>("Jake", "Jesse", "Matt", "Alec")
    val newList = names.filter { it.equals("Jake") || it == "Alec" }
    println(newList)
}


abstract class AbsClass

class D : AbsClass()

fun AbsClass.foo() = "c"

fun D.foo() = "d"

fun printFoo(c: AbsClass) {
    println(c.foo())
}


fun Any?.printString(): String {
    if (this == null) return "null"
    // 进行过 null 检查后, 'this' 会被自动转换为非 null 类型, 因此下面的 toString() 方法
    // 会被解析为 Any 类的成员函数
    return toString()
}

// 属性拓展
val <T> List<T>.lastIndex: Int
    get() = this.size - 1

class MyCompanionClass {
    companion object {

    }
}

fun MyCompanionClass.Companion.method() {
    println("我是 MyCompanionClass类的伴生对象的拓展方法")
}

// 在B类中定义A类的拓展函数
class MyA {
    fun methodA() {
        println("我是 MyA 类的 methodA 方法")
    }
}

class MyB {

    fun methodA() {
        println("我是 MyB 类的 methodA 方法")
    }

    fun methodB() {
        println("我是 MyB 类的 methodB 方法")
    }

    fun MyA.extensionA() {
        methodA()
        println("我是 MyB 类的 extensionA 方法")
    }

    fun caller(a: MyA) {
        methodA()
        methodB()
        a.methodA()
        a.extensionA()
    }
}

/**
 * 高阶函数
 */
fun <T> List<T>.filter(predicate: (T) -> Boolean): List<T> {
    val newList = ArrayList<T>()
    for (item in this) {
        if (predicate(item)) {
            newList.add(item)
        }
    }
    return newList
}