package org.zero;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * <a href="https://openjdk.org/projects/jdk7/">JDK 7</a>
 * <a href="https://docs.oracle.com/javase/7/docs/">Java Platform Standard Edition 7 Documentation</a>
 * <h1>Features</h1>
 * <ol>
 *     <li>二进制字面值（Binary Literals）{@link #binaryLiteral()}</li>
 *     <li>数字字面量使用下划线（Underscores in Numeric Literals）{@link #underscoreInNumericLiteral()}</li>
 *     <li>字符串在 switch 语句块中使用（Strings in switch Statements）{@link #stringInSwitchStatement()}</li>
 *     <li>泛型类型推理（Type Inference for Generic Instance Creation）{@link #typeInference()}</li>
 *     <li>改进编译器警告和错误（Improved Compiler Warnings and Errors When Using Non-Reifiable Formal Parameters with Varargs Methods）{@link #compilerWarningAndError()}</li>
 *     <li>try-with-resources 语句块（The try-with-resources Statement）{@link #tryWithResource()}</li>
 *     <li>改进捕获多个异常（Catching Multiple Exception Types and Rethrowing Exceptions with Improved Type Checking）{@link #catchingMultipleException()}</li>
 *     <li>ThreadLocalRandom 类{@link #threadLocalRandom()}</li>
 * </ol>
 *
 * @author Zero
 * @since 2018/12/25
 */
public class Java7 {
    /**
     * 二进制字面值
     * <p>
     * 在 Java 7 中，整型（byte、short、int 和 long）也可以使用二进制数来表示。若要指定二进制文本，请在数字中添加前缀 <code>0b</code> 或 <code>0B</code>。
     */
    public void binaryLiteral() {
        int binaryNumber = 0b110011;
        System.out.println("Binary Number: " + binaryNumber);
    }

    /**
     * 数字字面量中使用下划线
     * <p>
     * 从 Java 7 开始，可以在数字字面量中使用下划线以提高代码的可读性，将下划线放置在数字之间的任何位置。<br>
     * 下划线不会影响字面量的值。<br>
     * 这个特性在处理大型数字字面量（如常量或表示特定模式的值）时尤其有用。
     */
    public void underscoreInNumericLiteral() {
        int million = 1_000_000;
        System.out.println("一百万: " + million);
        double pi = 3.1415_9265;
        System.out.println("圆周率: " + pi);
        int intHexBytes = 0xFF_EC_DE_5E;
        System.out.println("intHexBytes: " + intHexBytes);
        long longBytes = 0B1101_0010__0110_1001__1001_0100__1001_0010;
        System.out.println("longBytes: " + longBytes);
    }

    /**
     * 字符串在 switch 语句块中使用
     * <p>
     * Java 7 引入了字符串在 switch 语句中的支持。
     * 在之前的版本中，switch 语句只支持整数类型（byte、short、char 和 int）。
     * 但从 Java 7 开始，可以在 <code>switch</code> 语句的表达式中使用 {@link String} 类型。
     */
    public void stringInSwitchStatement() {
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        int i = ThreadLocalRandom.current().nextInt(days.length);
        String day = days[i];
        switch (day) {
            case "Monday":
                System.out.println("一周的第一天。");
                break;
            case "Tuesday":
            case "Wednesday":
            case "Thursday":
                System.out.println("这是工作日。");
                break;
            case "Friday":
                System.out.println("周五到了，准备happy！");
                break;
            case "Saturday":
            case "Sunday":
                System.out.println("这是周末！");
                break;
            default:
                throw new IllegalArgumentException("无效的日期。");
        }
    }

    /**
     * 创建通用实例的类型推理
     * <p>
     * 从 Java 7 开始，引入了”Diamond Operator“（菱形操作符），可以在创建泛型类实例时使用空的尖括号（<>），由编译器根据上下文自动推断类型参数。
     */
    public void typeInference() {
        System.out.println("Before:");
        // 在Java 7之前，需要在两侧都显式指定类型参数
        List<String> list1 = new ArrayList<String>();
        System.out.println("list: " + list1);

        System.out.println("After:");
        // 在Java 7及之后，可以使用菱形操作符自动推断类型参数
        List<String> list2 = new ArrayList<>();
        System.out.println("list: " + list2);
    }

    /**
     * 改进编译器警告和错误
     * <p>
     * 在 Java 7 中，对于具有非可具体化（non-reifiable）变长参数（在运行时无法准确地确定参数类型的参数）的方法或构造函数，编译器会在声明处生成警告。<br>
     * 为了提高编译器生成的警告的可见性，Java SE 7 引入了 <code>-Xlint:varargs</code> 编译器选项，以及 <code>@SafeVarargs和@SuppressWarnings({"unchecked", "varargs"})<code> 注解用于抑制这些警告。
     */
    public void compilerWarningAndError() {
        List<?> list = this.asList(1, "Two", 3.0, false);
        System.out.println("list: " + list);
    }

    @SafeVarargs
    @SuppressWarnings("varargs")
    private final <T> List<T> asList(T... args) {
        List<T> list = new ArrayList<>();
        Collections.addAll(list, args);
        return list;
    }

    /**
     * 引入 try-with-resources 语句块
     * <p>
     * 使用 try-with-resources 语句，可以在 try 语句的括号中声明一个或多个资源，这些资源必须实现 {@link AutoCloseable} 接口或 {@link Closeable} 接口。<br>
     * 在 try 语句块结束时，这些资源会自动关闭，而无需手动调用 <code>close()</code> 方法。
     */
    public void tryWithResource() {
        System.out.println("Before:");
        // 在 Java 7 之前，必须显式关闭资源
        BufferedReader reader1 = new BufferedReader(new StringReader("Hello\nJava 7"));
        try {
            String line;
            while ((line = reader1.readLine()) != null) {
                System.out.println("line: " + line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                reader1.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("After:");
        // try-with-resources 语句块允许自动关闭资源
        try (BufferedReader reader2 = new BufferedReader(new StringReader("Hello\nJava 7"))) {
            String line;
            while ((line = reader2.readLine()) != null) {
                System.out.println("line: " + line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 改进捕获多个异常
     * <p>
     * 在 Java 7 中，可以在一个catch块中捕获多种异常类型，这样可以更简洁地处理多个异常情况。
     */
    public void catchingMultipleException() {
        System.out.println("Before:");
        // 以前的写法
        try {
            Object min = Math.class
                    .getDeclaredMethod("min", int.class, int.class)
                    .invoke(null, 3, 4);
            System.out.println("min: " + min);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        // 现在的写法
        System.out.println("After:");
        try {
            Object max = Math.class
                    .getDeclaredMethod("max", int.class, int.class)
                    .invoke(null, 3, 4);
            System.out.println("max: " + max);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 新增 ThreadLocalRandom 类
     * <p>
     * 新增线程安全的随机数生成器，专为多线程环境而设计，通过使用线程本地变量来避免多线程竞争，从而提高性能。
     */
    public void threadLocalRandom() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        double nextDouble = random.nextDouble(100.0, 1000.0);
        System.out.println("randomNum: " + nextDouble);
    }
}