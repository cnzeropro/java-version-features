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
 * <a href="https://openjdk.org/projects/jdk/11/">JDK 11</a>
 * <a href="https://docs.oracle.com/javase/11/">JDK 11 Documentation</a>
 * <h1>Features</h1>
 * <ol>
 *     <li><a href="https://openjdk.org/jeps/323">323</a>：局部变量类型推断（Local-Variable Syntax for Lambda Parameters）{@link #enhanceVarIdentifier()}</li>
 *     <li>新增 String 类方法 {@link #addMethodsForString()}</li>
 *     <li><a href="https://openjdk.org/jeps/321">321</a>：新增 HTTP Client API（HTTP Client）{@link #addHttpClientApi()}</li>
 *     <li>重载 toArray 方法（New Collection.toArray(IntFunction) Default Method）{@link #overloadToArrayMethod()}</li>
 *     <li><a href="https://openjdk.org/jeps/333">333</a>：新增 ZGC（ZGC: A Scalable Low-Latency Garbage Collector）[实验性]{@link #zgc()}</li>
 *     <li><a href="https://openjdk.org/jeps/335">335</a>：弃用 Nashorn JavaScript 引擎（Deprecate the Nashorn JavaScript Engine）{@link #deprecateNashornEngine()}</li>
 * </ol>
 *
 * @author Zero
 * @since 2019/01/24
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
        // java 11 以前发送 http 请求
        HttpURLConnection httpURLConnection = (HttpURLConnection) URI.create("https://www.baidu.com/").toURL().openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), StandardCharsets.UTF_8));
        String content = reader.lines().collect(Collectors.joining("\n"));
        System.out.println(content);

        // java 11 发送 http 请求
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://www.baidu.com/"))
                .timeout(Duration.ofSeconds(5L))
                .version(HttpClient.Version.HTTP_2)
                .build();
        String body = HttpClient.newHttpClient()
                .send(httpRequest, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8))
                .body();
        System.out.println(body);
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

    /**
     * 新增 ZGC
     * <p>
     * Z 垃圾回收器，也称为 ZGC，是一种可扩展的低延迟垃圾回收器。它旨在实现以下目标：
     * <ul>
     *     <li>暂停时间不超过10毫秒</li>
     *     <li>暂停时间不会随着堆或 live-set 大小的增加而增加</li>
     *     <li>处理大小从几百兆字节到数万亿字节不等的堆</li>
     * </ul>
     * ZGC 作为实验性功能包含在内。因此，要启用它，需要将 {@code -XX:+UnlockExperimentalVMOptions} 选项与 {@code -XX:+UseZGC} 选项结合使用。
     */
    public void zgc() {

    }

    /**
     * 弃用 Nashorn JavaScript 引擎
     * <p>
     * Nashorn JavaScript 引擎实现、API 和 jjs shell 工具已被弃用，可能会在将来的版本中删除。
     * 使用 link jdk.nashorn.api.tree 和 jdk.nashorn.api.scripting 包中的类和接口的代码将收到来自 javac 的弃用警告。
     */
    public void deprecateNashornEngine() {

    }
}
