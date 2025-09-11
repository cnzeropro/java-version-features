package org.zero;

import jdk.jfr.consumer.RecordedEvent;
import jdk.jfr.consumer.RecordingFile;
import lombok.NonNull;
import lombok.SneakyThrows;

import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.NamedParameterSpec;
import java.time.Duration;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <a href="https://openjdk.org/projects/jdk/11/">JDK 11</a>
 * <a href="https://docs.oracle.com/javase/11/">JDK 11 Documentation</a>
 * <h1>Features</h1>
 * <ol>
 *     <li><a href="https://openjdk.org/jeps/181">181</a>：{@linkplain #nestBasedAccessControl 基于嵌套的访问控制（Nest-Based Access Control）}</li>
 *     <li><a href="https://openjdk.org/jeps/309">309</a>：{@linkplain #dynamicClassFileConstant 动态类文件常量（Dynamic Class-File Constants）}</li>
 *     <li><a href="https://openjdk.org/jeps/315">315</a>：{@linkplain #improveAarch64Intrinsic 改进 Aarch64 内联函数（Improve Aarch64 Intrinsics）}</li>
 *     <li><a href="https://openjdk.org/jeps/322">322</a>：{@linkplain #removeJavaEEAndCORBA 移除 Java EE 和 CORBA 模块（Remove the Java EE and CORBA Modules）}</li>
 *     <li><a href="https://openjdk.org/jeps/321">321</a>：{@linkplain #httpClientApi HTTP 客户端 API（HTTP Client API）}</li>
 *     <li><a href="https://openjdk.org/jeps/323">323</a>：{@linkplain #lambdaParameterLocalVariableSyntax Lambda 参数局部变量语法（Local-Variable Syntax for Lambda Parameters）}</li>
 *     <li><a href="https://openjdk.org/jeps/324">324</a>：{@linkplain #curve25519AndCurve448KeyAgreement 基于 Curve25519 和 Curve448 的密钥协商（Key Agreement with Curve25519 and Curve448）}</li>
 *     <li><a href="https://openjdk.org/jeps/327">327</a>：{@linkplain #unicode10 支持 Unicode 标准 10.0 版本（Unicode 10）}</li>
 *     <li><a href="https://openjdk.org/jeps/328">328</a>：{@linkplain #flightRecorder 飞行记录器（Flight Recorder）}
 *     <li><a href="https://openjdk.org/jeps/329">329</a>：{@linkplain #chaCha20AndPoly1305CryptographicAlgorithm ChaCha20 和 Poly1305 加密算法（ChaCha20 and Poly1305 Cryptographic Algorithms）}</li>
 *     <li><a href="https://openjdk.org/jeps/330">330</a>：{@linkplain #launchSingleFileSourceCodeProgram 启动单文件源代码程序（Launch Single-File Source-Code Programs）}</li>
 *     <li><a href="https://openjdk.org/jeps/331">331</a>：{@linkplain #lowOverheadHeapProfiling 低开销堆分析（Low-Overhead Heap Profiling）}</li>
 *     <li><a href="https://openjdk.org/jeps/332">332</a>：{@linkplain #tls1_3 TLS 1.3（Transport Layer Security (TLS) 1.3）}</li>
 *     <li><a href="https://openjdk.org/jeps/335">335</a>：{@linkplain #deprecateNashornEngine 弃用 Nashorn JavaScript 引擎以计划删除（Deprecate the Nashorn JavaScript Engine for Removal）}</li>
 *     <li><a href="https://openjdk.org/jeps/336">336</a>：{@linkplain #deprecatePack200ToolsAndApi 弃用 Pack200 工具和 API（Deprecate the Pack200 Tools and API）}</li>
 *     <li>{@linkplain #overloadToArrayMethod 重载 toArray 方法（New Collection.toArray(IntFunction) Default Method）}</li>
 *     <li>{@linkplain #addMethodsForString String 类新增方法}</li>
 * </ol>
 * <ol>
 *     <li><a href="https://openjdk.org/jeps/317">317</a>：{@linkplain #epsilonGc Epsilon - 无操作垃圾回收器（Epsilon: A No-Op Garbage Collector）[实验性]}</li>
 *     <li><a href="https://openjdk.org/jeps/333">333</a>：{@linkplain #zgc ZGC - 一种可扩展的低延迟垃圾收集器（ZGC: A Scalable Low-Latency Garbage Collector）[实验性]}</li>
 * </ol>
 *
 * @author Zero
 * @since 2019/01/24
 */
public class Java11 {

    /**
     * 基于嵌套的访问控制
     * <p>
     * 它主要目的是简化 Java 中对嵌套类型（如内部类）私有成员的访问控制，使其更符合开发者的直觉，并消除编译器之前为绕过 JVM 限制而生成的“桥接方法”。
     */
    public void nestBasedAccessControl() {
        new Outer().tryReflection();
    }

    /**
     * 动态类文件常量
     * <p>
     * 其是 JDK 11 中引入的一项旨在增强 Java 类文件格式灵活性的特性。
     * 它通过引入一种新的常量池形式 CONSTANT_Dynamic，将常量的解析过程从编译时推迟到运行时，并委托给用户指定的引导方法（Bootstrap Method） 来完成
     *
     * @see <a href="https://openjdk.org/jeps/303">JEP 303: Intrinsics for the LDC and INVOKEDYNAMIC Instructions</a>
     */
    private void dynamicClassFileConstant() {
    }

    /**
     * 改进 Aarch64 内联函数
     * <p>
     * 改进现有的字符串和数组内联函数，并在 AArch64 处理器上实现新的 {@link java.lang.Math} 正弦、余弦和对数内联函数。
     */
    private void improveAarch64Intrinsic() {
    }

    /**
     * Epsilon - 无操作垃圾回收器
     * <p>
     * Epsilon GC 是一个只负责内存分配，但完全不实施任何垃圾回收机制的垃圾收集器。一旦可用的 Java 堆内存被耗尽，JVM 便会直接关闭并抛出 OutOfMemoryError 异常。
     * 它被设计用于极特殊的场景，在这些场景下，开发者明确知道应用程序的生命周期极短且内存需求绝对可控，或者需要完全消除 GC 带来的任何性能干扰。
     */
    private void epsilonGc() {
    }

    /**
     * 移除 Java EE 和 CORBA 模块
     * <p>
     * 其核心目标是将已过时、废弃且不再维护的 Java EE 和 CORBA 技术从 Java SE 平台和 JDK 中移除。此举旨在简化 JDK，减少其大小，并为未来的开发扫清障碍。
     */
    private void removeJavaEEAndCORBA() {
    }

    /**
     * 新增 HTTP Client API
     * <p>
     * 用于发送 HTTP 请求，在此之前，如果不用三方包的话，只能使用 {@link HttpURLConnection}
     *
     * @see <a href="https://openjdk.org/jeps/110">JEP 110: HTTP/2 Client (Incubator)</a>
     * @see <a href="https://openjdk.org/jeps/517">JEP 517: HTTP/3 for the HTTP Client API</a>
     */
    @SneakyThrows
    public void httpClientApi() {
        /*
         * 旧 API
         */
        URL url = new URL("https://httpbin.org/get");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(10_000);
        connection.setReadTimeout(5_000);
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Java HttpURLConnection)");
        int responseCode = connection.getResponseCode();
        System.out.println("Response Code: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String response = reader.lines().collect(Collectors.joining("\n"));
            System.out.println("Response Body: " + response);
        }
        connection.disconnect();

        /*
         * 新 API
         */
        // 创建 HttpClient（可复用，线程安全）
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(10))
                .build();
        // 创建 HttpRequest
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://httpbin.org/get"))
                .timeout(Duration.ofSeconds(5))
                .header("User-Agent", "Mozilla/5.0 (Java HttpClient)")
                .GET()
                .build();
        // 发送同步请求，使用 BodyHandlers.ofString() 将响应体转换为 String
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        // 输出响应信息
        System.out.println("Status code: " + response.statusCode());
        System.out.println("Response body: " + response.body());
    }

    /**
     * 曲线 25519 和 448 的密钥协商
     * <p>
     * JEP 324 在 Java 密码学体系结构（JCA）中实现了椭圆曲线 Diffie-Hellman (ECDH) 协议的两种现代、安全且高效的椭圆曲线：X25519 和 X448。
     * 它们分别基于 Curve25519 和 Curve448 曲线。
     */
    @SneakyThrows
    public void curve25519AndCurve448KeyAgreement() {
        // Provider[] providers = Security.getProviders();
        // for (Provider provider : providers) {
        //     System.out.println(provider.getName() + ": " + provider);
        //     for (Provider.Service service : provider.getServices()) {
        //         System.out.println("\t" + service.getType() + ": " + service.getAlgorithm());
        //     }
        // }

        NamedParameterSpec parameterSpec = NamedParameterSpec.X25519;
        String name = parameterSpec.getName();
        KeyPairGenerator keyPairGenerator1 = KeyPairGenerator.getInstance(name);
        // keyPairGenerator1.initialize(parameterSpec);
        KeyPair keyPair1 = keyPairGenerator1.generateKeyPair();
        KeyPairGenerator keyPairGenerator2 = KeyPairGenerator.getInstance(name);
        // keyPairGenerator2.initialize(parameterSpec);
        KeyPair keyPair2 = keyPairGenerator2.generateKeyPair();
        // 密钥协商
        KeyAgreement keyAgreement1 = KeyAgreement.getInstance(name);
        // keyAgreement1.init(keyPair1.getPrivate(), parameterSpec);
        keyAgreement1.init(keyPair1.getPrivate());
        keyAgreement1.doPhase(keyPair2.getPublic(), true);
        KeyAgreement keyAgreement2 = KeyAgreement.getInstance(name);
        // keyAgreement2.init(keyPair1.getPrivate(), parameterSpec);
        keyAgreement2.init(keyPair2.getPrivate());
        keyAgreement2.doPhase(keyPair1.getPublic(), true);
        byte[] secret1 = keyAgreement1.generateSecret();
        byte[] secret2 = keyAgreement2.generateSecret();
        // 生成的共享密钥应该相同
        System.out.println(Arrays.toString(secret1));
        System.out.println(Arrays.toString(secret2));
        System.out.println(Arrays.equals(secret1, secret2));
    }

    /**
     * Unicode 10.0
     * <p>
     * 支持 Unicode 10.0 标准，引入了 8518 个新字符、4 个新脚本和 6 个新区块。
     */
    public void unicode10() {
        // 🦕
        System.out.println("New Characters Like: " + "\uD83E\uDD95");
    }

    /**
     * 飞行记录器
     * <p>
     * Flight Recorder 曾经是 Oracle JDK 的商业特性，但在 JDK 11 中成为了开源功能，允许开发者和运维人员在生产环境中持续使用，而无需担心显著的性能损耗。<br>
     * <p>
     * 命令行启用：
     * <pre>{@code
     * // 延迟20秒开始，记录60秒，输出到指定文件，使用"profile"配置集
     * java -XX:StartFlightRecording=delay=20s,duration=60s,filename=recording.jfr,settings=profile,name=App
     * }</pre>
     */
    @SneakyThrows
    public void flightRecorder() {
        CustomEvent event = new CustomEvent();
        event.message = "Flight Recorder Test";
        event.commit();

        // 读取文件
        Path path = Paths.get("recording.jfr");
        for (RecordedEvent recordedEvent : RecordingFile.readAllEvents(path)) {
            System.out.println(recordedEvent.getStartTime() + " : " + recordedEvent.getValue("message"));
        }
    }

    /**
     * ChaCha20 和 Poly1305 加密算法
     * <p>
     * JEP 329 在 Java 密码学体系结构（JCA）中实现了 ChaCha20 流密码和 Poly1305 消息认证码（MAC）算法。
     * 这两种算法经常组合使用，形成 ChaCha20-Poly1305 认证加密（AEAD）方案。
     * 该方案是 TLS 1.3 中强制实现的密码套件之一，旨在提供一种高性能、高安全性的替代方案，尤其适用于那些没有 AES 硬件加速（如 AES-NI）的硬件平台（如某些移动设备或旧系统）。
     */
    @SneakyThrows
    public void chaCha20AndPoly1305CryptographicAlgorithm() {
        String plainText = "This is a confidential and authenticated message!";

        // 1. 生成密钥
        KeyGenerator keyGenerator = KeyGenerator.getInstance("ChaCha20");
        keyGenerator.init(256);
        SecretKey key = keyGenerator.generateKey();

        // 2. 生成随机数 Nonce (必须是12字节)
        SecureRandom rand = new SecureRandom();
        byte[] nonce = new byte[12];
        rand.nextBytes(nonce);

        // 3. 创建 Cipher 实例，指定使用 ChaCha20-Poly1305
        Cipher cipher = Cipher.getInstance("ChaCha20-Poly1305");
        // Poly1305 产生 128 位标签
        IvParameterSpec parameterSpec = new IvParameterSpec(nonce);
        cipher.init(Cipher.ENCRYPT_MODE, key, parameterSpec);

        // 4. （可选）添加关联数据 (AAD) - 被认证但不被加密的数据
        // byte[] aad = "This is AAD".getBytes();
        // cipher.updateAAD(aad);

        // 5. 执行加密和认证
        byte[] cryptedText = cipher.doFinal(plainText.getBytes());
        System.out.println("CipherText + Tag: " + Base64.getEncoder().encodeToString(cryptedText));

        // 6. 解密和验证
        cipher.init(Cipher.DECRYPT_MODE, key, parameterSpec);
        // cipher.updateAAD(aad); // 如果加密时添加了 AAD，解密时也必须提供相同的 AAD
        byte[] decryptedText = cipher.doFinal(cryptedText);
        System.out.println("Decrypted and Authenticated: " + new String(decryptedText));
    }

    /**
     * 启动单文件源代码程序
     * <p>
     * JEP 330 增强了 java 启动器，使其能够直接运行单个 Java 源代码文件，而无需先显式地使用 javac 命令进行编译，然后再运行。<br>
     * 另外，Shebang 脚本支持（Unix/Linux/macOS）：在类 Unix 系统上，甚至可以创建可执行的脚本文件，其第一行以 {@code #!} 开头并指定 Java 解释器。<br>
     * 此外，还存在一些注意事项与限制：
     * <ul>
     *     <li>单文件限制：此功能仅适用于单个源文件。如果程序依赖其他源文件中定义的类（非 JDK 标准库），运行时会报“找不到符号”的错误。即所有相关的类都必须定义在同一个 .java 文件中。</li>
     *     <li>执行顺序：源文件中定义的第一个顶层类的 main 方法会被执行，无论这个类是否是 public，也无论其类名是否与文件名相同。</li>
     * </ul>
     *
     * @see SingleFileSourceCode
     * @see <a href="https://openjdk.org/jeps/458">JEP 458: Launch Multi-File Source-Code Programs</a>
     */
    private void launchSingleFileSourceCodeProgram() {
    }

    /**
     * 低开销堆分析
     * <p>
     * 其提供一种通过 JVMTI 访问的低开销 Java 堆分配采样方法。
     */
    private void lowOverheadHeapProfiling() {
    }

    /**
     * TLS 1.3
     * <p>
     * 实现传输层安全（TLS）协议 <a href="https://tools.ietf.org/html/rfc8446">RFC 8446</a> 1.3 版本。
     */
    private void tls1_3() {
    }

    /**
     * Lambda 参数的局部变量语法
     * <p>
     * Java 10 引入了 var 关键字，使得局部变量可以自动类型推断；现在 Java 11 支持在声明隐式类型的 lambda 表达式的形参时使用 var。
     *
     * @see <a href="https://openjdk.org/jeps/286">JEP 286: Local-Variable Type Inference</a>
     */
    public void lambdaParameterLocalVariableSyntax() {
        List<String> result = Stream.of("jac", "jlb", "bdg")
                .filter((@NonNull var s) -> s.startsWith("j"))
                .filter(Predicate.not((@NonNull var s) -> s.contains("b")))
                .collect(Collectors.toList());
        System.out.println(result);
    }

    /**
     * String 类新增方法
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
     * 重载 toArray 方法
     * <p>
     * Java 11 新增了 {@link Collection#toArray(IntFunction)}
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
     *
     * @see <a href="https://openjdk.org/jeps/304">JEP 304: Garbage Collector Interface</a>
     * @see <a href="https://openjdk.org/jeps/312">JEP 312: Thread-Local Handshakes</a>
     * @see <a href="https://openjdk.org/jeps/351">JEP 351: ZGC: Uncommit Unused Memory (Experimental)</a>
     * @see <a href="https://openjdk.org/jeps/364">JEP 364: ZGC on macOS (Experimental)</a>
     * @see <a href="https://openjdk.org/jeps/365">JEP 365: ZGC on Windows (Experimental)</a>
     * @see <a href="https://openjdk.org/jeps/377">JEP 377: ZGC: A Scalable Low-Latency Garbage Collector (Production)</a>
     */
    private void zgc() {
    }

    /**
     * 弃用 Nashorn JavaScript 引擎
     * <p>
     * Nashorn JavaScript 引擎实现、API 和 jjs shell 工具已被弃用，可能会在将来的版本中删除。
     * 使用 jdk.nashorn.api.tree 和 jdk.nashorn.api.scripting 包中的类和接口的代码将收到来自 javac 的弃用警告。
     *
     * @see <a href="https://openjdk.org/jeps/372">JEP 372: Remove the Nashorn JavaScript Engine</a>
     */
    private void deprecateNashornEngine() {
    }

    /**
     * 弃用 Pack200 工具和 API
     * <p>
     * 弃用 pack200 和 unpack200 工具，以及 java.util.jar 中的 Pack200 API。
     */
    private void deprecatePack200ToolsAndApi() {
    }
}
