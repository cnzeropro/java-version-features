# Java 7

[Java Platform Standard Edition 7 Documentation](https://docs.oracle.com/javase/7/docs)

## Enhancements

1. 二进制字面值

   **Binary Literals**

   在 Java 7 中，整型（byte、short、int 和 long）也可以使用二进制数来表示。
   若要指定二进制文本，请在数字中添加前缀`0b`或`0B`。

2. 数字字面量可以使用下划线

   **Underscores in Numeric Literals**

   从 Java 7 开始，可以在数字字面量中使用下划线以提高代码的可读性，将下划线放置在数字之间的任何位置。
   下划线不会影响字面量的值。
   这个特性在处理大型数字字面量（如常量或表示特定模式的值）时尤其有用。

3. 字符串可以在 switch 语句块中使用

    **Strings in switch Statements**
    
    Java 7 引入了字符串在 switch 语句中的支持。
    在之前的版本中，switch 语句只支持整数类型（byte、short、char 和 int）。
    但从 Java 7 开始，可以在`switch`语句的表达式中使用`String`类型。

4. 创建通用实例的类型推理

    **Type Inference for Generic Instance Creation**
    
    从 Java 7 开始，引入了”Diamond Operator“（菱形操作符），可以在创建泛型类实例时使用空的尖括号（<>），由编译器根据上下文自动推断类型参数。

5. 改进编译器警告和错误

    **Improved Compiler Warnings and Errors When Using Non-Reifiable Formal Parameters with Varargs Methods**
    
    在 Java 7 中，对于具有非可具体化（non-reifiable）变长参数（在运行时无法准确地确定参数类型的参数）的方法或构造函数，编译器会在声明处生成警告。
    为了提高编译器生成的警告的可见性，Java SE 7 引入了`-Xlint:varargs`编译器选项。
    以及`@SafeVarargs`和`@SuppressWarnings({"unchecked", "varargs"})`注解用于抑制这些警告。

6. try-with-resources 语句块

    **The try-with-resources Statement**
    
    使用 try-with-resources 语句，可以在try语句的括号中声明一个或多个资源，这些资源必须实现`AutoCloseable`接口或`Closeable`接口。
    在 try 语句块结束时，这些资源会自动关闭，而无需手动调用`close()`方法。

7. 改进捕获多个异常

    **Catching Multiple Exception Types and Rethrowing Exceptions with Improved Type Checking**
    
    在 Java 7 中，可以在一个catch块中捕获多种异常类型，这样可以更简洁地处理多个异常情况。

8. ThreadLocalRandom 类

    **ThreadLocalRandom**
    
    新增线程安全的随机数生成器，专为多线程环境而设计，通过使用线程本地变量来避免多线程竞争，从而提高性能。

