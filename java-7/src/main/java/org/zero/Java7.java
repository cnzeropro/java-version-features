package org.zero;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Zero
 * @since 2018/12/25
 */
public class Java7 {
    /**
     * 新增二进制字面值
     */
    public void addBinaryLiterals() {
        int binaryNumber = 0b110011;
        System.out.println("Binary Number: " + binaryNumber);
    }

    /**
     * 数字字面量可以使用下划线
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
     */
    public void addTypeInference() {
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
     */
    public void improveCompilerWarningsAndErrors() {
        List<?> list = this.asList(1, "Two", 3.0, false);
        System.out.println("list: " + list);
    }

    @SafeVarargs
    @SuppressWarnings("varargs")
    public final <T> List<T> asList(T... args) {
        List<T> list = new ArrayList<>();
        Collections.addAll(list, args);
        return list;
    }

    /**
     * 引入 try-with-resources 语句块
     */
    public void addTryWithResourcesStatement() {
        System.out.println("Before:");
        // 在 Java 7 之前，必须显式关闭资源
        BufferedReader reader0 = new BufferedReader(new StringReader("Hello\nJava 7"));
        try {
            String line;
            while ((line = reader0.readLine()) != null) {
                System.out.println("line: " + line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                reader0.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        System.out.println("After:");
        // try-with-resources 语句块允许自动关闭资源
        try (BufferedReader reader1 = new BufferedReader(new StringReader("Hello\nJava 7"))) {
            String line;
            while ((line = reader1.readLine()) != null) {
                System.out.println("line: " + line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 捕获多个异常类型并通过改进的类型检查重新引发异常
     */
    public void improveCatchingMultipleException() {
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
     */
    public void addThreadLocalRandomClass() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        double nextDouble = random.nextDouble(100.0, 1000.0);
        System.out.println("randomNum: " + nextDouble);
    }
}