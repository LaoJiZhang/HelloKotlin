#Kotlin VS Java

* createTime : 2017.08.29

* updateTime : 2017.09.14

* author : 老机长

* version : 1.5.0

* 审阅者：pighead、社会我道哥

### 0.What is Kotlin ? 
* Kotlin 是一个基于 JVM 的新的编程语言，由 JetBrains 开发。
* Kotlin可以编译成Java字节码，也可以编译成JavaScript，方便在没有JVM的设备上运行。
* JetBrains，作为目前广受欢迎的Java IDE IntelliJ 的提供商，在 Apache 许可下已经开源其Kotlin 编程语言。
* Kotlin已正式成为Android官方支持开发语言。


### 1.基础语法

**(1.1) 包 及 类的导入、注释**

a. 定义包 ： 
	
```
package xx.xxx.xxxx
```

b. 导入包 ： 

```
import xx.xxx.xxxx
```

c. 代码注释：

c1 .行注释：

```
// 这是一条行末注释
```

c2. 段落注释 ：
	
```
/* 这是一条块注释
	可以包含多行内容. */
```
   
**（1.2） 定义变量**

a. 只读变量：	

```
val i:Int = 10
val i = 1 	// 变量类型自动推断为 `Int` 类型

//当一个引用可能为 null 值时, 对应的类型声明必须明确地标记为可为 null
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
```
	
b. 可写变量： 

```
var i = 1
```   

**（1.3） 定义函数**

```
//标准定义,该函数接受两个 Int 类型参数, 并返回 Int 类型结果
fun sum(a: Int, b: Int): Int {
    return a + b
}

//该函数使用表达式语句作为函数体, 返回类型由自动推断决定
fun sum(a: Int, b: Int) = a + b
	
//若该函数不需要返回值如何处理 ？？？
fun printSum(a: Int, b: Int): Unit {
	print(a + b)
}
	
	
//notice 返回值为 Unit 类型时, 可以省略
fun printSum(a: Int, b: Int) {
	print(a + b)
}
```

**（1.4） 字符串模板**

```
fun main(args: Array<String>) {
    if (args.size == 0) return

    print("First argument: ${args[0]}")
}
```

> 案例参见： BaseDemo.kt

**（1.5） 逻辑控制 if and when and for**

> 案例参见：ControlDemo.kt

**（1.6） 返回及跳转**
> 案例参见：BreakDemo.kt

### 2. 类与继承

**（2.1） 类的定义**

a. Kotlin 中的类使用 class 关键字定义

```
package com.laojizhang.kotlin.clazz

//类的定义由以下几部分组成: 类名, 类头部(指定类的类型参数, 主构造器, 等等.), 以及由大括号括起的类主体部分. 类的头部和主体部分都是可选的; 如果类没有主体部分, 那么大括号也可以省略.
//如果一个非抽象类没有声明任何主构造器和次级构造器, 它将带有一个自动生成的, 无参数的主构造器. 这个构造器的可见度为 public
class EmptyClass

open class Person(val name: String = "")

class Person1 constructor(name: String)
//上面两种主构造函数有区别吗 ？？？

// 问：我们在写Java的时候，有时候可能会写好几个构造方法，这种情况怎么处理呢？？
// 答：次级构造函数
open class Person2(val name: String) {
    // 如果类有主构造器, 那么每个次级构造器都必须委托给主构造器, 要么直接委托, 要么通过其他次级构造器间接委托. 委托到同一个类的另一个构造器时, 使用 this 关键字实现:
    constructor(name: String, age: Int) : this(name)

    open val city: String = "beijing"

    open fun eat() {
        println("在 Person2 类中 eat")
    }

    fun sleep() {}
}

// 问： 有一个类我们不允许其他人直接实例化 又该怎么处理？？？
// 答： 如果不希望你的类带有 public 的构造器, 你需要声明一个空的构造器, 并明确设置其可见度:
class Person3 private constructor()

class ClassDemo {
    // 初始化代码段
    init {
        println("这是初始化代码段，可以执行一些你需要的初始化操作")
    }
}

fun main(args: Array<String>) {
    //类的实例化
    val person = Person("laojizhang")
    println("name = " + person.name)

//    val person1 = Person1("laojizhang")
//    println(person1.name)

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

    fun b() {
        print("b")
    }
}

class C() : A(), B {
    // 编译器要求 f() 方法必须覆盖:
    override fun f() {
        super<A>.f() // 调用 A.f()
//        super<B>.f() // 调用 B.f()
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

class ChildClass(override val property: Int) : MyInterface {
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

// 数据类 and 解构声明
data class User(val name: String, val age: Int, val sex: Int, val address: String)
```

**（2.2） 访问修饰符**

类, 对象, 接口, 构造器, 函数, 属性, 以及属性的设值方法, 都可以使用_可见度修饰符_.(属性的取值方法永远与属性本身的可见度一致, 因此不需要控制其可见度.) 
Kotlin 中存在 4 种可见度修饰符: private, protected, internal（内部的） 以及 public. 如果没有明确指定修饰符, 则使用默认的可见度 public.


说在前面 --- 模块的概念

internal 修饰符表示这个成员只能在同一个模块内访问. 更确切地说, 一个模块(module)是指一起编译的一组 Kotlin 源代码文件:

一个 IntelliJ IDEA 模块;

一个 Maven 工程, 或 Gradle 工程;

通过 Ant 任务的一次调用编译的一组文件.


a. 包的访问修饰符

如果你不指定任何可见度修饰符, 默认会使用 public, 其含义是, 你声明的东西在任何位置都可以访问;

如果你将声明的东西标记为 private, 那么它将只在同一个源代码文件内可以访问;

如果标记为 internal, 那么它将在同一个模块(module)内的任何位置都可以访问;

对于顶级(top-level)声明, protected 修饰符是无效的.

b. 类和接口的访问修饰符

private 表示只在这个类(以及它的所有成员)之内可以访问;

protected — 与 private 一样, 另外在子类中也可以访问;

internal — 在 本模块之内, 凡是能够访问到这个类的地方, 同时也能访问到这个类的 internal 成员;

public — 凡是能够访问到这个类的地方, 同时也能访问这个类的 public 成员.

**（2.3） 拓展**

> 与 C# 和 Gosu 类似, Kotlin 提供了向一个类扩展新功能的能力, 而且不必从这个类继承, 也不必使用任何设计模式, 比如 Decorator 模式之类. 这种功能是通过一种特殊的声明来实现的, Kotlin 中称为 扩展(extension). 
Kotlin 支持 扩展函数(extension function) 和 扩展属性(extension property).

a. 拓展函数

> 要声明一个扩展函数, 我们需要在函数名之前添加前缀, 表示这个函数的 接收者类型(receiver type), 也就是说, 表明我们希望扩展的对象类型. 以下示例将为 MutableList<Int> 类型添加一个 swap 函数:

b. 为所有对象增加一个printString方法

>即使在对象变量值为 null 时也可以调用, 在扩展函数的实现体之内, 可以通过 this == null 来检查接收者是否为 null. 
在 Kotlin 中允许你调用 toString() 函数, 而不必检查对象是否为 null, 就是通过这个原理实现的: 对象是否为 null 的检查发生在扩展函数内部, 因此调用者不必再做检查.

c. 拓展属性

```
val <T> List<T>.lastIndex: Int
    get() = size - 1
```

d. 对伴生类添加拓展函数

e. 在B类定义A类的拓展函数

> 案例参见：ExtensionDemo.kt


### 3. 异常

> Kotlin异常和Java异常基本一致
> Kotlin 中所有的异常类都是 Throwable 的后代类. 每个异常都带有一个错误消息, 调用堆栈, 以及可选的错误原因.
  
a. try表达式 
```
// try 表达式的返回值, 要么是 try 代码段内最后一个表达式的值, 要么是 catch 代码段内最后一个表达式的值. finally 代码段的内容不会影响 try 表达式的结果值.
val a: Int? = try { parseInt(input) } catch (e: NumberFormatException) { null }
```

b. Checked Exception(受控异常)

> Kotlin 中不存在受控异常(checked exception)

```
下面的例子是 JDK 中 StringBuilder 类所实现的一个接口:

Appendable append(CharSequence csq) throws IOException

这个方法签名代表什么意思? 它说, 每次我想要将一个字符串追加到某个对象(比如, 一个 StringBuilder, 某种 log, 控制台, 等等), 我都必须要捕获 IOException 异常. 
为什么? 因为这个对象有可能会执行 IO 操作 (比如 Writer 类也会实现 Appendable 接口).因此就导致我们的程序中充满了这样的代码:

try {
    log.append(message)
}
catch (IOException e) {
    // 实际上前面的代码必然是安全的
}
```


高阶函数

Kotlin 支持把函数赋值给变量并传递变量作为其他函数的参数。接受其他函数作为参数的函数称为 高阶函数

函数调用

一个 Kotlin 函数可以由它的名字加前缀 :: 而引用，或直接在代码块中声明一个匿名函数，或使用  lambda 表达式语法


### 资料来源

[Kotlin教程](https://kotlinlang.org/docs/reference/)