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
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * <a href="https://docs.oracle.com/javase/9/index.html">Oracle JDK 9 Documentation</a>
 * <h2>Important Enhancements and Changes</h2>
 * <ol>
 *     <li>【update】新的 JDK 版本控制方案。{@link Java9#updateVersioningScheme()}</li>
 *     <li>【new】引入模块系统（Java Platform Module System，JPMS）。参见：{@link module-info.class}</li>
 *     <li>【update】默认使用 CLDR 语言环境数据。
 *     在 JDK 9 中，默认区域设置数据使用派生自 Unicode 联盟的通用区域设置数据存储库 （CLDR） 的数据。
 *     例如，CLDR 不为大多数 3 个字母的时区 ID 提供本地化的显示名称，因此显示名称可能与 JDK 8 及更早版本不同。
 *     JDK 继续提供旧版 JRE 语言环境数据，并且 system 属性 java.locale.providers 可用于配置查找顺序。
 *     要启用与 JDK 8 兼容的行为，可以使用以下命令设置系统属性：
 *     -Djava.locale.providers=COMPAT,SPI</li>
 *     <li>【update】将默认 GC 更改为 G1。
 *     在 JDK 9 中，当未显式指定垃圾回收器时，默认垃圾回收器为 G1。
 *     与面向吞吐量的收集器（如Parallel GC）相比，G1 为大多数用户提供了更好的整体体验。</li>
 *     <li>【new】模块化运行时映像</li>
 *     <li>【remove】删除对 1.5 及更早版本的源和目标选项的支持。
 *     javac 命令不再支持 6/1.6 之前版本的 -source 或 -target 值。但是，较旧的类文件仍可由 javac 读取。</li>
 *     <li>【update】将类文件版本更新为 53.0。</li>
 * </ol>
 * <h2>New Features</h2>
 * <ol>
 *     <li>【new】序列化过滤器配置。
 *     序列化筛选引入了一种新机制，该机制允许筛选对象序列化数据的传入流，以提高安全性和可靠性。
 *     每个 ObjectInputStream 都会在反序列化期间将筛选器（如果已配置）应用于流内容。</li>
 *     <li>【update】紧凑、小巧的Strings。
 *     java.lang.String、StringBuilder 和 StringBuffer 类由原有的 char[] 变更为 byte[]，减少了底层存储的开销。
 *     因此 JDK 9 中引入了一个新的 jvm 选项 <code>-XX：-CompactStrings</code> 来禁用此功能，以回到 java 8 的支持。</li>
 *     <li>【new】引入弃用警告。{@link Java9#introduceDeprecationWarning()}</li>
 *     <li>【new】Unicode 8 支持。 自支持 Unicode 6.2.0 的 JDK 8 发布以来，Unicode 8.0 引入了以下新功能，现在这些功能现在包含在 JDK 9 中：
 *     <ul>
 *         <li>10555 new characters</li>
 *         <li>42 new blocks</li>
 *         <li> 29 scripts</li>
 *     </ul></li>
 *     <li>【new】可以限制临时缓冲区使用的内存大小。
 *     JDK 9 中引入了系统属性 jdk.nio.maxCachedBufferSize 以限制“temporary buffer cache”使用的内存。</li>
 *     <li>【new】允许在 Microsoft Windows 上使用 SIO_LOOPBACK_FAST_PATH。
 *     默认情况下，它处于禁用状态，但可以通过在命令行上使用 -Djdk.net.useFastTcpLoopback 或 -Djdk.net.useFastTcpLoopback=true 设置系统属性来启用它。</li>
 *     <li>【new】允许在 Microsoft Windows 上使用 TransmitFile。
 *     系统属性”jdk.nio.enableFastFileTransfer“控制 JDK 是否在 Microsoft Windows 上使用 TransmitFile。
 *     默认情况下，它处于禁用状态，但可以通过在命令行上使用 -Djdk.nio.enableFastFileTransfer 或 -Djdk.nio.enableFastFileTransfer=true 设置系统属性来启用它。</li>
 *     <li>【new】IBM1166字符集现已推出。
 *     它为哈萨克斯坦的西里尔文多语言和欧元提供支持。
 *     此新字符集的别名包括 cp1166、ibm1166、ibm-1166、1166。</li>
 *     <li>【update】RMI 更好的约束检查</li>
 *     <li>【update】基于 UTF-8 编码的Properties文件。
 *     ResourceBundle 现在支持 UTF-8 编码的属性文件，并在需要时自动回退到 ISO-8859-1 编码。</li>
 *     <li>【new】提供新的工具访问类支持SourceVersion.RELEASE_9</li>
 *     <li></li>
 *     <li>【new】使用deprecated的 javadoc 标签而不使用@Deprecated注解，编译器将发出警告。
 *     如果在元素上使用 javadoc deprecated 标记，而没有使用 @Deprecated 注解弃用该标记，那么默认情况下，编译器将为此生成新的警告。
 *     可以通过命令行选项 -Xlint:-dep-ann 或使用 @SuppressWarnings("dep-ann") 注解来抑制新警告。</li>
 *     <li>【new】JShell：Java交互式编程工具</li>
 * </ol>
 * <h2>Removed APIs, Features, and Options</h2>
 * <ol>
 *    <li>【remove】从公共 API 中删除对 java.awt.peer 和 java.awt.dnd.peer 包的引用</li>
 *    <li>【remove】删除 com.sun.image.codec.jpeg 包</li>
 *    <li>【update】删除 JFrame.EXIT_ON_CLOSE，所以 WindowConstants.EXIT_ON_CLOSE 取而代之</li>
 * </ol>
 * <h2>Deprecated APIs, Features, and Options</h2>
 * <ol>
 *    <li>【remove】AppletViewer 已弃用</li>
 *    <li>【remove】弃用 sun.misc.Unsafe.defineClass</li>
 *    <li>【remove】弃用盒装基元构造函数</li>
 *    <li>【remove】弃用 Object.finalize。
 *     java.lang.Object.finalize 方法已被弃用。终结机制本身就存在问题，并可能导致性能问题、死锁和挂起。
 *     java.lang.ref.Cleaner 和 java.lang.ref.PhantomReference 提供更灵活、更高效的方法，以便在对象变得不可访问时释放资源。</li>
 * </ol>
 * <h2>Others</h2>
 * <ol>
 *     <li>【update】@Deprecated注解新增属性 {@link Java9#improveDeprecatedAnnotation()}</li>
 *     <li>【new】接口增加私有方法 {@link Java9#addPrivateMethodInInterface()}</li>
 *     <li>【update】钻石操作符使用升级（Diamond operator） {@link Java9#upgradeDiamondOperator()}</li>
 *     <li>【update】try-with-resource语句改进 {@link Java9#improveTryWithResourcesStatement()}</li>
 *     <li>【new】集合新增静态工厂方法（of()方法） {@link Java9#addOfMethod()}</li>
 *     <li>【update】Stream API增强 {@link Java9#enhanceStreamApi()}</li>
 *     <li>【update】Optional API {@link Java9#enhanceOptionalApi()}</li>
 *     <li>【update】Process API {@link Java9#enhanceProcessApi()}</li>
 *     <li>【update】CompletableFuture API {@link Java9#enhanceCompletableFutureApi()}</li>
 *     <li>【update】允许在私有实例方法上使用 @SafeVarargs 注解。{@link Java9#allowSafeVarargsAnnotationInPrivateInstanceMethods()}</li>
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
     * 接口可定义私有方法了
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
     * 增强 Stream API
     * <p>
     * Java 9 为 Stream 流新增了几个方法：dropWhile、takeWhile、ofNullable，为 iterate 方法新增了一个重载方法。
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
        System.out.println("deprecated");
    }

    /**
     * 引入弃用警告。
     * <p>
     * 使用了@Deprecated注解的代码，会在 javac 编译过程中发出各种警告。
     * 但是，立即迁移代码并不总是可行的。因此，提供了两种机制来控制发出的 javac 警告：命令行选项和源代码中的注释。
     * javac 命令行选项 -Xlint:deprecation 或 -Xlint:removal 将启用相应的警告类型；反之，-Xlint:-deprecation 或 -Xlint:-removal 禁用相应的警告类型。
     * 另一种机制是将 @SuppressWarnings("deprecation") or @SuppressWarnings("removal") 注释添加到源代码中，以禁止该声明中发出的相应警告类型。
     */
    @SuppressWarnings("deprecation")
    public void introduceDeprecationWarning() {
        this.improveDeprecatedAnnotation();
    }

    /**
     * 10.Optional中新增了几个方法：or、ifPresentOrElse、stream
     */
    public void enhanceOptionalApi() {
        Optional.ofNullable(null)
                .or(Optional::empty)
                .ifPresentOrElse(System.out::println, () -> System.out.println("空对象"));
    }

    /**
     * 11.Process中新增了几个方法：supportsNormalTermination、pid、onExit、toHandle、info、children、descendants
     */
    public void enhanceProcessApi() throws IOException {
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
    public void enhanceCompletableFutureApi() {
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

    /**
     * 允许在私有实例方法上使用 @SafeVarargs 注解
     * <p>
     * 原本 @SafeVarargs 注解只能应用于无法重写的方法，这些方法包括静态方法、最终实例方法。现在，Java SE 9 中 @SafeVarargs 可用于私有实例方法。
     */
    public void allowSafeVarargsAnnotationInPrivateInstanceMethods() {
        this.m(10, "aaa", true, 6.5);
    }

    @SafeVarargs
    private <T> void m(T... args) {
        System.out.println(Arrays.toString(args));
    }
}
