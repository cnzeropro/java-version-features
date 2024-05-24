# Java 4

[Java(TM) 2 SDK Documentation](http://docs.oracle.com/javase/1.4.2/docs)

## Enhancements

### 1、断言功能（Assertion Facility）

断言是 Java 1.4 的一个语言特性，它允许程序员在运行时检查程序逻辑的正确性。断言使用`assert`关键字声明。

| 表达式                                  | 说明                                                                       |
|--------------------------------------|--------------------------------------------------------------------------|
| assert BoolExpression;               | 计算 boolean 表达式：如果为 true 正常通过断言语句，否则抛出没有详细信息的 AssertionError              |
| assert BoolExpression:MsgExpression; | 计算 boolean 表达式：如果为 true 正常通过断言语句，否则运行指定表达式获取字符串，使用其构造 AssertionError 并抛出 |

默认情况下，断言在运行时处于禁用状态。通过两个命令行开关允许有选择地启用或禁用断言。

* 要启用粗粒度的断言：使用`-enableassertions`或`-ea`；要启用细粒度的断言：使用`-enableassertions:xxx.xxx`或`-ea:xxx.xxx`。
* 要禁用用粗粒度的断言：使用`-disableassertions`或`-da`；要禁用细粒度的断言：使用`-disableassertions:xxx.xxx`
  或`-da:xxx.xxx`。
