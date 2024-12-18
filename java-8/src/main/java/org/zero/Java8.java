package org.zero;

import lombok.NonNull;
import lombok.SneakyThrows;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.lang.annotation.ElementType;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TimeZone;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.DoubleAccumulator;
import java.util.concurrent.atomic.DoubleAdder;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <a href="https://openjdk.org/projects/jdk8/">JDK 8</a>
 * <a href="https://docs.oracle.com/javase/8/docs/">Java Platform Standard Edition 8 Documentation</a>
 * <h1>Features</h1>
 * <ol>
 *     <li>Lambda 表达式（Lambda Expressions）{@link #lambdaExpression()}</li>
 *     <li>方法引用（Method References）{@link #methodReference()}</li>
 *     <li>构造器引用{@link #constructorReference()}</li>
 *     <li>数组引用{@link #arrayReference()}</li>
 *     <li>四大内置的核心函数式接口{@link #coreFunctionalInterface()}</li>
 *     <li>接口新增默认与静态方法{@link #defaultAndStaticMethod()}</li>
 *     <li>Stream 管道流操作{@link #stream()}</li>
 *     <li>Optional 容器类{@link #optional()}</li>
 *     <li>支持无符号算术{@link #unsignedArithmetic()}</li>
 *     <li><a href="https://openjdk.org/jeps/101">101</a>：改进类型推断（Generalized Target-Type Inference）{@link #typeInference()}</li>
 *     <li><a href="https://openjdk.org/jeps/104">104</a>：类型批注（Type Annotations）{@link #typeAnnotation()}</li>
 *     <li><a href="https://openjdk.org/jeps/118">118</a>：方法参数反射（Method parameter reflection）{@link #methodParameterReflection()}</li>
 *     <li><a href="https://openjdk.org/jeps/150">150</a>：日期时间 API（Date-Time API(JSR 310)）{@link #dateTimeApi()}</li>
 *     <li><a href="https://openjdk.org/jeps/180">180</a>：改善 HashMap 相关性能（Handle Frequent HashMap Collisions with Balanced Trees）{@link #hashMap()}</li>
 *     <li><a href="https://openjdk.org/jeps/155">155</a>：JUC 包新增并发相关的类（Concurrency Updates）{@link #juc()}</li>
 *     <li><a href="https://openjdk.org/jeps/120">120</a>：@Repeatable 注解（Repeating Annotations）{@link #repeatableAnnotation()}</li>
 *     <li><a href="https://openjdk.org/jeps/103">103</a>：数组的并行操作（Parallel Array Sorting）{@link #parallelArray()}</li>
 *     <li><a href="https://openjdk.org/jeps/135">135</a>：Base64 成为 Java 类库的标准（Base64 Encoding & Decoding）{@link #base64()}</li>
 *     <li><a href="https://openjdk.org/jeps/174">174</a>：Nashorn JavaScript 引擎{@link #nashornScriptEngine()}</li>
 *     <li><a href="https://openjdk.org/jeps/122">122</a>：移除永久代（Remove the Permanent Generation）{@link #permanentGeneration()}</li>
 * </ol>
 *
 * @author Zero
 */
public class Java8 {
    /**
     * Lambda 表达式
     * <p>
     * 使用格式：形参列表 -> Lambda 体
     * <p>
     * 1、“->”左边（形参列表）：
     * <table>
     *     <tr>
     *         <th>实例</th>
     *         <th>说明</th>
     *     </tr>
     *     <tr>
     *         <td>()</td>
     *         <td>无参</td>
     *     </tr>
     *     <tr>
     *         <td>x</td>
     *         <td>只有一个参数</td>
     *     </tr>
     *     <tr>
     *         <td>(a,...)</td>
     *         <td>多参</td>
     *     </tr>
     * </table>
     * 2、“->”右边（Lambda 体）：
     * <ul>
     *     <li>lambda 体（只有一条语句）（有无返回值都不用写 return）</li>
     *     <li>{lambda 体（多条语句）}（有返回值时需写 return）</li>
     * </ul>
     * 应用场景：实现函数式接口。
     */
    public void lambdaExpression() {
        // 传统的实现方法
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("传统实现：使用匿名接口重写抽象方法实现");
            }
        };
        runnable1.run();

        // jdk 1.8的实现方法
        Runnable runnable2 = () -> System.out.println("jdk 1.8实现：使用Lambda表达式实现");
        runnable2.run();
    }

    /**
     * 方法引用
     * <p>
     * 本质与Lambda表达式一样，也是函数式接口的实例。
     * 具体分为以下三种情况：
     * <ul>
     *     <li>对象::实例方法名</li>
     *     <li>类::静态方法名</li>
     *     <li>类::实例方法名</li>
     * </ul>
     * 引用场景：
     * 当要传递给Lambda体的操作，已经有实现的方法了，就可使用方法引用。
     */
    public void methodReference() {
        System.out.println("~~~对象::实例方法名~~~");
        String string = "java";
        Predicate<String> predicate1 = s -> string.equals(s);
        System.out.println("Lambda表达式实现：" + predicate1.test("Java"));
        Predicate<String> predicate2 = string::equals;
        System.out.println("方法引用实现：" + predicate2.test("Java"));
        System.out.println("*********************************************");

        System.out.println("~~~类::静态方法名~~~");
        Comparator<Integer> comparator1 = (i1, i2) -> Integer.compare(i1, i2);
        System.out.println("Lambda表达式实现：" + comparator1.compare(34, 97));
        Comparator<Integer> comparator2 = Integer::compare;
        System.out.println("方法引用实现：" + comparator2.compare(34, 97));
        System.out.println("*********************************************");

        System.out.println("~~~类::实例方法名~~~");
        Function<String, String> function1 = s -> s.toLowerCase();
        System.out.println("Lambda表达式实现：" + function1.apply("JAVA8"));
        Function<String, String> function2 = String::toLowerCase;
        System.out.println("方法引用实现：" + function2.apply("JAVA8"));
    }

    /**
     * 构造器引用
     * <p>
     * 本质与方法引用相同，只不过new表示类的空参构造器（方法）
     * <p>
     * xxx::new
     */
    public void constructorReference() {
        Supplier<Student> supplier1 = () -> new Student();
        System.out.println("Lambda表达式实现：" + supplier1.get());

        Supplier<Student> supplier2 = Student::new;
        System.out.println("构造器引用实现：" + supplier2.get());
    }

    /**
     * 数组引用
     * <p>
     * 本质与构造器引用相同
     * <p>
     * xxx[]::new
     */
    public void arrayReference() {
        Function<Integer, Student[]> function1 = i -> new Student[i];
        System.out.println("Lambda表达式实现：" + Arrays.toString(function1.apply(3)));

        Function<Integer, Student[]> function2 = Student[]::new;
        System.out.println("数组引用实现：" + Arrays.toString(function2.apply(3)));
    }

    /**
     * 四大内置的核心函数式接口
     * <p>
     * 供给型接口：
     * Supplier<T>
     * T get()
     * <p>
     * 消费型接口：
     * Consumer<T>
     * void accept(T t)
     * <p>
     * 断言型接口：
     * Predicate<T>
     * boolean test(T t)
     * <p>
     * 函数型接口：
     * Function<T, R>
     * R apply(T t)
     */
    public void coreFunctionalInterface() {
        FunctionalInterfaces functionalInterfaces = new FunctionalInterfaces();

        // 供给型接口
        Student student = functionalInterfaces.supplierTest(() ->
                new Student("YY-" + (int) (Math.random() * 1e8) + 1,
                        "小小",
                        '女',
                        (int) (Math.random() * 10) + 16,
                        (int) (Math.random() * 10000) / 100.0)
        );

        System.out.print("该同学是：");
        // 消费型接口
        functionalInterfaces.consumerTest(student, System.out::println);

        // 断言型接口
        System.out.println("该同学成绩是否及格：" + functionalInterfaces.predicateTest(student, s -> s.getScore().compareTo(60.0) > 0));

        // 函数型接口
        System.out.println("该同学基本信息：" + functionalInterfaces.functionTest(student, s -> s.getName() + "&" + s.getSex() + "&" + s.getAge()));
    }

    /**
     * 接口新增默认方法与静态方法
     * <p>
     * 现在接口除了抽象方法，还可定义默认和静态方法
     */
    public void defaultAndStaticMethod() {
        Printer printer = System.out::println;
        printer.print1("调用接口抽象方法（已通过方法引用实现）");
        printer.print2("调用接口默认方法");
        Printer.print3("调用接口静态方法");
    }

    /**
     * 新增Stream管道流操作
     * <p>
     * 此处展示少数stream中的用法，详情参见{@link Stream}相关API。
     * <p>
     * 作用：主要用于集合数据的处理
     */
    public void stream() {
        List<Student> data = Arrays.asList(
                new Student("CDTU-1", "小明", 'M', 18, 89.6),
                new Student("CDTU-2", "小芳", 'F', 21, 92.8),
                new Student("CDTU-3", "小龙", 'M', 18, 97.1),
                new Student("CDTU-4", "小红", 'F', 19, 85.4),
                new Student("CDTU-5", "小方", 'M', 20, 88.5),
                new Student("CDTU-6", "小吴", 'M', 22, 96.0),
                new Student("CDTU-7", "小亮", 'M', 18, 95.7),
                new Student("CDTU-8", "小林", 'F', 23, 91.8),
                new Student("CDTU-9", "小王", 'M', 21, 94.2),
                new Student("CDTU-10", "小魏", 'M', 20, 86.3));

        // 并行流，一般比串行流 stream() 快，但因为其依赖于 Fork/Join 框架，所以最终处理出的数据并不是和原数据顺序保持一致
        Map<Integer, Map<String, Student>> students = data.parallelStream()
                // 过滤出成绩大于90的数据
                .filter(s -> s.getScore() > 90.0)
                // 修改性别‘M’为‘男’，‘F’为‘女’
                .peek(s -> {
                    if (s.getSex() == 'M') {
                        s.setSex('男');
                    } else if (s.getSex() == 'F') {
                        s.setSex('女');
                    }
                })
                // 成绩降序排序
                .sorted(Comparator.comparing(Student::getScore).reversed())
                // 按照年龄分组并转成集合
                .collect(Collectors.groupingBy(Student::getAge, Collectors.toMap(Student::getName, Function.identity(), (oldVal, newVal) -> newVal, LinkedHashMap::new)));

        System.out.println("原始数据：" + data);
        System.out.println("处理后的数据：" + students);
    }

    /**
     * 新增Optional类（容器类）
     * <p>
     * 用于避免臭名昭著的空指针异常
     */
    public void optional() {
        Student student = new Student("123456", "小明", null, 18, 65.74);
        Character sex = Optional.of(student)
                .map(Student::getSex)
                .filter(s -> s == 'M')
                .map(s -> '男')
                .orElse('U');
        System.out.println("学生性别：" + sex);
    }

    /**
     * 新的时间日期 API
     * <p>
     * 在旧版的 Java 中，日期时间 API 存在诸多问题，其中有：
     * <ul>
     *     <li>非线程安全 − {@link java.util.Date} 是非线程安全的，所有的日期类都是可变的，这是 Java 日期类最大的问题之一。</li>
     *     <li>设计很差 − Java 的日期/时间类的定义并不一致，在 <code>java.util</code> 和 <code>java.sql</code> 的包中都有日期类，此外用于格式化和解析的类在 <code>java.text</code> 包中定义。
     *     {@link java.util.Date} 同时包含日期和时间，而 {@link java.sql.Date} 仅包含日期，将其纳入 <code>java.sql</code> 包并不合理。
     *     另外这两个类都有相同的名字，这本身就是一个非常糟糕的设计。</li>
     *     <li>时区处理麻烦 − 日期类并不提供国际化，没有时区支持，因此 Java 引入了 {@link java.util.Calendar} 和 {@link java.util.TimeZone} 类，但他们同样存在上述所有的问题。</li>
     * </ul>
     */
    public void dateTimeApi() {
        Date date = new Date();
        System.out.println("Date：" + date);
        Calendar calendar = Calendar.getInstance();
        System.out.println("Calendar：" + calendar);
        TimeZone timeZone = TimeZone.getDefault();
        System.out.println("TimeZone：" + timeZone);

        // java 8 新的时间 API
        Instant instant = Instant.now();
        System.out.println("Instant：" + instant);
        LocalDate localDate = LocalDate.now();
        System.out.println("LocalDate：" + localDate);
        LocalTime localTime = LocalTime.now();
        System.out.println("LocalTime：" + localTime);
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("LocalDateTime：" + localDateTime);
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println("ZonedDateTime：" + zonedDateTime);
        ZoneId zoneId = ZoneId.systemDefault();
        System.out.println("ZoneId：" + zoneId);
        Clock clock = Clock.systemDefaultZone();
        System.out.println("Clock：" + clock);
    }

    /**
     * Base64 编码成为 Java 类库的标准
     */
    public void base64() {
        String srcString = "我是一串小小的、可爱的 Java 字符串";
        System.out.println("原字串：" + srcString);

        String encodeString = Base64.getEncoder().encodeToString(srcString.getBytes(StandardCharsets.UTF_8));
        System.out.println("编码字串：" + encodeString);

        String decodeString = new String(Base64.getDecoder().decode(encodeString), StandardCharsets.UTF_8);
        System.out.println("解码字串：" + decodeString);
    }

    /**
     * 新增 Nashorn JavaScript 引擎，用于取代 Rhino JavaScript 引擎
     * <p>
     * 在 Java 环境下，除了通过代码运行 js 源码，也可以通过 <code>jjs func.js</code> 命令接受 js 源码并执行
     */
    @SneakyThrows
    public void nashornScriptEngine() {
        ScriptEngineManager manager = new ScriptEngineManager();
        List<String> engineInfos = manager.getEngineFactories()
                .stream()
                .map(scriptEngineFactory -> String.format("Script Engine Name: %s, Version: %s", scriptEngineFactory.getEngineName(), scriptEngineFactory.getEngineVersion()))
                .collect(Collectors.toList());
        System.out.println("Script Engine Factories: " + engineInfos);

        ScriptEngine engine = manager.getEngineByName("JavaScript");
        System.out.println("JavaScript Engine Name: " + engine.getClass().getName());
        String script = "function f() { return 1; }; f() + 1;";
        Object result = engine.eval(script);
        System.out.println("Result: " + result);
    }

    /**
     * 新增@Repeatable注解
     * <p>
     * 可用于定义重复注解
     */
    @Description("描述1")
    @Description("描述2")
    @Description("描述3")
    public void repeatableAnnotation() {
        String description = Optional.of(this.getClass())
                .map(c -> {
                    try {
                        return c.getDeclaredMethod("repeatableAnnotation");
                    } catch (NoSuchMethodException e) {
                        throw new RuntimeException(e);
                    }
                })
                .map(m -> m.getAnnotationsByType(Description.class))
                .map(Arrays::stream)
                .orElse(Stream.empty())
                .map(Description::value)
                .collect(Collectors.joining("，"));
        System.out.println("描述：" + description);
    }

    /**
     * 提供了对数组的并行操作
     * <p>
     * Arrays 工具类提供相关 parallel 开头的方法，用于进行对数组相关的并行操作
     */
    public void parallelArray() {
        double[] nums = new double[100];
        System.out.println("初始化后：" + Arrays.toString(nums));
        Arrays.parallelSetAll(nums, operand -> Math.random() * operand);
        System.out.println("内容填充后：" + Arrays.toString(nums));
        Arrays.parallelPrefix(nums, ((left, right) -> Math.random() * (right - left)));
        System.out.println("内容处理后：" + Arrays.toString(nums));
        Arrays.parallelSort(nums);
        System.out.println("排序后：" + Arrays.toString(nums));
    }

    /**
     * JUC 包新增相关类
     * <p>
     * 用于多线程与并发增强
     */
    public void juc() {
        LongAdder longAdder = new LongAdder();
        longAdder.increment();
        System.out.println(longAdder.sum());
        LongAccumulator longAccumulator = new LongAccumulator(Long::sum, 1L);
        longAccumulator.accumulate(2L);
        System.out.println(longAccumulator.get());
        DoubleAdder doubleAdder = new DoubleAdder();
        doubleAdder.add(1.5);
        System.out.println(doubleAdder.sum());
        DoubleAccumulator doubleAccumulator = new DoubleAccumulator(Double::sum, 1.0);
        doubleAccumulator.accumulate(1.5);
        System.out.println(doubleAccumulator.get());

        CompletableFuture<String> completableFuture = CompletableFuture.completedFuture("java");
        System.out.println(completableFuture.join());

        // 带戳锁
        StampedLockExample stampedLockExample = new StampedLockExample();
        // 写操作
        stampedLockExample.write(Math.random());
        // 读写操作
        stampedLockExample.readAndWrite();
        // 读操作
        System.out.println("Read data: " + stampedLockExample.read());
        // 乐观读操作
        System.out.println("Optimistic read data: " + stampedLockExample.optimisticRead());
    }

    /**
     * 改进类型推断
     * <p>
     * 在 java 8 中，无需显式声明参数类型
     */
    public void typeInference() {
        // 以前写法（必须指定类型）
        List<String> list1 = Arrays.<String>asList("Apple", "Orange");
        System.out.println("Fruit list1: " + list1);

        // 现在写法（无需指定）
        List<String> list2 = Arrays.asList("Banana", "Pear");
        System.out.println("Fruit list2: " + list2);
    }

    /**
     * 引入类型批注
     * <p>
     * 在 java 8 中，可将注解应用于任何使用类型的位置的功能，而不仅仅是在声明上。此功能与可插入类型系统一起使用，可以改进代码的类型检查。
     * 只有使用了 @Target({@link ElementType#TYPE_USE}) 的注解，才能应用到其他位置。
     */
    public void typeAnnotation() {
        List<@NonNull String> list0 = new ArrayList<>();
        ArrayList<String> list1 = (@NonNull ArrayList<String>) list0;
        BigDecimal bigDecimal = new @Description("Big Decimal") BigDecimal("45536.55");
    }

    /**
     * 新增方法参数反射
     * <p>
     * 在 Java 8 中，可以通过 java.lang.reflect.Parameter 类来访问方法的参数信息。
     * <p>
     * 为此还引入了一个新的编译选项 -parameters，该选项允许在编译时保留方法参数的名称信息。
     * 如果使用了这个编译选项，那么在运行时，可以通过 Parameter.getName() 直接获取参数的实际名称，而不是默认的 arg0、arg1 等。
     */
    @SneakyThrows
    public void methodParameterReflection() {
        Method method = String.class.getMethod("indexOf", String.class, int.class);
        Parameter[] parameters = method.getParameters();
        for (Parameter parameter : parameters) {
            System.out.println("Parameter Name: " + parameter.getName());
            System.out.println("Parameter Type: " + parameter.getType());
        }
    }

    /**
     * 改善 HashMap 相关性能
     * <p>
     * Java 8 及其之后的版本在 HashMap 处理键碰撞（key collisions）方面进行了性能优化，主要引入了红黑树（Red-Black Tree）来替代链表。
     * <p>
     * 在哈希表中，当两个或多个键散列到同一个桶（bucket）时，发生了键碰撞。在 Java 8 之前，HashMap 使用链表来解决键碰撞。
     * 然而，当链表长度变得很长时，查询一个特定的键的性能可能降低，因为需要遍历链表。
     * <p>
     * 为了解决这个问题，Java 8 引入了红黑树。当链表长度达到一定阈值（默认为8），HashMap 会将链表转换为红黑树。
     * 红黑树在某些情况下（例如，查找、插入和删除操作）比链表更高效，尤其是在链表很长的情况下。
     * <p>
     * 这样一来，HashMap 在处理键碰撞时具有更好的性能，特别是对于包含大量键的哈希表。红黑树的结构使得在最坏情况下的性能保持在 O(log n) 级别。
     * <p>
     * 这一性能改进使得 HashMap 能够更好地应对各种负载因素和键分布的情况，提高了其在实际应用中的性能表现。
     */
    public void hashMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        System.out.println(map);
    }

    /**
     * 支持无符号算术
     * <p>
     * Java 语言一直以来都没有提供原生的无符号整数类型，所有整数类型都是有符号的。
     * 从 Java 8 开始，提供了一些静态方法用于执行无符号整数运算。
     * 然而这并不是引入原生的无符号整数类型，而是通过方法调用来模拟无符号运算。
     */
    public void unsignedArithmetic() {
        int i = Integer.divideUnsigned(10, 3);
        System.out.println("Integer.divideUnsigned: " + i);
        long l = Long.remainderUnsigned(4365346, 56656);
        System.out.println("Long.remainderUnsigned: " + l);
        int i1 = Byte.toUnsignedInt((byte) 1);
        System.out.println("Byte.toUnsignedInt: " + i1);
        long l1 = Short.toUnsignedLong((short) 10);
        System.out.println("Short.toUnsignedLong: " + l1);
    }

    /**
     * JVM 内存永久代（Permgen）已经被元空间（Metaspace）替换
     * <p>
     * JVM 参数 <code>-XX:PermSize</code> 和 <code>–XX:MaxPermSize</code> 分别被 <code>XX:MetaSpaceSize</code> 和 <code>-XX:MaxMetaspaceSize</code> 代替。
     */
    public void permanentGeneration() {
        System.out.println("Remove the Permanent Generation");
    }
}
