package org.zero;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * jdk9 新特性
 * <p>
 * 1.【new】接口增加私有方法
 * <p>
 * 2.【update】钻石操作符使用升级（Diamond operator）
 * <p>
 * 3.【update】try-with-resource语句改进
 * <p>
 * 4.【new】集合新增静态工厂方法（of()方法）
 * <p>
 * 5.【update】Stream API增强
 * <p>
 * 6.【update】@Deprecated注解新增属性
 * <p>
 * 7.【update】String类底层存储变更
 * 由原有的 char[] 变更为 byte[]，减少了底层存储的开销
 * <p>
 * 8.【new】模块系统（Java Platform Module System，JPMS）
 * <p>
 * 9.【new】JShell：Java交互式编程工具
 * <p>
 * 10.【update】Optional API
 * <p>
 * 11.【update】Process API
 * <p>
 * 12.【update】CompletableFuture API
 *
 * @author Zero
 */
public class Java9 {
    /**
     * 1.接口可定义私有方法了
     * <p>
     * 在Java 8之前，接口只能有常量和抽象方法；Java 8之后，接口新增默认方法与静态方法。现在，Java 9接口更是可以定义私有方法了。
     */
    public void privateMethod() {
        Printer printer = System.out::println;
        printer.print1("调用接口抽象方法（已通过方法引用实现）");
        printer.print2("调用接口默认方法");
        Printer.print3("调用接口静态方法");
    }

    /**
     * 2.钻石操作符（Diamond operator）升级
     * <p>
     * 在java 9中，它可以与匿名的内部类一起使用，从而提高代码的可读性
     */
    public void diamondOperator() {
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
     * 3.try-with-resources语句块改进
     * <p>
     * try-with-resources是Java 7中一个新的异常处理机制，它确保了每个资源在语句结束时关闭。
     * 所谓的资源（resource）是指在程序完成后，必须关闭的对象。
     * 所有实现了java.lang.AutoCloseable接口（其中，它包括实现了java.io.Closeable的所有对象），就可以使用try-with-resources机制。
     * <p>
     * try-with-resources声明在Java 9已得到改进。
     * 如果你已经有一个资源是final或等效于final的变量，你可以在try-with-resources语句中使用该变量，而无需在try-with-resources语句中声明一个新变量。
     */
    public void tryBlock() {
        // jdk 1.7、1.8的写法
        // 可自动关闭的资源（现实了 AutoCloseable 接口）如需自动关闭，那其初始化必须放在小括号中
        try (Writer writer = new StringWriter()) {
            writer.write("java 8");
            writer.flush();
            System.out.println(writer);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // java 9可以在外部先定义资源，然后在小括号中只写资源名
        // 但此时的资源默认为final类型的常量，不可做出修改
        Writer writer = new StringWriter();
        try (writer) {
            writer.write("java 9");
            writer.flush();
            System.out.println(writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 4.集合新增静态工厂方法（of方法）
     * <p>
     * 注意：of方法生成的集合不可更改
     */
    public void ofMethod() {
        List<String> list = List.of("aaa", "bbb", "ccc", "ddd");
        // UnsupportedOperationException
        // list.add("eee");
        System.out.println("List：" + list);

        Set<String> set = Set.of("aaa", "bbb", "ccc", "ddd");
        System.out.println("Set:" + set);

        Map<String, String> map = Map.of("K1", "aaa", "K2", "bbb", "K3", "ccc");
        System.out.println("Map：" + map);
    }

    /**
     * 5.增强了Stream API
     * <p>
     * Java 9为Stream流新增了几个方法：dropWhile、takeWhile、ofNullable，为iterate方法新增了一个重载方法。
     */
    public void streamApi() {
        // takeWhile() 方法使用一个断言作为参数，返回给定 Stream 的子集直到断言语句第一次返回 false。
        // 如果第一个值不满足断言条件，将返回一个空的 Stream。
        System.out.println("takeWhile 方法：");
        Stream.of("a", "b", "c", "", "e", "f")
                .takeWhile(s -> !s.isEmpty())
                .forEach(System.out::print);
        System.out.println();

        // dropWhile 方法和 takeWhile 作用相反的，使用一个断言作为参数，直到断言语句第一次返回 false 才返回给定 Stream 的子集。
        System.out.println("dropWhile 方法：");
        Stream.of("a", "b", "c", "", "e", "f")
                .dropWhile(s -> !s.isEmpty())
                .forEach(System.out::print);
        System.out.println();

        // 避免NPE
        System.out.println("ofNullable 方法：");
        long count = Stream.ofNullable(null)
                .count();
        System.out.println(count);

        // 避免无限流
        System.out.println("iterate 方法：");
        IntStream.iterate(3, x -> x < 10, x -> x + 3)
                .forEach(System.out::print);
    }

    /**
     * 6.@Deprecated注解新增since和forRemoval属性
     * 分别表示被注解的元素从那个版本开始过时，以及未来是否移除
     */
    @Deprecated(since = "1.1", forRemoval = true)
    public void deprecatedAnnotation() {
        System.out.println("deprecated");
    }

    /**
     * 10.Optional中新增了几个方法：or、ifPresentOrElse、stream
     */
    public void optionalApi() {
        Optional.ofNullable(null)
                .or(Optional::empty)
                .ifPresentOrElse(System.out::println, () -> System.out.println("空对象"));
    }

    /**
     * 11.Process中新增了几个方法：supportsNormalTermination、pid、onExit、toHandle、info、children、descendants
     */
    public void processApi() throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder("ping", "localhost");
        Process process = processBuilder.start();
        boolean supportsNormalTermination = process.supportsNormalTermination();
        long pid = process.pid();
        ProcessHandle.Info info = process.info();


        System.out.println("支持正常终止：" + supportsNormalTermination);
        System.out.println("pid：" + pid);
        System.out.println("info：" + info);
    }

    /**
     * 12.CompletableFuture 中新增了几个方法：newIncompleteFuture、defaultExecutor、copy、minimalCompletionStage、completeAsync、orTimeout、completeOnTimeout、delayedExecutor、completedStage、failedFuture、failedStage
     */
    public void completableFutureApi() {
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> System.out.println("ok"));

        CompletableFuture<String> newIncompleteFuture = completableFuture.newIncompleteFuture();
        newIncompleteFuture.complete("abc");
        System.out.println(newIncompleteFuture.join());

        Executor executor = completableFuture.defaultExecutor();
        System.out.println("defaultExecutor: " + executor);

        CompletableFuture<Void> copy = completableFuture.copy();
        System.out.println("copy: " + copy);

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 10).completeOnTimeout(1, 1, TimeUnit.MILLISECONDS);
        System.out.println("completeOnTimeout: " + future.join());
    }
}
