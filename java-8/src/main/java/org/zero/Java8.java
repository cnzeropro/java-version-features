package org.zero;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.nio.charset.StandardCharsets;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.TimeZone;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * jdk1.8 新特性
 * <p>
 * 1.【new】Lambda表达式
 * <p>
 * 2.【new】方法引用
 * <p>
 * 3.【new】构造器引用
 * <p>
 * 4.【new】数组引用
 * <p>
 * 5.【new】四大内置核心函数式接口
 * <p>
 * 6.【new】接口的默认方法与静态方法
 * <p>
 * 7.【new】Stream管道流操作
 * <p>
 * 8.【new】Optional类
 * <p>
 * 9.【new】日期时间API（Date-Time API(JSR 310)）
 * <p>
 * 10.【new】Base64编码成为Java类库的标准
 * <p>
 * 11.【new】Nashorn JavaScript引擎（取代Rhino JavaScript引擎）
 * <p>
 * 12.【new】@Repeatable定义重复注解
 * <p>
 * 13.【new】对数组的并行操作
 * <p>
 * 14.【new】JUC包新增类用于并发增强
 * <p>
 * 15.【new】类依赖分析工具：jdeps
 * <p>
 * 16.【update】JVM内存永久代（Permgen）已经被元空间（Metaspace）替换（JEP 122）
 * JVM参数-XX:PermSize和–XX:MaxPermSize分别被XX:MetaSpaceSize和-XX:MaxMetaspaceSize代替
 *
 * @author Zero
 */
public class Java8 {
    /**
     * 1.Lambda表达式
     * <p>
     * 使用格式：形参列表 -> Lambda体
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
     * 2、“->”右边（Lambda体）：
     * <ul>
     *     <li>lambda体（只有一条语句）（有无返回值都不用写return）</li>
     *     <li>{lambda体（多条语句）}（有返回值时需写return）</li>
     * </ul>
     * 应用场景：实现函数式接口
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
     * 2.方法引用
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
     * 3.构造器引用
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
     * 4.数组引用
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
     * 5.四大内置核心函数式接口
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
    public void functionalInterface() {
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
        System.out.println("该同学成绩是否及格：" +
                functionalInterfaces.predicateTest(student, s -> s.getScore().compareTo(60.0) > 0));

        // 函数型接口
        System.out.println("该同学基本信息：" +
                functionalInterfaces.functionTest(student, s -> s.getName() + "&" + s.getSex() + "&" + s.getAge()));
    }

    /**
     * 6.接口新增默认方法与静态方法
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
     * 7.新增Stream管道流操作
     * <p>
     * 此处展示少数stream中的用法，详情参见stream相关API
     * 主要用于非关系数据库数据的处理
     */
    public void streamApi() {
        // 选出性别为‘M’的学生，并将其性别改为‘男’，最后按成绩降序排序
        Map<Integer, Map<String, Student>> students = Arrays.asList(
                        new Student("CDTU-1", "小明", 'M', 18, 89.6),
                        new Student("CDTU-2", "小芳", 'F', 21, 92.8),
                        new Student("CDTU-3", "小龙", 'M', 18, 97.1),
                        new Student("CDTU-4", "小红", 'F', 19, 85.4),
                        new Student("CDTU-5", "小方", 'M', 20, 88.5),
                        new Student("CDTU-6", "小吴", 'M', 22, 96.0),
                        new Student("CDTU-7", "小亮", 'M', 18, 95.7),
                        new Student("CDTU-8", "小林", 'F', 23, 91.8),
                        new Student("CDTU-9", "小王", 'M', 21, 94.2),
                        new Student("CDTU-10", "小魏", 'M', 20, 86.3))
                // 并行流，一般比串行流stream()快，但因为其依赖于Fork/Join框架，所以最终处理出的数据并不是和原数据顺序保持一致
                .parallelStream()
                // 过滤出sex为M的数据
                .filter(s -> s.getSex() == 'M')
                // 修改数据
                .peek(s -> s.setSex('男'))
                // 排序
                .sorted(Comparator.comparing(Student::getScore).reversed())
                // 转成List集合
                .collect(Collectors.groupingBy(Student::getAge, Collectors.toMap(Student::getName, Function.identity(), (s1, s2) -> s2, LinkedHashMap::new)));

        System.out.println(students);
    }

    /**
     * 8.新增Optional类（容器类）
     * <p>
     * 用于避免臭名昭著的空指针异常
     */
    public void optionalClass() {
        Student studentIn = new Student("123456", "小明", 'M', 18, 65.74);
        Student studentOut = Optional.of(studentIn)
                // .filter(s -> s.getAge() > 10)
                .filter(s -> s.getAge() > 20)
                .map(s -> {
                    s.setScore(100.0);
                    return s;
                })
                .orElse(new Student("Undefined", "Unknown", 'U', -1, 0.0));

        System.out.println(studentOut);
    }

    /**
     * 9.增加新的时间日期API
     * <p>
     * 在旧版的Java中，日期时间API存在诸多问题，其中有：
     * <ul>
     *     <li>非线程安全 − java.util.Date 是非线程安全的，所有的日期类都是可变的，这是Java日期类最大的问题之一。</li>
     *     <li>设计很差 − Java的日期/时间类的定义并不一致，在java.util和java.sql的包中都有日期类，此外用于格式化和解析的类在java.text包中定义。java.util.Date同时包含日期和时间，而java.sql.Date仅包含日期，将其纳入java.sql包并不合理。另外这两个类都有相同的名字，这本身就是一个非常糟糕的设计。</li>
     *     <li>时区处理麻烦 − 日期类并不提供国际化，没有时区支持，因此Java引入了java.util.Calendar和java.util.TimeZone类，但他们同样存在上述所有的问题。</li>
     * </ul>
     */
    public void dateTimeApi() {
        Date date = new Date();
        System.out.println("Date：" + date);

        Calendar calendar = Calendar.getInstance();
        System.out.println("Calendar：" + calendar);

        TimeZone timeZone = TimeZone.getDefault();
        System.out.println("TimeZone：" + timeZone);

        // java 8 新的时间API
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
     * 10.Base64编码成为Java类库的标准
     */
    public void base64() {
        String srcString = "我是一串小小的、可爱的 Java 字符串";
        System.out.println("原字串：" + srcString);

        Base64.Encoder encoder = Base64.getEncoder();
        String encodeString = encoder.encodeToString(srcString.getBytes(StandardCharsets.UTF_8));
        System.out.println("编码字串：" + encodeString);

        Base64.Decoder decoder = Base64.getDecoder();
        String decodeString = new String(decoder.decode(encodeString), StandardCharsets.UTF_8);
        System.out.println("解密字串：" + decodeString);
    }

    /**
     * 11.新增Nashorn JavaScript引擎（取代Rhino JavaScript引擎）
     * <p>
     * 也可以通过[jjs func.js]命令接受js源码并执行
     */
    public void nashorn() throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        System.out.println(engine.getClass().getName());
        System.out.println("Result: " + engine.eval("function f() { return 1; }; f() + 1;"));
    }

    /**
     * 12.新增@Repeatable定义重复注解
     */
    @Description("描述1")
    @Description("描述2")
    public void repeatableAnnotation() {
        String description = Optional.ofNullable(this.getClass())
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
     * 13.提供了对数组的并行操作
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
}
