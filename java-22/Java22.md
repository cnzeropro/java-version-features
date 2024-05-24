# Java 22

[JDK 22 Documentation](https://docs.oracle.com/en/java/javase/22)

## Java Language Changes

| Feature                                                                                                                                                                                                          | Description                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     | JEP                                                                                                             |
|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------|
| [Statements Before super(...)](https://docs.oracle.com/pls/topic/lookup?ctx=javase22&id=GUID-33B53C2C-F20C-4256-9055-9AFC73251AEB)                                                                               | Introduced as a preview feature for this release.In constructors in the Java programming language, you may add statements that don't reference the instance being created before an explicit constructor invocation.                                                                                                                                                                                                                                                                                                                                                                                            | [JEP 447: Statements before super(...) (Preview)](https://openjdk.org/jeps/440)                                 |
| [Unnamed Variables and Patterns](https://docs.oracle.com/pls/topic/lookup?ctx=javase22&id=GUID-D54E1CF1-BDFD-4B57-8A6E-5B4C87F4D58A)                                                                             | First previewed in Java SE 21 as *Unnamed Patterns and Variables*, this feature is permanent in this release. This means that it can be used in any program compiled for Java SE 22 without enabling preview features.This feature remains unchanged since Java SE 21.                                                                                                                                                                                                                                                                                                                                          | [JEP 456: Unnamed Variables & Patterns](https://openjdk.org/jeps/456)                                           |
| [String Templates](https://docs.oracle.com/en/java/javase/22/language/string-templates.html#GUID-78F545D3-CDD0-415C-9B4B-6EF361D184F5)                                                                           | Preview feature from Java SE 21 re-previewed for this release.Except for a technical change in the types of template expressions, which is described in [String Templates (Second Preview)](https://docs.oracle.com/javase/specs/jls/se22/preview/specs/string-templates-jls.html) in The Java Language Specification: Java SE 22 Edition, this feature remains unchanged since Java SE 21.                                                                                                                                                                                                                     | [JEP 459: String Templates (Second Preview)](https://openjdk.org/jeps/459)                                      |
| [Implicitly Declared Classes and Instance Main Methods](https://docs.oracle.com/en/java/javase/22/language/implicitly-declared-classes-and-instance-main-methods.html#GUID-35544A22-61AB-4928-99BB-A9DD1CA062FF) | First previewed in Java SE 21 as *JEP 445: Unnamed Classes and Instance Main Methods (Preview)*, this feature is re-previewed for this release as *JEP 463: Implicitly Declared Classes and Instance Main Methods (Second Preview)*.In this release:A source file without an enclosing class declaration is said to implicitly declare a class with a name chosen by the host system.The procedure for selecting a `main` method is simplified. If there is a candidate `main` method with a `String[]` parameter then we invoke that method; otherwise we invoke a candidate `main` method with no parameters. | [JEP 463: Implicitly Declared Classes and Instance Main Methods (Second Preview)](https://openjdk.org/jeps/463) |

## Major New Functionality

### Language

#### 匿名的变量和模式（Unnamed Variables & Patterns）

[JEP 456](https://openjdk.org/jeps/456)

### Language Previews and Incubator

#### 允许在构造函数的 super() 调用之前出现不引用正在创建的实例的语句（Statements before super(...) (Preview)）

[JEP 447](https://openjdk.org/jeps/447)

#### 流收集器和流 API（Stream Gatherers (Preview)）

[JEP 461](https://openjdk.org/jeps/461)

#### 字符串模板（String Templates (Second Preview)）

[JEP 459](https://openjdk.org/jeps/459)

#### 隐式声明类和实例主方法（Implicitly Declared Classes and Instance Main Methods (Second Preview)）

[JEP 463](https://openjdk.org/jeps/463)

### Libraries

#### 外部函数和内存 API（Foreign Function & Memory API）

[JEP 454](https://openjdk.org/jeps/454)

### Library Previews and Incubator

#### Class 文件 API（Class-File API (Preview)）

[JEP 457](https://openjdk.org/jeps/457)

#### 结构化并发（Structured Concurrency (Second Preview)）

[JEP 462](https://openjdk.org/jeps/462)

#### 作用域值（Scoped Values (Second Preview)）

#### 向量 API（Vector API (Seventh Incubator)）

[JEP 460](https://openjdk.org/jeps/460)

### Performance

#### G1 固定区域（Region Pinning for G1）

[JEP 423](https://openjdk.org/jeps/423)

### Tooling

#### 启动多文件源代码程序（Launch Multi-File Source-Code Programs）

[JEP 458](https://openjdk.org/jeps/458)