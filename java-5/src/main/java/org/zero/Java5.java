package org.zero;

import java.util.Arrays;
import java.util.List;

import static org.zero.Constant.SEASON_SPRING;
import static org.zero.Constant.SEASON_WINTER;

/**
 * <a href="https://docs.oracle.com/javase/1.5.0/docs/">JDK<sup>TM</sup> 5.0 Documentation</a>
 * <h1>Features</h1>
 * <ol>
 *     <li>增强 for 循环（Enhanced for Loop）{@link #enhanceForLoop()}</li>
 *     <li>泛型（Generic）{@link #generic()}</li>
 *     <li>可变长参数（Varargs）{@link #vararg()}</li>
 *     <li>类型安全枚举（Typesafe Enum）{@link #typesafeEnum()}</li>
 *     <li>静态导入（Static Import）{@link #staticImport()}</li>
 *     <li>注解（Annotation）{@link #annotation()}</li>
 *     <li>自动装箱、拆箱（Autoboxing/Unboxing）{@link #autoBoxingAndUnboxing()}</li>
 * </ol>
 *
 * @author Zero
 * @since 2018/12/25
 */
public class Java5 {
    /**
     * 引入泛型
     * <p>
     * Generics（泛型）是一种强类型编程机制。<br>
     * 它允许在类、接口和方法中使用类型参数，并为其提供编译时的类型安全性，消除了繁琐的强制转换。<br>
     * 具体参见 Collections Framework（集合框架）。<br>
     * 使用泛型的主要目的是增加代码的重用性、类型安全性和可读性。<br>
     */
    public void generic() {
        System.out.println("Before:");
        // 以前写法
        List list0 = Arrays.asList("aaa", "bbb", "ccc", "ddd");
        for (int i = 0; i < list0.size(); i++) {
            String s = (String) list0.get(i);
            System.out.println(s);
        }

        System.out.println("Now:");
        // 现在写法
        List<String> list1 = Arrays.asList("aaa", "bbb", "ccc", "ddd");
        for (int i = 0; i < list1.size(); i++) {
            String s = list1.get(i);
            System.out.println(s);
        }
    }

    /**
     * 增强 for 循环
     * <p>
     * 使用增强型的 for 循环（for-each），可以更简洁地遍历数组和集合。<br>
     * 这样的循环结构不需要显式地使用迭代器或索引，从而减少了代码的冗余，提高了可读性，同时降低了出错的可能性。
     */
    public void enhanceForLoop() {
        String[] a = new String[]{"aaa", "bbb", "ccc", "ddd"};

        System.out.println("Before:");
        // 以前写法
        for (int i = 0; i < a.length; i++) {
            String s = a[i];
            System.out.println(s);
        }

        System.out.println("Now:");
        // 现在写法
        for (String s : a) {
            System.out.println(s);
        }
    }

    /**
     * 自动装箱、拆箱
     * <p>
     * 在 Java 5 中，引入了自动装箱和拆箱的概念，消除在原始类型（如 int）和包装类型（如 Integer）之间进行手动转换的繁琐工作。
     */
    public void autoBoxingAndUnboxing() {
        System.out.println("Before:");
        // 以前写法
        Integer integer = Integer.valueOf(45);
        int i = integer.intValue();
        System.out.printf("integer: %d, int: %d%n", integer, i);

        System.out.println("Now:");
        // 现在写法
        Character character = 'c';
        char c = character;
        System.out.printf("character: %c, char: %c%n", character, c);
    }

    /**
     * 类型安全枚举
     * <p>
     * Java 5 之前定义枚举的标准方式是使用 Enum Pattern（常见的是静态常量）。
     * 但此模式存在许多问题，例如：
     * <ul>
     *     <li>非类型安全 - 示例中，两个季节相加毫无意义</li>
     *     <li>无命名空间 - 必须以字符串（SEASON_ ）作为 int 枚举的常量前缀，以避免与其他 int 枚举类型发生冲突</li>
     *     <li>脆性 - 如果在两个现有常量之间添加新常量或更改顺序，则必须重新编译代码</li>
     *     <li>打印的值没有信息 - 打印时只是一个数字，无法得知它代表什么，甚至它是什么类型</li>
     * </ul>
     * 而 Typesafe Enums 提供了一种灵活的、面向对象的枚举类型机制，允许创建带有任意方法和字段的枚举类型，提高了代码灵活性和可读性。
     */
    public void typesafeEnum() {
        System.out.println("Before:");
        // 以前写法
        System.out.println(SEASON_SPRING);

        System.out.println("Now:");
        // 现在写法
        System.out.println(Season.SPRING);
    }

    /**
     * 可变长参数
     * Varargs（可变参数）是 Java 5 引入的一项语言特性，使得在调用方法时，可以传递不定数量的参数，而无需手动将参数装箱成数组。
     */
    public void vararg() {
        System.out.println("Before:");
        // 以前的方法定义
        m0(new String[]{"aaa", "bbb"});

        System.out.println("Now:");
        // 现在的方法定义
        m1("aaa", "bbb");
    }

    private void m0(String[] args) {
        System.out.println("args: " + Arrays.toString(args));
    }

    private void m1(String... args) {
        System.out.println("args: " + Arrays.toString(args));
    }

    /**
     * 静态导入
     */
    public void staticImport() {
        System.out.println("Before:");
        // 以前写法
        System.out.println(Constant.SEASON_AUTUMN);

        System.out.println("Now:");
        // 现在写法
        System.out.println(SEASON_WINTER);
    }

    /**
     * 引入注解
     * <p>
     * 注解允许在源代码中添加元数据，从而在许多情况下避免编写样板代码。<br>
     * 通过启用工具从注解中生成代码，可以采用一种”声明式“（declarative）的编程风格。
     */
    @Deprecated
    public void annotation() {
        System.out.println("This method has '@Deprecated' annotation");
    }
}