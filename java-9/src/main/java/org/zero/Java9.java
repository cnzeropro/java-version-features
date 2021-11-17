package org.zero;

import java.io.StringWriter;
import java.io.Writer;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * jdk9 新特性
 * <p>
 * 1.接口可定义私有方法了
 * <p>
 * 2.钻石操作符（Diamond operator）升级了
 * <p>
 * 3.try语句改进
 * <p>
 * 4.集合新增静态工厂方法（of()方法）
 * <p>
 * 5.增强了 Stream API
 * <p>
 * 6.全新的HTTP Client API
 * <p>
 * 7.新增Deprecated相关API
 * <p>
 * 8.String类底层存储变更
 * 由原有的 char[] 变更为 byte[]，减少了底层存储的开销
 *
 * @author Zero
 */
public class Java9 {
    /**
     * 1.接口可定义私有方法了
     * <p>
     * 在 Java 8之前，接口只能有常量和抽象方法；Java 8之后，接口新增默认方法与静态方法。现在，Java 9 接口更可以定义私有方法了
     */
    public void printer() {
        Printer printer = System.out::println;
        printer.print1("调用接口抽象方法（已通过方法引用实现）");
        printer.print2("调用接口默认方法");
        Printer.print3("调用接口静态方法");
    }

    /**
     * 2.钻石操作符（Diamond operator）升级了
     * <p>
     * 在 java 9 中， 它可以与匿名的内部类一起使用，从而提高代码的可读性
     */
    public void diamondOperatorUpgrade() {
        // Java 8 写法（必须在后面也指定类型）
        Comparator<String> comparator1 = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }
        };
        System.out.println(comparator1.compare("abc", "abd"));

        // Java 9 写法（无需指定）
        Comparator<String> comparator2 = new Comparator<>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }
        };
        System.out.println(comparator2.compare("abc", "abd"));
    }

    /**
     * 3.try语句改进
     * <p>
     * try-with-resources 是 JDK 7 中一个新的异常处理机制，它能够很容易地关闭在 try-catch 语句块中使用的资源。所谓的资源（resource）是指在程序完成后，必须关闭的对象。try-with-resources 语句确保了每个资源在语句结束时关闭。所有实现了 java.lang.AutoCloseable 接口（其中，它包括实现了 java.io.Closeable 的所有对象），可以使用作为资源。
     * <p>
     * try-with-resources 声明在 JDK 9 已得到改进。如果你已经有一个资源是 final 或等效于 final 变量,您可以在 try-with-resources 语句中使用该变量，而无需在 try-with-resources 语句中声明一个新变量。
     */
    public void tryBlock() {
        // jdk 1.7、1.8的写法
        // 可自动关闭的资源（现实了 AutoCloseable 接口）如需自动关闭，那其初始化必须放在小括号中
        try (Writer writer = new StringWriter()) {
            writer.write("java 8");
            writer.flush();
            System.out.println(writer.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // jdk 9可以在外部先定义资源，然后在小括号中只写资源名
        // 但此时的资源为 final 类型的常量，不可做出修改
        Writer writer = new StringWriter();
        try (writer) {
            writer.write("java 9");
            writer.flush();
            System.out.println(writer.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 4.集合新增静态工厂方法（of()方法）
     */
    public void of() {
        List<String> list = List.of("aaa", "bbb", "ccc", "ddd");
        System.out.println("List：" + list);

        Set<String> set = Set.of("aaa", "bbb", "ccc", "ddd");
        System.out.println("Set:" + set);

        Map<String, String> map = Map.of("K1", "aaa", "K2", "bbb", "K3", "ccc");
        System.out.println("Map：" + map);
    }

    /**
     * 5.增强了 Stream API
     * <p>
     * Java 9 为 Stream 新增了几个方法：dropWhile、takeWhile、ofNullable，为 iterate 方法新增了一个重载方法。
     */
    public void enhanceStreamAPI() {
        // takeWhile() 方法使用一个断言作为参数，返回给定 Stream 的子集直到断言语句第一次返回 false。
        // 如果第一个值不满足断言条件，将返回一个空的 Stream。
        System.out.println("takeWhile 方法：");
        Stream.of("a", "b", "c", "", "e", "f").takeWhile(s -> !s.isEmpty())
                .forEach(System.out::print);
        System.out.println();

        // dropWhile 方法和 takeWhile 作用相反的，使用一个断言作为参数，直到断言语句第一次返回 false 才返回给定 Stream 的子集。
        System.out.println("dropWhile 方法：");
        Stream.of("a", "b", "c", "", "e", "f").dropWhile(s -> !s.isEmpty())
                .forEach(System.out::print);
        System.out.println();

        // 避免NPE
        System.out.println("ofNullable 方法：");
        long count = Stream.ofNullable(null).count();
        System.out.println(count);

        // 避免无限流
        System.out.println("iterate 方法：");
        IntStream.iterate(3, x -> x < 10, x -> x + 3).forEach(System.out::print);
    }
}
