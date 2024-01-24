package org.zero;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * <a href="https://docs.oracle.com/javase/7/docs/">Java Platform Standard Edition 7 Documentation</a>
 * <h2>Enhancements</h2>
 * <ol>
 *     <li>【new】二进制字面值（Binary Literals）{@link Java7#addBinaryLiterals()}</li>
 *     <li>【new】数字字面量可以使用下划线（Underscores in Numeric Literals）{@link Java7#addUnderscoresInNumericLiterals()}</li>
 *     <li>【update】字符串可以在 switch 语句块中使用（Strings in switch Statements）{@link Java7#addStringsInSwitchStatements()}</li>
 *     <li>【new】创建通用实例的类型推理（Type Inference for Generic Instance Creation）{@link Java7#addTypeInference()}</li>
 *     <li>【update】改进编译器警告和错误（Improved Compiler Warnings and Errors When Using Non-Reifiable Formal Parameters with Varargs Methods）{@link Java7#improveCompilerWarningsAndErrors()}</li>
 *     <li>【new】try-with-resources 语句块（The try-with-resources Statement）{@link Java7#addTryWithResourcesStatement()}</li>
 *     <li>【update】改进捕获多个异常（Catching Multiple Exception Types and Rethrowing Exceptions with Improved Type Checking）{@link Java7#improveCatchingMultipleException()}</li>
 *     <li>【new】ThreadLocalRandom类。{@link Java7#addThreadLocalRandomClass()}</li>
 * </ol>
 *
 * @author @author Zero
 * @since 2018/12/25
 */
public class Java7 {
    /**
     * 新增二进制字面值
     * <p>
     * 在 Java SE 7 中，整型（byte、short、int 和 long）也可以使用二进制数系统来表示。
     * 若要指定二进制文本，请在数字中添加前缀 0b 或 0B
     */
    public void addBinaryLiterals() {
        int binaryNumber = 0b110011;
        System.out.println("Binary Number: " + binaryNumber);
    }

    /**
     * 数字字面量可以使用下划线
     * <p>
     * 从Java 7开始，你可以在数字字面量中使用下划线以提高代码的可读性，将下划线放置在数字之间的任何位置。
     * 下划线不会影响字面量的值。这个特性在处理大型数字字面量（如常量或表示特定模式的值）时尤其有用。
     */
    public void addUnderscoresInNumericLiterals() {
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
     * 字符串可以在 switch 语句块中使用
     * <p>
     * Java 7 引入了字符串在 switch 语句中的支持。
     * 在之前的版本中，switch 语句只支持整数类型（byte、short、char 和 int），但从 Java 7 开始，可以在 switch 语句的表达式中使用 String 类型。
     */
    public void addStringsInSwitchStatements() {
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
                System.out.println("周五到了，感谢上帝！");
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
     * 从Java 7开始，引入了”Diamond Operator“（菱形操作符），可以在创建泛型类实例时使用空的尖括号（<>），由编译器根据上下文自动推断类型参数。
     */
    public void addTypeInference() {
        // 在Java 7之前，需要在两侧都显式指定类型参数
        List<String> list1 = new ArrayList<String>();

        // 在Java 7及之后，可以使用菱形操作符自动推断类型参数
        List<String> list2 = new ArrayList<>();
    }

    /**
     * 改进编译器警告和错误
     * <p>
     * 在Java SE 7中，对于具有非可具体化（non-reifiable）变长参数（在运行时无法准确地确定参数类型的参数）的方法或构造函数，编译器会在声明处生成警告。
     * 为了提高编译器生成的警告的可见性，Java SE 7 引入了 -Xlint:varargs 编译器选项。
     * 以及 @SafeVarargs 和 @SuppressW arnings({"unchecked", "varargs"}) 注解用于抑制这些警告。
     */
    public void improveCompilerWarningsAndErrors() {
        List<? extends Serializable> list = Arrays.asList(1, "Two", 3.0);
        System.out.println(list);
    }

    /**
     * 引入 try-with-resources 语句块
     * <p>
     * 使用 try-with-resources 语句，可以在 try 语句的括号中声明一个或多个资源，这些资源必须实现 AutoCloseable 接口或 Closeable 接口。
     * 在 try 语句块结束时，这些资源会自动关闭，而无需手动调用 close() 方法。
     */
    public void addTryWithResourcesStatement() {
        try (BufferedReader reader = new BufferedReader(new StringReader("Hello\nJava 7"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("line: " + line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 捕获多个异常类型并通过改进的类型检查重新引发异常
     * <p>
     * 在 Java 7 中，可以在一个 catch 块中捕获多种异常类型，这样可以更简洁地处理多个异常情况。
     */
    public void improveCatchingMultipleException() {
        // 以前的写法
        try {
            Object sum = Integer.class.getDeclaredMethod("sum", int.class, int.class).invoke(null, 3, 4);
            System.out.println("sum: " + sum);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        // 现在的写法
        try {
            Object max = Integer.class.getDeclaredMethod("max", int.class, int.class).invoke(null, 3, 4);
            System.out.println("max: " + max);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 新增ThreadLocalRandom
     * <p>
     * 新增线程安全的随机数生成器，专为多线程环境而设计，通过使用线程本地变量来避免多线程竞争，从而提高性能。
     */
    public void addThreadLocalRandomClass() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        double nextDouble = random.nextDouble(100.0, 1000.0);
        System.out.println(nextDouble);
    }
}