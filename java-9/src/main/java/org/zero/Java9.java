package org.zero;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * <a href="https://docs.oracle.com/javase/9">Oracle JDK 9 Documentation</a>
 * <h2>Language Features</h2>
 * <ol>
 *     <li>【new】Java 平台模块系统（JPMS: Java Platform Module System）。参见：{@link module-info.java}</li>
 *     <li>【new】接口增加私有方法（Support for Private Interface Methods）{@link Java9#addPrivateMethodInInterface()}</li>
 *     <li>【update】try-with-resources 语句（More Concise try-with-resources Statements）{@link Java9#improveTryWithResourcesStatement()}</li>
 *     <li>【update】私有实例方法允许使用 @SafeVarargs 注解（@SafeVarargs Annotation Allowed on Private Instance Methods）{@link Java9#enhanceSafeVarargsAnnotation()}</li>
 *     <li>【update】改进钻石操作符（Diamond Syntax and Anonymous Inner Classes）{@link Java9#upgradeDiamondOperator()}</li>
 *     <li>【update】下划线字符（“_”）不是法定名称（Underscore Character Not Legal Name）<p>
 *     如果使用下划线字符作为标识符，则无法再编译源代码了。
 *     </li>
 * </ol>
 *
 * <h2>Previews and Incubator</h2>
 * <ol>
 * </ol>
 *
 * <h2>Libraries Improvements</h2>
 * <ol>
 * </ol>
 *
 * <h2>Changes</h2>
 * <ol>
 *     <li>【update】@Deprecated注解新增属性。{@link Java9#improveDeprecatedAnnotation()}</li>
 *     <li>【new】集合新增静态工厂方法：of方法。{@link Java9#addOfMethod()}</li>
 *     <li>【update】Stream 类新增方法。{@link Java9#enhanceStreamApi()}</li>
 *     <li>【update】Optional 类新增方法。{@link Java9#enhanceOptionalApi()}</li>
 *     <li>【update】Process 类新增方法。{@link Java9#enhanceProcessApi()}</li>
 *     <li>【update】CompletableFuture 类新增方法。{@link Java9#enhanceCompletableFutureApi()}</li>
 *     <li>【update】新的 JDK 版本控制方案（New Version-String Scheme）{@link Java9#updateVersioningScheme()}</li>
 *     <li>【update】默认使用 CLDR 语言环境数据（Use CLDR locale data by default）<p>
 *     在 JDK 9 中，默认区域设置数据使用派生自 Unicode 联盟的通用区域设置数据存储库 （CLDR） 的数据。
 *     但是，CLDR 不为大多数3个字母的时区 ID 提供本地化的显示名称，因此显示名称可能与 JDK 8 及更早版本不同。
 *     因此，JDK 继续提供旧版 JRE 语言环境数据，使用 system 属性：java.locale.providers 可用于配置查找顺序。
 *     要启用与 JDK 8 兼容的行为，可以使用以下命令设置系统属性：{@code -Djava.locale.providers=COMPAT,SPI}
 *     </li>
 *     <li>【remove】取消对1.5及更早版本源代码和目标选项的支持（Remove support for 1.5 and earlier source and target options）。
 *     该版本 javac 命令不再支持 -source 或 -target 选项在 6/1.6 之前版本的值。
 *     但是，较旧的类文件仍可由 javac 读取，旧版本的源代码可以移植到较新的源代码级别。
 *     要生成早于 JDK 6 发行版可用的类文件，可以使用 JDK 6、7 或 8 发行版系列中的 javac 文件。
 *     </li>
 *     <li>【update】将默认 GC 更改为 G1（Change default GC to G1）<p>
 *     在 JDK 9 中，当未显式指定垃圾回收器时，默认垃圾回收器为 G1。
 *     与面向吞吐量的收集器（如 Parallel GC）相比，G1 为大多数用户提供了更好的整体体验。
 *     </li>
 *     <li>【new】模块化运行时映像（Modular Run-Time Images）</li>
 *     <li>【update】将类文件版本更新为 53.0（Update class file version to 53.0）</li>
 *     <li>【update】紧凑、小巧的 Strings（Compact Strings）<p>
 *     {@link java.lang.String}、{@link java.lang.StringBuilder} 和 {@link java.lang.StringBuffer}类的内部存储由原有的 char[] 变更为 byte[]。
 *     此项改动减少了底层存储的开销，良好情况下（Strings 对象全是单字节字符 Strings），可将存储字符所需的空间量减少 50%。
 *     如果要回到 Java 8 的支持，Java 9 中引入了一个新的 jvm 选项{@code -XX：-CompactStrings} 来禁用此功能。
 *     </li>
 *     <li>【new】引入弃用警告（Deprecation Warnings Introduced）{@link Java9#introduceDeprecationWarning()}</li>
 *     <li>【new】Unicode 8 支持（Unicode 8 support）<p>
 *     自支持 Unicode 6.2.0 的 JDK 8 发布以来，Unicode 8.0 引入了以下新功能，现在这些功能现在包含在 JDK 9 中：
 *     <ul>
 *         <li>10555 new characters</li>
 *         <li>42 new blocks</li>
 *         <li>29 scripts</li>
 *     </ul>
 *     </li>
 *     <li>【update】基于 UTF-8 编码的 Properties 文件（UTF-8 based Properties Files）<p>
 *     ResourceBundle 现在支持 UTF-8 编码的属性文件，并在需要时自动回退到 ISO-8859-1 编码。
 *     </li>
 *     <li>【new】使用 deprecated javadoc 标签而不使用 @Deprecated 注解，编译器将发出警告（Compiler will emit a warning if deprecated javadoc tag is used without @Deprecated annotation）<p>
 *     如果在元素上使用 javadoc deprecated 标记，而没有使用 @Deprecated 注解该元素，那么默认情况下，编译器将为此生成新的警告。
 *     可以通过命令行选项{@code -Xlint:-dep-ann}或使用{@code @SuppressWarnings("dep-ann")}注解来抑制新警告。
 *     </li>
 *     <li>【new】JShell：Java 交互式编程工具（JShell -- New tool and API for interactive Java）</li>
 *     <li>【remove】弃用盒装基元构造函数（Deprecation of Boxed Primitive Constructors）</li>
 *     <li>【remove】弃用{@link java.lang.Object#finalize}方法（ Deprecate Object.finalize）<p>
 *     Object.finalize 方法已被弃用。终结机制本身就存在问题，并可能导致性能问题、死锁和挂起。
 *     {@link java.lang.ref.Cleaner}和{@link java.lang.ref.PhantomReference}提供更灵活、更高效的方法，以便在对象变得不可访问时释放资源。
 *     </li>
 * </ol>
 *
 * @author Zero
 */
public class Java9 {
    /**
     * 新的 JDK 版本控制方案
     * <p>
     * JDK 9 使用了新的版本字符串格式。最显著的更改是从版本字符串的开头删除了“1.”，并使用3个或更多单独的元素来指定主要、次要和安全更新。
     */
    public void updateVersioningScheme() {
        Runtime.Version version = Runtime.version();
        System.out.println(version);
    }

    /**
     * 接口允许定义私有方法
     * <p>
     * 在 Java 8 之前，接口只能有常量和抽象方法。
     * Java 8 之后，接口新增默认方法与静态方法。
     * 现在，Java 9 接口更是可以定义私有方法了。
     */
    public void addPrivateMethodInInterface() {
        Printer printer = System.out::println;
        printer.print1("调用接口抽象方法（已通过方法引用实现）");
        printer.print2("调用接口默认方法");
        Printer.print3("调用接口静态方法");
    }

    /**
     * 钻石操作符（Diamond Operator）升级
     * <p>
     * 在 java 9 中，它可以与匿名的内部类一起使用，从而提高代码的可读性
     */
    public void upgradeDiamondOperator() {
        // Java 9 以前的写法（必须在后面也指定类型）
        Comparator<String> comparator1 = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }
        };
        System.out.println(comparator1.compare("abc", "abd"));

        // Java 9 现在的写法（无需指定）
        Comparator<String> comparator2 = new Comparator<>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }
        };
        System.out.println(comparator2.compare("abc", "abd"));
    }

    /**
     * try-with-resources 语句块改进
     * <p>
     * try-with-resources 是 Java 7 中一个新的异常处理机制，它确保了每个资源在语句结束时关闭。
     * 所谓的资源（resource）是指在程序完成后，必须关闭的对象。
     * 所有实现了 java.lang.AutoCloseable 接口（其中，它包括实现了 java.io.Closeable 的所有对象），就可以使用 try-with-resources 机制。
     * <p>
     * try-with-resources 声明在 Java 9 已得到改进。
     * 如果已经有一个资源是 final 或等效于 final 的变量，就可以在 try-with-resources 语句中使用该变量，而无需在 try-with-resources 语句中声明一个新变量。
     */
    public void improveTryWithResourcesStatement() {
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
     * 集合新增静态工厂方法（of方法）
     * <p>
     * 注意：of 方法生成的集合不可更改
     */
    public void addOfMethod() {
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
     * Stream 类新增方法
     * <p>
     * 新增：
     * {@link Stream#dropWhile}、
     * {@link Stream#takeWhile}、
     * {@link Stream#ofNullable}
     * <p>
     * 重载：
     * {@link Stream#iterate(Object, Predicate, UnaryOperator)}
     */
    public void enhanceStreamApi() {
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
     * Deprecated注解新增since和forRemoval属性
     * <p>
     * 分别表示被注解的元素从那个版本开始过时，以及未来是否移除
     */
    @Deprecated(since = "1.1", forRemoval = true)
    public void improveDeprecatedAnnotation() {
        System.out.println("used deprecated method");
    }

    /**
     * 引入弃用警告。
     * <p>
     * 使用了 @Deprecated 注解的代码，会在 javac 编译过程中发出各种警告。
     * 但是，立即迁移代码并不总是可行的。因此，提供了两种机制来控制发出的 javac 警告：命令行选项和源代码中的注释。
     * javac 命令行选项 -Xlint:deprecation 或 -Xlint:removal 将启用相应的警告类型；反之，-Xlint:-deprecation 或 -Xlint:-removal 禁用相应的警告类型。
     * 另一种机制是将 @SuppressWarnings("deprecation") or @SuppressWarnings("removal") 注释添加到源代码中，以禁止该声明中发出的相应警告类型。
     */
    @SuppressWarnings("deprecation")
    public void introduceDeprecationWarning() {
        this.improveDeprecatedAnnotation();
    }

    /**
     * Optional 类新增方法
     * <p>
     * 包括：
     * {@link Optional#or}、
     * {@link Optional#ifPresentOrElse}、
     * {@link Optional#stream}
     */
    public void enhanceOptionalApi() {
        Optional.ofNullable(null)
                .or(Optional::empty)
                .ifPresentOrElse(System.out::println, () -> System.out.println("空对象"));
    }

    /**
     * Process 类新增方法
     * <p>
     * 包括：
     * {@link Process#supportsNormalTermination}、
     * {@link Process#pid}、
     * {@link Process#onExit}、
     * {@link Process#toHandle}、
     * {@link Process#info}、
     * {@link Process#children}、
     * {@link Process#descendants}
     */
    public void enhanceProcessApi() throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder("ping", "localhost");
        Process process = processBuilder.start();
        boolean supportsNormalTermination = process.supportsNormalTermination();
        long pid = process.pid();
        ProcessHandle.Info info = process.info();

        System.out.println("支持正常终止：" + supportsNormalTermination);
        System.out.println("pid：" + pid);
        System.out.println("进程信息快照：" + info);
    }

    /**
     * CompletableFuture 类新增方法
     * <p>
     * CompletableFuture 中新增了几个方法：
     * {@link CompletableFuture#newIncompleteFuture}、
     * {@link CompletableFuture#defaultExecutor}、
     * {@link CompletableFuture#copy}、
     * {@link CompletableFuture#minimalCompletionStage}、
     * {@link CompletableFuture#completeAsync}、
     * {@link CompletableFuture#orTimeout}、
     * {@link CompletableFuture#completeOnTimeout}、
     * {@link CompletableFuture#delayedExecutor}、
     * {@link CompletableFuture#completedStage}、
     * {@link CompletableFuture#failedFuture}、
     * {@link CompletableFuture#failedStage}
     */
    public void enhanceCompletableFutureApi() {
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> System.out.println("ok"));

        CompletableFuture<String> newCompleteFuture = completableFuture.newIncompleteFuture();
        newCompleteFuture.complete("abc");
        System.out.println(newCompleteFuture.join());

        Executor executor = completableFuture.defaultExecutor();
        System.out.println("defaultExecutor: " + executor);

        CompletableFuture<Void> copy = completableFuture.copy();
        System.out.println("copy: " + copy);

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 10).completeOnTimeout(1, 1, TimeUnit.MILLISECONDS);
        System.out.println("completeOnTimeout: " + future.join());
    }

    /**
     * 允许在私有实例方法上使用 @SafeVarargs 注解
     * <p>
     * 原本 @SafeVarargs 注解只能应用于无法重写的方法，这些方法包括静态方法、最终实例方法。现在，Java SE 9 中 @SafeVarargs 可用于私有实例方法。
     */
    public void enhanceSafeVarargsAnnotation() {
        this.m(10, "aaa", true, 6.5);
    }

    @SafeVarargs
    private <T> void m(T... args) {
        System.out.println(Arrays.toString(args));
    }
}
