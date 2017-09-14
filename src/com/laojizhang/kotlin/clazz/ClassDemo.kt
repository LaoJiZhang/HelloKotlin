package com.laojizhang.kotlin.clazz

//类的定义由以下几部分组成: 类名, 类头部(指定类的类型参数, 主构造器, 等等.), 以及由大括号括起的类主体部分. 类的头部和主体部分都是可选的; 如果类没有主体部分, 那么大括号也可以省略.
//如果一个非抽象类没有声明任何主构造器和次级构造器, 它将带有一个自动生成的, 无参数的主构造器. 这个构造器的可见度为 public
//
class EmptyClass

open class Person(val name: String = "")

class Person1 constructor(name: String)
//上面两种主构造函数有区别吗 ？？？

// 问：我们在写Java的时候，有时候可能会写好几个构造方法，这种情况怎么处理呢？？
// 答：次级构造函数
open class Person2(val name: String) {
    // 如果类有主构造器, 那么每个次级构造器都必须委托给主构造器, 要么直接委托, 要么通过其他次级构造器间接委托. 委托到同一个类的另一个构造器时, 使用 this 关键字实现:
    constructor(name: String, age: Int) : this(name) {
        sleep()
    }

    init {
        println("这是初始化代码段，可以执行一些你需要的初始化操作")
        println("init " + name)
    }

    open val city: String = "beijing"

    open fun eat() {
        println("在 Person2 类中 eat")
    }

    internal fun sleep() {
        println("Person2 sleep")
    }
}

// 问： 有一个类我们不允许其他人直接实例化 又该怎么处理？？？
// 答： 如果不希望你的类带有 public 的构造器, 你需要声明一个空的构造器, 并明确设置其可见度:
class Person3 private constructor() {
    constructor(name: String) : this()
}

fun main(args: Array<String>) {
    //类的实例化
    val person = Person("laojizhang")
    println("name = " + person.name)

//    val person1 = Person1("laojizhang")
//    println(person1.name)
    Person2("laojizhang1")
    Person2("laojizhang2", 18)

    val female = Male("laojizhang", 18, true)
    female.eat()

    println("Companion = " + MyClass.Companion)
    println(MyClass.create())

    val user = User("老机长", 18, 1, "北京 朝阳 三里屯")
    val (a, b, c, d) = user
    val (name, address) = user
    println("name = $a address = $d")
    println("name = $name address = $address")

}

// 子类继承
// note:类上的 open 注解(annotation) 与 Java 的 final 正好相反: 这个注解表示允许从这个类继承出其他子类. 默认情况下, Kotlin 中所有的类都是 final 的
class Male(val name1: String, val age: Int, val sex: Boolean) : Person2(name1, age) {

    // 属性复写
    override val city: String
        get() = "帝都"

    // 方法重写
    override fun eat() {
//        super.eat()
        println("在 Male 类中 eat")
    }

    fun drink() {
        println("在 Male 类中 drink")
    }
}


//在 Kotlin 中, 类继承中的方法实现问题, 遵守以下规则: 如果一个类从它的直接超类中继承了同一个成员的多个实现, 那么这个子类必须覆盖这个成员, 并提供一个自己的实现(可以使用继承得到的多个实现中的某一个). 为了表示使用的方法是从哪个超类继承得到的, 我们使用 super 关键字, 将超类名称放在尖括号类, 比如, super<Base>:
//同时继承 A 和 B 是合法的, 而且函数 a() 和 b() 的继承也不存在问题, 因为对于这两个函数, C 类都只继承得到了唯一的一个实现. 但对函数 f() 的继承就发生了问题, 因为 C 类从超类中继承得到了两个实现, 因此在 C 类中我们必须覆盖函数 f(), 并提供我们自己的实现, 这样才能消除歧义.
open class A {
    open fun f() {
        print("A")
    }

    fun a() {
        print("a")
    }
}

interface B {
    fun f() {
        print("B")
    } // 接口的成员默认是 'open' 的

    fun b()
}

class C() : A(), B {
    // 编译器要求 f() 方法必须覆盖:
    override fun f() {
        super<A>.f() // 调用 A.f()
//        super<B>.f() // 调用 B.f()
    }

    override fun b() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

// Companion Object（同伴对象 伴生对象）
// 与 Java 或 C# 不同, Kotlin 的类没有静态方法(static method). 大多数情况下, 建议使用包级函数(package-level function)替代静态方法.
class MyClass private constructor() {
    companion object {
        fun create(): MyClass {
            return MyClass()
        }
    }
}


// 抽象类
abstract class absClass {
    abstract fun method1()
    abstract fun method2()
}

interface MyInterface {
    val property: Int

    fun bar()
    fun foo() {
        // 方法体是可选的
    }
}

class ChildClass() : absClass(), MyInterface {
    override val property: Int = 1
    override fun method1() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun method2() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun bar() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun foo() {
        super.foo()
    }
}

// 封闭类  和枚举的作用应该是一样的，没用过 不作介绍，有兴趣自己了解吧
sealed class Expr {
    class Const(val number: Double) : Expr()
    class Sum(val e1: Expr, val e2: Expr) : Expr()
    object NotANumber : Expr()
}

private class MyPrivateClass {
    private val name: String = "privateClass"
}

// 数据类 and
data class User(val name: String, val age: Int, val sex: Int, val address: String)


fun <A, B, C> compose(f: (B) -> C, g: (A) -> B): (A) -> C {
    return { x -> f(g(x)) }
}
//
//@NotNull
//public static final Function1 compose(@NotNull final Function1 f, @NotNull final Function1 g) {
//    Intrinsics.checkParameterIsNotNull(f, "f");
//    Intrinsics.checkParameterIsNotNull(g, "g");
//    return (Function1)(new Function1() {
//        public final Object invoke(Object x) {
//            return f.invoke(g.invoke(x));
//        }
//    });
//}