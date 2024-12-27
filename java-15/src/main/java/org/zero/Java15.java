package org.zero;

import lombok.SneakyThrows;

import java.io.IOException;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;
import java.util.Optional;
import java.util.TreeMap;

/**
 * <a href="https://openjdk.org/projects/jdk/15/">JDK 15</a>
 * <a href="https://docs.oracle.com/javase/15/">JDK 15 Documentation</a>
 * <h1>Features</h1>
 * <ol>
 *     <li><a href="https://openjdk.org/jeps/339">339</a>：{@linkplain #edDsa Edwards-Curve 数字签名算法（Edwards-Curve Digital Signature Algorithm (EdDSA)）}</li>
 *     <li><a href="https://openjdk.org/jeps/371">371</a>：{@linkplain #hiddenClasses 隐式类（Hidden Classes）}</li>
 *     <li><a href="https://openjdk.org/jeps/372">372</a>：{@linkplain #removeNashorn 移除 Nashorn JavaScript 引擎（Remove the Nashorn JavaScript Engine）}</li>
 *     <li><a href="https://openjdk.org/jeps/373">373</a>：{@linkplain #reimplementDatagramSocketAPI 重新实现旧版 DatagramSocket API（Reimplement the Legacy DatagramSocket API）}</li>
 *     <li><a href="https://openjdk.org/jeps/374">374</a>：{@linkplain #disableBiasedLocking 弃用并禁用偏向锁定（Deprecate and Disable Biased Locking）}</li>
 *     <li><a href="https://openjdk.org/jeps/377">377</a>：{@linkplain #zgc ZGC：可扩展的低延迟垃圾回收器（ZGC: A Scalable Low-Latency Garbage Collector）}</li>
 *     <li><a href="https://openjdk.org/jeps/378">378</a>：{@linkplain #textBlock 文本块（Text Blocks）}</li>
 *     <li><a href="https://openjdk.org/jeps/379">379</a>：{@linkplain #shenandoah Shenandoah：低暂停时间的垃圾回收器（Shenandoah: A Low-Pause-Time Garbage Collector ）}</li>
 *     <li><a href="https://openjdk.org/jeps/381">381</a>：{@linkplain #removeSolarisAndSPARCPort 移除对 Solaris 操作系统和 SPARC 架构的支持（Remove the Solaris and SPARC Ports）}</li>
 *     <li><a href="https://openjdk.org/jeps/385">385</a>：{@linkplain #deprecateRMIActivation 弃用 RMI 激活以移除（Deprecate RMI Activation for Removal）}</li>
 *     <li>{@linkplain #addMethodForCharSequence CharSequence 类新增 isEmpty 方法（Added isEmpty Default Method to CharSequence）}</li>
 *     <li>{@linkplain #overrideTreeMapMethod 重写 TreeMap 方法并改进性能（Specialized Implementations of TreeMap Methods）}</li>
 * </ol>
 * <ol>
 *     <li><a href="https://openjdk.org/jeps/360">360</a>：密封类（Sealed Classes）[首次预览]</li>
 *     <li><a href="https://openjdk.org/jeps/375">375</a>：instanceof 模式匹配（Pattern Matching for instanceof）[第二次预览]</li>
 *     <li><a href="https://openjdk.org/jeps/384">384</a>：记录类（Records）[第二次预览]</li>
 * </ol>
 * <ol>
 *     <li><a href="https://openjdk.org/jeps/383">383</a>：外部内存访问 API（Foreign-Memory Access API）[第二次孵化]</li>
 * </ol>
 *
 * @author Zero (cnzeropro@qq.com)
 * @since 2021/11/17 19:28
 */
public class Java15 {

    /**
     * 文本块
     * <p>
     * Java 15 引入文本块（Text Blocks），这是一种新的字符串字面量形式。
     * 这主要用于解决 xml、json 等语法书写难以排版的问题。
     *
     * @see <a href="https://openjdk.org/jeps/355">JEP 355: Text Blocks (Preview)</a>
     * @see <a href="https://openjdk.org/jeps/378">JEP 368: Text Blocks (Second Preview)</a>
     */
    public void textBlock() {
        // 传统写法
        String str1 = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "   <head>\n" +
                "       <meta charset='utf-8'>\n" +
                "       <title>test</title>\n" +
                "   </head>\n" +
                "   <body>\n" +
                "       <h1>TEST</h1>\n" +
                "   </body>\n" +
                "</html>\n";
        System.out.println("传统多行文本写法：\n" + str1);

        // 文本块写法
        String str2 = """
                <!DOCTYPE html>
                <html>
                    <head>
                        <meta charset="utf-8">
                        <title>test</title>
                    </head>
                    <body>
                        <h1>TEST</h1>
                    </body>
                </html>
                """;
        System.out.println("文本块写法：\n" + str2);
    }

    /**
     * CharSequence 类新增 isEmpty 方法
     */
    public void addMethodForCharSequence() {
        CharSequence charSequence = "";
        System.out.println("CharSequence is empty: " + charSequence.isEmpty());
    }

    /**
     * 隐藏类
     * <p>
     * 这是一种新的类定义形式，旨在支持动态类型语言和框架中的高级特性，如方法替换、代理类创建等。
     * 隐藏类允许在运行时定义类，并且这些类不会被其他代码直接引用或访问，从而提高了灵活性和安全性。<br>
     * 以下是它的一些特点：
     * <ul>
     *     <li>匿名性：不同于传统的 Java 类加载机制，隐藏类没有全局唯一的名字，因此它们不能通过常规的类加载机制被其他代码直接引用。</li>
     *     <li>短生命周期：隐藏类可以具有非常短的生命周期，适用于临时用途，例如为特定调用创建的代理类。</li>
     *     <li>增强的 {@code ClassLoader} API：引入了新的 API 方法 {@code defineHiddenClass()}，允许开发者定义隐藏类并控制其可见性和生命周期。</li>
     *     <li>与现有机制兼容：尽管隐藏类具有特殊的属性，但它们仍然遵循 JVM 的基本规则，确保与现有的类加载和反射机制兼容。</li>
     * </ul>
     */
    @SneakyThrows
    public void hiddenClasses() {
        MethodHandles.Lookup lookup = MethodHandles.lookup();

        // 获取类字节码，此处只做展示，实际不应该这样获取，因为隐式类的字节码通常是由字节码生成库（如 ASM 或 Javassist）动态生成的
        byte[] classBytes = Optional.of(HiddenExample.class)
                .map(Class::getName)
                .map(className -> className.replace('.', '/') + ".class")
                .map(Thread.currentThread().getContextClassLoader()::getResourceAsStream)
                .map(inputStream -> {
                    try {
                        return inputStream.readAllBytes();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .orElseThrow();

        Class<?> hiddenClass = lookup.defineHiddenClass(classBytes, true, MethodHandles.Lookup.ClassOption.NESTMATE)
                .lookupClass();
        System.out.println("类名：" + hiddenClass.getName());
        System.out.println("是否为隐藏类：" + hiddenClass.isHidden());
        System.out.println("调用sum方法：");
        MethodHandle sumMethodHandle = lookup.findStatic(hiddenClass, "sum", MethodType.methodType(long.class, int[].class))
                // 将 MethodHandle 转换为支持 varargs 的形式
                .asVarargsCollector(int[].class);
        long result = (long) sumMethodHandle.invokeExact(1, 3, 5, 6, 2);
        System.out.println(result);
        System.out.println("调用sayHello方法：");
        MethodHandle constructorMethodHandle = lookup.findConstructor(hiddenClass, MethodType.methodType(void.class, String.class));
        Object instance = constructorMethodHandle.invoke("Bob");
        MethodHandle sayHelloMethodHandle = lookup.findVirtual(hiddenClass, "sayHello", MethodType.methodType(void.class));
        sayHelloMethodHandle.bindTo(instance).invoke();
    }

    /**
     * 重写 TreeMap 方法并改进性能
     * <p>
     * TreeMap 类现在提供了 putIfAbsent、computeIfAbsent、computeIfPresent、compute 和 merge 方法的重写实现并为新的实现提供了性能改进。
     */
    public void overrideTreeMapMethod() {
        var treeMap = new TreeMap<String, Object>();
        treeMap.putIfAbsent("a", 12);
        treeMap.putIfAbsent("c", 28);
        treeMap.putIfAbsent("b", 5);
        System.out.println(treeMap);
    }

    /**
     * Edwards-Curve 数字签名算法（EdDSA）
     * <p>
     * Java 15 引入对 Edwards-Curve 数字签名算法（EdDSA）的支持。<br>
     * EdDSA 是一种基于 Edwards 形式的椭圆曲线的数字签名算法，因其高效性和安全性而受到广泛认可。
     */
    @SneakyThrows
    public void edDsa() {
        // 生成 Ed25519 密钥对
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("Ed25519");
        // 对于 Ed25519，这里可以省略，因为它是固定的
        keyGen.initialize(255);
        KeyPair keyPair = keyGen.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        // 创建签名对象并初始化
        Signature signature = Signature.getInstance("Ed25519");

        // 使用私钥签名数据
        signature.initSign(privateKey);
        String data = "Hello, World!";
        System.out.println("Data: " + data);
        byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
        signature.update(dataBytes);
        byte[] signedBytes = signature.sign();
        String base64Signature = Base64.getEncoder().encodeToString(signedBytes);
        System.out.println("Signature: " + base64Signature);

        // 使用公钥验证签名
        signature.initVerify(publicKey);
        signature.update(dataBytes);
        boolean verify = signature.verify(signedBytes);
        System.out.println("Signature verify: " + verify);
    }

    /**
     * 移除 Nashorn JavaScript 引擎
     * <p>
     * 该引擎最初在 Java 8 中作为 Rhino 脚本引擎的替代品引入。
     * 但随着 ECMAScript 语言结构和 API 的快速适应和修改，Nashorn 变得难以维护。
     * 并在 Java 11 中标记为废弃并计划在未来版本中移除。
     * 现在，Nashorn JavaScript 脚本引擎正式已被移除。
     *
     * @see <a href="https://openjdk.org/jeps/335">JEP 335: Deprecate the Nashorn JavaScript Engine for Removal</a>
     */
    private void removeNashorn() {
    }

    /**
     * 重新实现 DatagramSocket API
     * <p>
     * 传统的 DatagramSocket 实现依赖于本地代码（C/C++），这不仅增加了维护成本，还限制了其在不同平台上的表现和可靠性。
     * Java 15 重新实现 Java 中的遗留 DatagramSocket API，以提高性能、可靠性和可维护性。
     */
    @SneakyThrows
    private void reimplementDatagramSocketAPI() {
        int port = 9876;
        int bufferSize = 1024;

        // Server
        try (DatagramSocket socket = new DatagramSocket(port)) {
            while (Thread.currentThread().isAlive()) {
                // 准备接收数据的缓冲区
                DatagramPacket receivePacket = new DatagramPacket(new byte[bufferSize], bufferSize);
                // 接收数据报
                socket.receive(receivePacket);
                String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength(), StandardCharsets.UTF_8);
                System.out.println("Received: " + receivedMessage);

                // 获取发送方信息
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                // 回传消息给客户端
                String message = "Hello, " + receivedMessage;
                byte[] sendBuffer = message.getBytes(StandardCharsets.UTF_8);
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, clientAddress, clientPort);
                socket.send(sendPacket);
                System.out.println("Sent: " + message);
                System.out.println("Echoed message back to client.");
            }
        }

        // Client
        try (DatagramSocket socket = new DatagramSocket()) {
            String message = "Bob";
            // 发送数据
            byte[] sendBuffer = message.getBytes(StandardCharsets.UTF_8);
            InetAddress serverAddress = InetAddress.getLocalHost();
            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, port);
            socket.send(sendPacket);
            System.out.println("Sent: " + message);
            System.out.println("Sent message to server.");

            // 接收数据
            DatagramPacket receivePacket = new DatagramPacket(new byte[bufferSize], bufferSize);
            socket.receive(receivePacket);
            String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength(), StandardCharsets.UTF_8);
            System.out.println("Received: " + receivedMessage);
        }
    }

    /**
     * 弃用并禁用偏向锁定
     * <p>
     * 偏向锁定（Biased Locking）是一种在 Java HotSpot 虚拟机中用于优化锁争用的机制。
     * 它假设大多数对象的锁只会被一个线程持有。在这种情况下，JVM 可以将锁“偏向”给该线程，从而减少同步开销。
     * 但随着 Java 应用程序和硬件架构的发展，偏向锁定的效果逐渐减弱，甚至在某些情况下可能带来负面影响。
     */
    private void disableBiasedLocking() {
    }

    /**
     * ZGC
     * <p>
     * 将 ZGC（Z Garbage Collector）从实验性功能转变为产品功能。
     * ZGC 在 JDK 11 中被引入，通过后续版本测试优化，终于发布为正式版本。<br>
     * 但请注意默认垃圾回收器仍是 G1。
     *
     * @see <a href="https://openjdk.org/jeps/333">JEP 333: ZGC: A Scalable Low-Latency Garbage Collector (Experimental)</a>
     * @see <a href="https://openjdk.org/jeps/351">JEP 351: ZGC: Uncommit Unused Memory (Experimental)</a>
     * @see <a href="https://openjdk.org/jeps/364">JEP 364: ZGC on macOS (Experimental)</a>
     * @see <a href="https://openjdk.org/jeps/365">JEP 365: ZGC on Windows (Experimental)</a>
     */
    private void zgc() {
    }

    /**
     * Shenandoah
     * <p>
     * 将 Shenandoah 垃圾收集器从实验性功能更改为产品功能。
     * Shenandoah 在 JDK 12 中被引入，通过后续版本测试优化，终于发布为正式版本。<br>
     * 但请注意默认垃圾回收器仍是 G1。
     */
    private void shenandoah() {
    }

    /**
     * 删除 Solaris 和 SPARC 的移植
     * <p>
     * 在 JDK 14 中，Solaris 和 SPARC 的移植被标记为废弃并计划在未来版本中删除。
     * 现在，Solaris/SPARC、Solaris/x64 和 Linux/SPARC 支持移植的源代码和构建已被移除。
     *
     * @see <a href="https://openjdk.org/jeps/362">JEP 362: Deprecate the Solaris and SPARC Ports</a>
     */
    private void removeSolarisAndSPARCPort() {
    }

    /**
     * 弃用 RMI 激活以移除
     * <p>
     * RMI 激活是一种允许远程对象自动实例化和去激活的机制，但它在现代应用开发中的使用已经大大减少。
     * 因此，在 JDK 15 中，RMI 激活功能被标记为废弃，并计划在未来版本中被删除。
     */
    private void deprecateRMIActivation() {
    }
}
