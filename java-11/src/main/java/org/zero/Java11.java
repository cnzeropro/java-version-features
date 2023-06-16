package org.zero;

import lombok.NonNull;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * java 11 新特性
 * <p>
 * 1.【update】可在 Lambda 中使用 var
 * <p>
 * 2.【update】新增一系列字符串处理方法
 * <p>
 * 3.【update】java文件直接运行。11以前，需要先编译生成class文件之后再运行，现在可以通过 java xxx.java 命令直接运行
 * <p>
 * 4.【new】HTTP Client 模块
 *
 * @author Zero
 */
public class Java11 {

    /**
     * 1.在Lambda表达式的参数中可以使用var修饰修饰了
     */
    public void lambdaWithVar() {
        List<String> result = Stream.of("jac", "jlb", "bdg")
                // 不声明 var 就没有办法为输入参数添加想要的注解
                .filter((@NonNull var s) -> s.startsWith("j"))
                .filter(Predicate.not((@NonNull var s) -> s.contains("b")))
                .collect(Collectors.toList());
        System.out.println(result);
    }

    /**
     * 2.新增字符串类的方法
     */
    public void stringApi() {
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
     * 4.新增HTTP Client模块
     * <p>
     * 用于发送HTTP请求，在此之前，如果不用三方包的话，只能使用HttpURLConnection
     */
    public void httpClientModule() throws Exception {
        // java11以前
        HttpURLConnection httpURLConnection = (HttpURLConnection) URI.create("https://www.baidu.com/").toURL().openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), StandardCharsets.UTF_8));
        String content = reader.lines().collect(Collectors.joining("\n"));
        System.out.println(content);

        // java11写法
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.baidu.com/"))
                .build();
        HttpClient.newHttpClient()
                .sendAsync(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8))
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join();
    }
}
