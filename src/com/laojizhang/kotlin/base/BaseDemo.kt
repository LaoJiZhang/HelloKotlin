package com.laojizhang.kotlin.base

import com.laojizhang.kotlin.clazz.ChildClass
import java.text.SimpleDateFormat
import java.util.*
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

const val SEX: String = "this is sex"

class BaseDemo {

    public val i0 = 10
    // note : 未显式申明访问修饰符的在生成字节码后都是private形式的，但是会提供相应的get 及set方法
    val i1: Int = 1
    val i2 = 1
    var name: String = "null"

    // note : 禁止生成get 及set方法可显式申明私有访问修饰符private
//    private int age = 18;
    private val age: Int = 18

//    val i3: Int = null    //非空变量赋值 null 报错信息

    // 延迟加载属性
    lateinit var late: String

    val lazyValue: String by lazy {
        println("第一次调用lazy方法会输出，后期只返回之前记住的结果")
        "this is lazyValue"
    }

    // 属性委托
    // 场景： 商城项目里计时器 假如开售前可以预约，在开售前20分钟内禁止预约 按钮需要消失，则需要根据当前时间做出相应的逻辑实现
    var address: String by Delegates.observable("北京市朝阳区三里屯", onChange = { property, oldValue, newValue ->
        println("property = $property  oldValue : $oldValue newValue = $newValue")
    })

    // 常量放置位置 1.top level or objects
    companion object {
        const val myStr: String = "str"
        var myStr1: String = "str1"
            set(value) {
                field = field.toString() + "aa123"
                println(field)
            }
            get() = "sss"
    }

    // 单例模式 你经常写的
    object Single {
        const val myAge = 18
        fun aaa(): Int {
            return 1
        }
    }

    // 加法函数
//    private int sum(int x, int y) {
//        return x + y;
//    }
    fun sum(x: Int, y: Int): Int = x + y

    fun sum2(x: Int, y: Int) = x + y

    /**
     * @desc 乘法函数
     * @param x ,y Int 类型数据，可能为空
     */

    //    private Integer mutily(Integer a, Integer b) {
    //        if (a == null)
    //            return null;
    //        if (b == null)
    //            return null;
    //        return a * b;
    //    }

    fun mutily(x: Int?, y: Int?): Int? {
        if (x == null) {
            println("Wrong number format in x")
            return null
        }

        if (y == null) {
            println("Wrong number format in y")
            return null
        }
        return x * y
    }
}

fun main(args: Array<String>) {
    println(SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date()))

    val baseDemo = BaseDemo()
    println("sum = " + baseDemo.sum(10, 5))
    baseDemo.late = "late"

    println(baseDemo.lazyValue)
    println(baseDemo.lazyValue)
    BaseDemo.myStr1 = "我是str "
    BaseDemo.Companion.myStr

    baseDemo.address = "北京市天安门"
    baseDemo.address = "北京市世贸天阶"

    println("name = ${baseDemo.name}")
    baseDemo.name = "laojizhang"
    println("name = " + baseDemo.name)

    println("mutily = " + baseDemo.mutily(null, 10))
    println("mutily = " + baseDemo.mutily(10, null))
    println("mutily = " + baseDemo.mutily(10, 10))

}
