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
 * <a href="https://www.oracle.com/java/technologies/javase/9-all-relnotes.html#Important">what's new in java 9</a>
 * <p>
 * 【update】新的 JDK 版本控制方案。{@link Java9#updateVersioningScheme()}
 * <p>
 * 【new】模块系统（Java Platform Module System，JPMS）
 * <p>
 * 【update】将类文件版本更新为 53.0。
 * <p>
 * 【update】将默认 GC 更改为 G1。
 * 在 JDK 9 中，当未显式指定垃圾回收器时，默认垃圾回收器为 G1。
 * 与面向吞吐量的收集器（如Parallel GC）相比，G1 为大多数用户提供了更好的整体体验
 * <p>
 * 【new】G1 在年轻代收集器工作期间无法触及 Humongous Objects。
 * G1 现在尝试收集原始类型的大量对象（char、integer、long、double），而在任何年轻收集器中，其他对象的引用很少或根本没有引用。
 * 在年轻代收集期间，G1 会检查对这些巨大对象的任何剩余传入引用是否是最新的。G1 将回收任何没有剩余传入引用的巨大对象。
 * 此更改添加了三个新的实验性 JVM 选项，用于控制此行为：
 * <ul>
 * <li>G1EagerReclaimHumongousObjects - 此选项控制 G1 是否尝试在每个年轻 GC 上回收无法访问的巨型对象。默认值为启用。</li>
 * <li>G1EagerReclaimHumongousObjectsWithStaleRefs - 启用此选项后，G1 会尝试回收每个年轻 GC 中可能有一些过时传入引用的巨型对象。默认值为启用。</li>
 * <li>G1TraceEagerReclaimHumongousObjects - 此选项允许在每个年轻 GC 上打印有关巨大对象集合的一些信息。默认值为禁用。</li>
 * </ul>
 * <p>
 * 【new】os::set_native_thread_name() 清理
 * <p>
 * 【update】紧凑、小巧的Strings。
 * java.lang.String、StringBuilder 和 StringBuffer 类由原有的 char[] 变更为 byte[]，减少了底层存储的开销。
 * 因此 JDK 9 中引入了一个新的 jvm 选项 <code>-XX：-CompactStrings</code> 来禁用此功能，以回到 java 8 的支持
 * <p>
 * 【update】@Deprecated注解新增属性 {@link Java9#deprecatedAnnotation()}
 * <p>
 * 【new】引入弃用警告。{@link Java9#introduceDeprecationWarning()}
 * <p>
 * 【new】使用deprecated的 javadoc 标签而不使用@Deprecated注解，编译器将发出警告
 * 如果在元素上使用 javadoc deprecated 标记，而没有使用 @Deprecated 注解弃用该标记，那么默认情况下，编译器将为此生成新的警告。
 * 可以通过命令行选项 -Xlint:-dep-ann 或使用 @SuppressWarnings("dep-ann") 注解来抑制新警告.
 * <p>
 * 【new】Unicode 8 支持。
 * 自支持 Unicode 6.2.0 的 JDK 8 发布以来，Unicode 8.0 引入了以下新功能，现在这些功能现在包含在 JDK 9 中：
 * <ul>
 * <li>10555 new characters.</li>
 * <li>42 new blocks.</li>
 * <li>29 scripts.</li>
 * </ul>
 * <p>
 * 【new】可以限制临时缓冲区使用的内存大小。
 * JDK 9 中引入了系统属性 jdk.nio.maxCachedBufferSize 以限制“temporary buffer cache”使用的内存。
 * <p>
 * 【new】允许在 Microsoft Windows 上使用 SIO_LOOPBACK_FAST_PATH。
 * 默认情况下，它处于禁用状态，但可以通过在命令行上使用 -Djdk.net.useFastTcpLoopback 或 -Djdk.net.useFastTcpLoopback=true 设置系统属性来启用它。
 * <p>
 * 【new】允许在 Microsoft Windows 上使用 TransmitFile。
 * 系统属性”jdk.nio.enableFastFileTransfer“控制 JDK 是否在 Microsoft Windows 上使用 TransmitFile。默认情况下，它处于禁用状态，但可以通过在命令行上使用 -Djdk.nio.enableFastFileTransfer 或 -Djdk.nio.enableFastFileTransfer=true 设置系统属性来启用它。
 * <p>
 * 【new】IBM1166字符集现已推出。
 * 它为哈萨克斯坦的西里尔文多语言和欧元提供支持。
 * 此新字符集的别名包括“cp1166”、“ibm1166”、“ibm-1166”、“1166”。
 * <p>
 * 【update】基于 UTF-8 编码的Properties文件。
 * ResourceBundle 现在支持 UTF-8 编码的属性文件，并在需要时自动回退到 ISO-8859-1 编码。
 * <p>
 * 【update】RMI 更好的约束检查
 * <p>
 * 【new】远程 JMX 连接器的新系统属性
 * 新的 JMX 代理属性 - jmxremote.host
 * <p>
 * 【new】JMX RMI JRMP 服务器的新属性指定反序列化服务器凭据时要使用的类名列表
 * 属性名称为：jmx.remote.rmi.server.credential.types，用以允许 JMX RMI JRMP 服务器指定类名列表。
 * 缺省情况下，此属性仅由具有 { "[Ljava.lang.String;", "java.lang.String" }的缺省代理使用，因此在反序列化凭证时，将只接受 Strings 和 Strings 数组。
 * <p>
 * 【new】添加新的 ManagementAgent.status 诊断命令。
 * 引入了新的 ManagementAgent.status 诊断命令，用于查询 JMX 代理的状态。
 * <p>
 * 【new】接口增加私有方法 {@link Java9#privateMethod()}
 * <p>
 * 【update】钻石操作符使用升级（Diamond operator） {@link Java9#diamondOperator()}
 * <p>
 * 【update】XPath 增强
 * <p>
 * 【new】XML 目录 API
 * <p>
 * 【update】try-with-resource语句改进 {@link Java9#tryBlock()}
 * <p>
 * 【new】集合新增静态工厂方法（of()方法） {@link Java9#ofMethod()}
 * <p>
 * 【update】Stream API增强 {@link Java9#streamApi()}
 * <p>
 * 【new】JShell：Java交互式编程工具
 * <p>
 * 【update】Optional API {@link Java9#optionalApi()}
 * <p>
 * 【update】Process API {@link Java9#processApi()}
 * <p>
 * 【update】CompletableFuture API {@link Java9#completableFutureApi()}
 * <p>
 * 【update】默认使用 CLDR 语言环境数据。
 * 在 JDK 9 中，默认区域设置数据使用派生自 Unicode 联盟的通用区域设置数据存储库 （CLDR） 的数据。
 * 例如，CLDR 不为大多数 3 个字母的时区 ID 提供本地化的显示名称，因此显示名称可能与 JDK 8 及更早版本不同。
 * JDK 继续提供旧版 JRE 语言环境数据，并且 system 属性 java.locale.providers 可用于配置查找顺序。
 * 要启用与 JDK 8 兼容的行为，可以使用以下命令设置系统属性：
 * -Djava.locale.providers=COMPAT,SPI
 * <p>
 * 【new】模块化运行时映像
 * <p>
 * 【remove】删除对 1.5 及更早版本的源和目标选项的支持。
 * javac 命令不再支持 6/1.6 之前版本的 -source 或 -target 值。但是，较旧的类文件仍可由 javac 读取。
 * <p>
 * 【new】序列化过滤器配置。
 * 序列化筛选引入了一种新机制，该机制允许筛选对象序列化数据的传入流，以提高安全性和可靠性。
 * 每个 ObjectInputStream 都会在反序列化期间将筛选器（如果已配置）应用于流内容。
 * <p>
 * 【new】在 JNLP 文件中设置 32 或 64 位 JRE 要求
 * <p>
 * 【new】Java Web Start 中的 32/64 位互操作性
 * <p>
 * 【new】新的 JVM 选项：ExitOnOutOfMemoryError 和 CrashOnOutOfMemoryError。
 * 添加了两个新的 JVM 标志：
 * ExitOnOutOfMemoryError 启用此选项时，JVM 将在第一次出现内存不足错误时退出。
 * CrashOnOutOfMemoryError 如果启用此选项，那么当发生内存不足错误时，JVM 将崩溃并生成文本和二进制崩溃文件（如果启用了核心文件）。
 * <p>
 * 【new】添加不对签名字节进行 ASN.1 编码的 DSA 签名算法变体。
 * 对于 DSA：NONEwithDSAinP1363Format SHA1withDSAinP1363Format SHA224withDSAinP1363Format SHA256withDSAinP1363Format
 * 对于 ECDSA: NONEwithECDSAinP1363Format SHA1withECDSAinP1363Format SHA224withECDSAinP1363Format SHA256withECDSAinP1363Format SHA384withECDSAinP1363Format SHA512withECDSAinP1363Format
 * <p>
 * 【update】允许非默认根 CA 不受算法限制。
 * <p>
 * 【new】支持高达 8192 位的 DHE 大小和高达 3072 位的 DSA 大小。
 * <p>
 * 【new】支持通过系统属性自定义默认启用的密码套件。
 * 系统属性 jdk.tls.client.cipherSuites 可用于为 SSL/TLS 连接的客户端自定义默认启用的密码套件。
 * <p>
 * 【update】在 SunJSSE 提供程序中支持 SHA224withDSA 和 SHA256withDSA
 * <p>
 * 【new】TLS 应用层协议协商扩展。
 * <p>
 * 【new】通过 SASL 访问 ExtendedGSSContext.inquireSecContext() 结果。
 * <p>
 * 【new】添加安全属性以配置 XML 签名安全验证模式
 * <p>
 * 【new】用于 jar 签名的新 API。
 * jdk.jartool 模块中添加了一个新的 jdk.security.jarsigner.JarSigner API，可用于对 jar 文件进行签名。
 * <p>
 * 【update】krb5.conf 接受设置为：yes/no
 * 除了 “true” 和 “false” 之外，krb5.conf 现在还接受设置为 “yes” 和 “no”。
 * <p>
 * 【new】krb5.conf 中支持 “include” 和 “includedir”设置
 * krb5.conf 文件现在支持使用“include FILENAME”或“includedir DIRNAME”指令包含其他文件。FILENAME 或 DIRNAME 必须是绝对路径。命名文件或目录必须存在且可读。
 * <p>
 * 【new】支持 java 命令行工具的 @-files
 * <p>
 * 【new】添加新的启动器环境变量：JDK_JAVA_OPTIONS
 * 与旧版/不支持 _JAVA_OPTIONS 的环境变量相比，新环境变量具有几个优点，包括能够包含 java 启动器选项和支持 @file。
 * 从 JDK 8 迁移到 JDK 9 时，对于需要新的命令行选项（JDK 8 不支持）的情况，新的环境变量也可能很有用。
 * <p>
 * 【new】添加新属性：maxXMLNameLimit
 * 添加了新属性”maxXMLNameLimit“以限制 XML 名称的最大大小，包括元素名称、属性名称和命名空间前缀和 URI。
 * <p>
 * 【remove】从公共 API 中删除对 java.awt.peer 和 java.awt.dnd.peer 包的引用
 * <p>
 * 【remove】删除 com.sun.image.codec.jpeg 包
 * <p>
 * 【remove】删除JFrame.EXIT_ON_CLOSE，取而代之的是WindowConstants.EXIT_ON_CLOSE
 * <p>
 * 【remove】从默认的 java.policy 中删除 stopThread RuntimePermission。
 * <p>
 * 【remove】不再支持“sun.lang.ClassLoader.allowArraySyntax”系统属性
 * <p>
 * 【remove】删除 netdoc URL 协议处理程序
 * <p>
 * 【remove】删除 lib/content-types.properties 文件
 * <p>
 * 【remove】删除 JDK-Internal 名称服务提供程序接口和默认实现
 * <p>
 * 【remove】删除 sun.nio.sh.PollSelectorProvider
 * <p>
 * 【remove】从 RMI 中删除 HTTP 代理
 * <p>
 * 【remove】删除 java.util.jar.Pack200.Packer/Unpacker 中 addPropertyChangeListener 和 removePropertyListener 方法
 * <p>
 * 【remove】删除系统属性：sun.zip.disableMemoryMapping
 * <p>
 * 【remove】删除 java.util.logging.LogManager 中 addPropertyChangeListener 和 removePropertyChangeListener 方法
 * <p>
 * 【remove】创建 JNDI InitialContext 时会忽略 Context.APPLET
 * <p>
 * 【remove】CodeStore 服务已被移除。
 * 删除了通过 java.util.ServiceLoader API 提供 jdk.nashorn.internal.runtime.CodeStore 的 provide 子类的可能性。
 * <p>
 * 【remove】删除很少使用的 sun.misc.Unsafe 方法
 * <p>
 * 【remove】删除 com.sun.tracing API
 * <p>
 * 【remove】SA-JDI 已被移除
 * <p>
 * 【remove】删除对来自 JMX RMI 连接器的 IIOP 传输的支持。
 * <p>
 * 【remove】删除 native2ascii 工具
 * <p>
 * 【remove】删除 management-agent.jar
 * <p>
 * 【remove】删除 jhat 工具
 * <p>
 * 【remove】删除 serialver -show 选项
 * <p>
 * 【remove】删除 extcheck 工具
 * <p>
 * 【remove】禁用 rmic -Xnew 选项
 * <p>
 * 【remove】删除对 applets（小程序）的序列化支持
 * <p>
 * 【remove】删除 SafepointPollOffset、BackEdgeThreshold、EnableInvokeDynamic、Use486InstrsOnly 标志
 * -XX:SafepointPollOffset 标志已被删除，因为它只是为了重现 C1 编译器的问题而引入的，不再需要。
 * -XX:BackEdgeThreshold 标志已被删除。用户需要改用 -XX:OnStackReplacePercentage。
 * -XX:EnableInvokeDynamic 标志已被删除，因为 VM 不再支持在没有 invokedynamic 的情况下执行。
 * -XX:+Use486InstrsOnly 标志已被删除。
 * <p>
 * 【remove】删除每个编译器的性能计数器。
 * 因为它们在存在更细粒度和更精确的编译事件时已过时。
 * sun.management.* 相应接口已被弃用，因为它将不再提供没有性能计数器的信息。
 * 用户可以通过全局性能计数器、事件跟踪 API （JFR） 或 -XX:+PrintCompilation。
 * <p>
 * 【remove】删除已弃用的命令行标志
 * 自 JDK 6 以来已弃用或别名化的以下内部命令行标志已被删除：
 * CMSParPromoteBlocksToClaim, ParCMSPromoteBlocksToClaim, ParallelGCOldGenAllocBufferSize, ParallelGCToSpaceAllocBufferSize, UseGCTimeLimit, CMSPermGenSweepingEnabled, ResizeTLE, PrintTLE, TLESize, UseTLE, MaxTLERatio, TLEFragmentationRatio, TLEThreadRatio
 * 除此之外，这些内部标志已被弃用：
 * CMSMarkStackSizeMax, ParallelMarkingThreads, ParallelCMSThreads, CMSMarkStackSize, G1MarkStackSize
 * <p>
 * 【remove】删除各种 GC 组合
 * JDK 8 中已弃用的 GC 组合现已删除。这意味着以下 GC 组合不再存在：
 * DefNew + CMS
 * ParNew + SerialOld
 * Incremental CMS The "foreground" mode for CMS has also been removed
 * 已删除的命令行标志包括：
 * -Xincgc, -XX:+CMSIncrementalMode, -XX:+UseCMSCompactAtFullCollection, -XX:+CMSFullGCsBeforeCompaction 和 -XX:+UseCMSCollectionPassing。
 * 命令行标志 -XX:+UseParNewGC 不再具有任何作用，已弃用。
 * <p>
 * 【remove】删除VM 选项 AdjustConcurrency 和 PrintJVMWarnings
 * <p>
 * 【remove】删除 Oracle Solaris ISA bin 目录和链接
 * <p>
 * 【remove】从 Linux 和 Solaris 映像中删除 lib/$ARCH 目录
 * <p>
 * 【remove】删除 JavaFX impl_* 方法
 * <p>
 * 【remove】删除 JavaFX 构建器类
 * <p>
 * 【remove】删除 com.apple.concurrent.Dispatch
 * <p>
 * 【remove】AppleScript 脚本引擎已被移除
 *
 * @author Zero
 */
public class Java9 {
    /**
     * 新的 JDK 版本控制方案
     * <p>
     * JDK 9使用了新的版本字符串格式。最显著的更改是从版本字符串的开头删除了“1.”，并使用3个或更多单独的元素来指定主要、次要和安全更新。
     */
    public void updateVersioningScheme() {
        Runtime.Version version = Runtime.version();
        System.out.println(version);
    }

    /**
     * 接口可定义私有方法了
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
     * 钻石操作符（Diamond operator）升级
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
     * try-with-resources语句块改进
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
     * 集合新增静态工厂方法（of方法）
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
     * 增强了Stream API
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
     * Deprecated注解新增since和forRemoval属性
     * 分别表示被注解的元素从那个版本开始过时，以及未来是否移除
     */
    @Deprecated(since = "1.1", forRemoval = true)
    public void deprecatedAnnotation() {
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
        this.deprecatedAnnotation();
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
