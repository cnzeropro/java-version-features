package org.zero;

import lombok.NonNull;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <a href="https://docs.oracle.com/javase/11">JDK 11 Documentation</a>
 * <h2>Language Features</h2>
 * <ol>
 *     <li>【update】局部变量类型推断（Local Variable Type Inference）{@link Java11#enhanceVarIdentifier()}</li>
 * </ol>
 *
 * <h2>Previews and Incubator</h2>
 * <ol>
 *     <li>【new】新增 ZGC（ZGC A Scalable Low-Latency Garbage Collector）（实验性）。
 *     Z 垃圾回收器，也称为 ZGC，是一种可扩展的低延迟垃圾回收器。它旨在实现以下目标：
 *     <ul>
 *         <li>暂停时间不超过10毫秒</li>
 *         <li>暂停时间不会随着堆或 live-set 大小的增加而增加</li>
 *         <li>处理大小从几百兆字节到数万亿字节不等的堆</li>
 *     </ul>
 *     ZGC 作为实验性功能包含在内。因此，要启用它，需要将该 -XX:+UnlockExperimentalVMOptions 选项与 -XX:+UseZGC 该选项结合使用。
 *     </li>
 * </ol>
 *
 * <h2>Libraries Improvements</h2>
 * <ol>
 *     <li>【update】新增 String 类方法 {@link Java11#addMethodsForString()}</li>
 *     <li>【new】新增 HTTP Client API（HTTP Client）{@link Java11#addHttpClientApi()}</li>
 *     <li>【update】重载 toArray 方法（New Collection.toArray(IntFunction) Default Method）{@link Java11#overloadToArrayMethod()}</li>
 * </ol>
 *
 * <h2>Changes</h2>
 * <ol>
 *     <li>【update】java 文件直接运行。
 *     11以前，需要先编译生成 class 文件之后再运行；如今可以通过 java xxx.java 命令直接运行。</li>
 *     <li>支持 Unicode 标准版本 10.0（Unicode 10）</li>
 *     <li>【remove】不再提供 JRE 或 Server JRE，仅提供 JDK。
 *     用户可用于 jlink 创建较小的自定义运行时。</li>
 *     <li>【update】支持 Unicode 标准版本 10.0（Unicode 10）</li>
 *     <li>【new】延迟分配编译器线程（Lazy Allocation of Compiler Threads）。
 *     添加了新的命令行标志{@code -XX:+UseDynamicNumberOfCompilerThreads}（默认开启），用于动态控制编译器线程。
 *     在分层编译模式下（默认处于打开状态），VM 会在具有多核 CPU 的系统上启动大量编译器线程，而不考虑可用内存和编译请求数。
 *     由于线程即使在空闲时（几乎一直处于空闲状态）也会消耗内存，这会导致资源使用效率低下。
 *     为了解决此问题，更改为在启动期间每种类型的编译器线程仅启动一个，并动态处理其他线程的启动和关闭。
 *     </li>
 *     <li>【new】新增 Epsilon GC（Epsilon, A No-Op Garbage Collector）。
 *     Epsilon GC 是新的实验性无操作垃圾收集器。Epsilon GC 仅处理内存分配，不实现任何内存回收机制。
 *     </li>
 *     <li>【remove】删除{@link Thread#destroy}和{@link Thread#stop(Throwable)}方法（Removal of Thread.destroy() and Thread.stop(Throwable) Methods）</li>
 *     <li>【remove】从 Oracle JDK 中删除 JavaFX（Removal of JavaFX from the Oracle JDK）</li>
 *     <li>【remove】弃用 Nashorn JavaScript 引擎（Deprecate the Nashorn JavaScript Engine）。
 *     Nashorn JavaScript 引擎实现、API 和 jjs shell 工具已被弃用，可能会在将来的版本中删除。
 *     使用 link jdk.nashorn.api.tree 和 jdk.nashorn.api.scripting 包中的类和接口的代码将收到来自 javac 的弃用警告。
 *     </li>
 * </ol>
 *
 * @author Zero
 */
public class Java11 {

    /**
     * 增强 var 关键字（Local-Variable Syntax for Lambda Parameters）
     * <p>
     * Java 10 引入了 var 关键字，使得局部变量可以自动类型推断；现在 Java 11 支持在声明隐式类型的 lambda 表达式的形参时使用 var。
     */
    public void enhanceVarIdentifier() {
        List<String> result = Stream.of("jac", "jlb", "bdg")
                .filter((@NonNull var s) -> s.startsWith("j"))
                .filter(Predicate.not((@NonNull var s) -> s.contains("b")))
                .collect(Collectors.toList());
        System.out.println(result);
    }

    /**
     * 新增 String 类方法
     * <p>
     * 包括：isBlank、strip、stripLeading、stripTrailing、repeat、lines
     */
    public void addMethodsForString() {
        String str = "  i love java!   ";

        System.out.println("原始字串：" + str);
        System.out.println("是否空白字串：" + str.isBlank());
        System.out.println("去除开头和结尾空白：" + str.strip());
        System.out.println("去除首部空白：" + str.stripLeading());
        System.out.println("去除尾部空白：" + str.stripTrailing());
        System.out.println("复制两遍后的字符串：" + str.repeat(2));
        System.out.println("行数：" + str.lines().count());
    }

    /**
     * 新增 HTTP Client API
     * <p>
     * 用于发送 HTTP 请求，在此之前，如果不用三方包的话，只能使用 HttpURLConnection
     */
    @SneakyThrows
    public void addHttpClientApi() {
        // java11以前发送http请求
        HttpURLConnection httpURLConnection = (HttpURLConnection) URI.create("https://www.baidu.com/").toURL().openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), StandardCharsets.UTF_8));
        String content = reader.lines().collect(Collectors.joining("\n"));
        System.out.println(content);

        // java11发送http请求
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://www.baidu.com/"))
                .timeout(Duration.ofSeconds(5L))
                .version(HttpClient.Version.HTTP_2)
                .build();
        HttpClient.newHttpClient()
                .sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8))
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join();
    }

    /**
     * 重载 toArray 方法
     * <p>
     * Java 11 新增了{@link java.util.Collection#toArray(java.util.function.IntFunction<T[]>)}
     */
    public void overloadToArrayMethod() {
        var list = List.of("a", "b", "c");
        // 以前
        Object[] a0 = list.toArray();
        String[] a1 = list.toArray(new String[0]);
        System.out.println(Arrays.toString(a0));
        System.out.println(Arrays.toString(a1));

        // Java 11 重载
        String[] a2 = list.toArray(String[]::new);
        System.out.println(Arrays.toString(a2));
    }
}
