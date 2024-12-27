package org.zero;

import lombok.SneakyThrows;

import java.io.ByteArrayOutputStream;
import java.net.UnixDomainSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

/**
 * <a href="https://openjdk.org/projects/jdk/16/">JDK 16</a>
 * <a href="https://docs.oracle.com/javase/16/">JDK 16 Documentation</a>
 * <h1>Features</h1>
 * <ol>
 *     <li><a href="https://openjdk.org/jeps/347">347</a>：{@linkplain #cppLanguageFeature 启用 C++14 语言特性（Enable C++14 Language Features）}</li>
 *     <li><a href="https://openjdk.org/jeps/357">357</a>：{@linkplain #migrateToGit 从 Mercurial 迁移到 Git（Migrate from Mercurial to Git）}</li>
 *     <li><a href="https://openjdk.org/jeps/369">369</a>：{@linkplain #migrateToGithub 迁移到 GitHub（Migrate to GitHub）}</li>
 *     <li><a href="https://openjdk.org/jeps/376">376</a>：{@linkplain #zgc ZGC：并发线程栈处理（ZGC: Concurrent Thread-Stack Processing）}</li>
 *     <li><a href="https://openjdk.org/jeps/380">380</a>：{@linkplain #unixSocketChannel Unix 域套接字通道（Unix-Domain Socket Channels）}</li>
 *     <li><a href="https://openjdk.org/jeps/386">386</a>：{@linkplain #portAlpineLinux Alpine Linux 移植（Alpine Linux Port）}</li>
 *     <li><a href="https://openjdk.org/jeps/387">387</a>：{@linkplain #elasticMetaspace 弹性元空间（Elastic Metaspace）}</li>
 *     <li><a href="https://openjdk.org/jeps/388">388</a>：{@linkplain #portWindowsAArch64 Windows/AArch64 移植（Windows/AArch64 Port）}</li>
 *     <li><a href="https://openjdk.org/jeps/390">390</a>：{@linkplain #warningForValueBasedClass 基于值基类的警告（Warnings for Value-Based Classes）}</li>
 *     <li><a href="https://openjdk.org/jeps/392">392</a>：{@linkplain #packagingTool 打包工具（Packaging Tool）}</li>
 *     <li><a href="https://openjdk.org/jeps/394">394</a>：{@linkplain #instanceofPatternMatching instanceof 模式匹配（Pattern Matching for instanceof）}</li>
 *     <li><a href="https://openjdk.org/jeps/395">395</a>：{@linkplain #recordClass 记录类（Records）}</li>
 *     <li><a href="https://openjdk.org/jeps/396">396</a>：{@linkplain #encapsulateJDKInternal 默认情况下强封装 JDK 内部结构（Strongly Encapsulate JDK Internals by Default）}</li>
 *     <li>{@linkplain #addMethodsForStream stream 类新增方法} </li>
 * </ol>
 * <ol>
 *     <li><a href="https://openjdk.org/jeps/397">397</a>：密封类（Sealed Classes）[第二次预览]</li>
 * </ol>
 * <ol>
 *     <li><a href="https://openjdk.org/jeps/338">338</a>：向量 API（Vector API）[首次孵化]</li>
 *     <li><a href="https://openjdk.org/jeps/389">389</a>：外部链接器 API（Foreign Linker API）[首次孵化]</li>
 *     <li><a href="https://openjdk.org/jeps/393">393</a>：外部内存访问 API（Foreign-Memory Access API）[第三次孵化]</li>
 * </ol>
 *
 * @author Zero (cnzeropro@qq.com)
 * @since 2021/11/17 19:19
 */
public class Java16 {
    /**
     * 记录类
     * <p>
     * Java 是一种面向对象的语言，这使其可以创建类来保存数据，并使用封装来控制如何访问和修改数据。
     * 但创建数据容器的模板代码非常冗长，即使在最直接的情况下也需要大量时间成本（使用 Lombok 等工具除外）。
     * <p>
     * Java 16 引入了记录类，它是一种新的类形式。
     * 此举旨在声明不可变的数据载体类，通常用于表示简单的聚合数据结构。
     * 虽然它还是类的受限形式，就像枚举一样，但 record 提供了一种简洁的语法来定义主要目的是保存数据的类，并且自动提供了全参构造函数、{@code equals()}、{@code hashCode()} 和 {@code toString()} 方法
     * <p>
     * <b>注意</b>：记录类无法使用 {@code extends} 关键字继承自其他类，这是为了维护其不可变性、简化设计并避免构造函数相关的复杂问题。
     * 但其可以实现接口，也可以通过组合模式来扩展功能。
     *
     * @see <a href="https://openjdk.org/jeps/359">JEP 359: Records (Preview)</a>
     * @see <a href="https://openjdk.org/jeps/384">JEP 384: Records (Second Preview)</a>
     */
    public void recordClass() {
        // 传统实现
        var isoscelesTriangle = new IsoscelesTriangle(9.1, 7, 9.1);
        System.out.println("IsoscelesTriangle: " + isoscelesTriangle);

        // record 实现
        var rightTriangle = new RightTriangle(4, 3, 5);
        System.out.println("RightTriangle: " + rightTriangle);
    }

    /**
     * instanceof 模式匹配
     * <p>
     * Java 16 引入模式匹配（Pattern Matching）来增强 Java 编程语言中的 instanceof 操作符。
     * 这使得类型检查和转换更加简洁、安全，并减少了样板代码。
     *
     * @see <a href="https://openjdk.org/jeps/305">JEP 305: Pattern Matching for instanceof (Preview)</a>
     * @see <a href="https://openjdk.org/jeps/375">JEP 375: Pattern Matching for instanceof (Second Preview)</a>
     */
    public void instanceofPatternMatching() {
        Triangle[] triangles = {new IsoscelesTriangle(5.8, 4, 5.8),
                new RightTriangle(3, 4, 5),
                new EquilateralTriangle(7.1, 7.1, 7.1)};
        Triangle triangle = triangles[ThreadLocalRandom.current().nextInt(triangles.length)];
        System.out.println("Triangle: " + triangle);

        // 原来写法
        if (triangle instanceof RightTriangle) {
            RightTriangle rightTriangle = (RightTriangle) triangle;
            System.out.println("RightTriangle area: " + rightTriangle.area());
        }

        // 现在写法
        if (triangle instanceof IsoscelesTriangle isoscelesTriangle) {
            System.out.println("IsoscelesTriangle area: " + isoscelesTriangle.area());
        }
    }

    /**
     * stream 类新增方法
     * <p>
     * 包括：
     * {@link java.util.stream.Stream#toList}、
     * {@link java.util.stream.Stream#mapMulti}、
     * {@link java.util.stream.Stream#mapMultiToInt}、
     * {@link java.util.stream.Stream#mapMultiToLong}、
     * {@link java.util.stream.Stream#mapMultiToDouble}
     */
    public void addMethodsForStream() {
        var set = Set.of("Bob", "Tom", "Alice");

        var list0 = set.stream()
                .mapMulti((s, consumer) -> {
                    consumer.accept(s.toUpperCase());
                    consumer.accept(s.toLowerCase());
                })
                .toList();
        System.out.println(list0);

        // 同样的事 flatMap 也能实现，因为 mapMulti 本质也是委托给 flatMap
        // 但在以下情况下，mapMulti 比 flatMap 更可取
        // 用少量（可能为零）元素替换每个流元素时，可以减少创建一个新的 Stream 实例的开销
        // 使用命令式方法生成结果元素比以流的形式返回更容易时（此情况针对那些经过处理的流元素不好生成 Stream 对象时）
        var list1 = set.stream()
                .flatMap(s -> Stream.of(s.toUpperCase(), s.toLowerCase()))
                .toList();
        System.out.println(list1);
    }

    /**
     * 启用 C++14 语言特性
     * <p>
     * Java 16 引入了对 C++14 语言特性的支持，但这主要是针对 HotSpot 虚拟机本身的开发和优化，而不是直接面向 Java 应用程序开发者。
     */
    private void cppLanguageFeature() {
    }

    /**
     * 从 Mercurial 迁移到 Git
     * <p>
     * OpenJDK 项目的版本管理工具从 Mercurial（Hg）迁移到 Git。
     * 这是 Java 开发社区现代化和与更广泛的开源生态系统接轨的重要一步。
     */
    private void migrateToGit() {
    }

    /**
     * OpenJDK 项目迁移到 GitHub
     * <p>
     * 与 Mercurial 迁移到 Git 协同，这将把所有单仓库的 OpenJDK 项目迁移到 GitHub，包括 11 版本及以后的所有 JDK 功能发布版和 JDK 更新发布版。
     *
     * @see <a href="https://openjdk.org/jeps/369">JEP 369: Migrate to GitHub</a>
     */
    private void migrateToGithub() {
    }

    /**
     * ZGC：并发线程栈处理
     * <p>
     * Java 16 引入了 ZGC（Z Garbage Collector）的一项重要改进：并发线程栈处理（Concurrent Thread-Stack Processing）。
     * 这项特性显著减少了垃圾回收过程中的暂停时间，进一步提高了应用程序的性能和响应速度。
     * <table>
     *     <caption>ZGC 参数</caption>
     *     <tr>
     *         <th>参数</th>
     *         <th>说明</th>
     *     </tr>
     *     <tr>
     *         <td>-XX:+UseZGC</td>
     *         <td>启用 ZGC</td>
     *     </tr>
     *     <tr>
     *         <td>-XX:+ZConcurrentThreadStacks</td>
     *         <td>启用并发线程栈处理（Java 16 默认开启）</td>
     *     </tr>
     * </table>
     */
    private void zgc() {
    }

    /**
     * Unix 域套接字通道
     * <p>
     * Java 16 引入了对 Unix 域套接字（Unix-Domain Sockets, UDS）通道的支持，这是一项重要的新特性，旨在为 Java 应用程序提供与本地进程间通信（IPC）更高效的交互方式。<br>
     * Unix 域套接字是一种只在同一台机器上的进程之间进行通信的机制，它比传统的 TCP/IP 套接字更高效，并且提供了更好的安全性，因为它们不通过网络栈。
     *
     * @see java.net.UnixDomainSocketAddress
     * @see java.nio.channels.SocketChannel
     */
    @SneakyThrows
    private void unixSocketChannel() {
        Path path = Paths.get(String.format("%s/%s", System.getProperty("java.io.tmpdir"), "unix-example.socket"));
        Files.deleteIfExists(path);
        UnixDomainSocketAddress socketAddress = UnixDomainSocketAddress.of(path);

        // Server
        try (SocketChannel socketChannel = SocketChannel.open(socketAddress)) {
            String message = "Message from server";
            ByteBuffer byteBuffer = ByteBuffer.wrap(message.getBytes(StandardCharsets.UTF_8));
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
        }

        // Client
        try (SocketChannel socketChannel = SocketChannel.open(socketAddress);
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
            int size;
            while ((size = socketChannel.read(byteBuffer)) != -1) {
                byteBuffer.flip();
                byte[] data = new byte[size];
                byteBuffer.get(data);
                outputStream.write(data);
                byteBuffer.clear();
            }
            String message = outputStream.toString(StandardCharsets.UTF_8);
            System.out.println("Received: " + message);
        }
    }

    /**
     * Alpine Linux 移植
     * <p>
     * Alpine Linux 因其小巧的镜像大小和较低的资源占用而受到容器化应用和云部署的欢迎。
     * 然而，由于它使用的是 musl libc 而不是大多数 Linux 发行版中常见的 glibc，导致了一些兼容性问题。<br>
     * 在 Java 16 中，将 JDK 移植到 Alpine Linux，以及其他使用 musl 作为主要 C 库的 Linux 发行版，包括 x64 和 AArch64 架构
     */
    private void portAlpineLinux() {
    }

    /**
     * 弹性元空间
     * <p>
     * 在之前的实现中，元空间的扩展和收缩行为不够灵活，可能导致内存使用效率低下。
     * 特别是在频繁加载和卸载类的应用程序中，元空间可能会保留过多的未使用内存，或者在需要时无法快速扩展。<br>
     * 此项改动旨在优化 HotSpot 虚拟机中的元空间（Metaspace）管理，允许元空间根据实际需求动态增长和收缩，而不需要依赖于固定的大小或预设的阈值。
     */
    private void elasticMetaspace() {
    }

    /**
     * Windows AArch64 移植
     * <p>
     * 随着 ARM64 硬件在服务器和个人计算设备中的普及，Windows/AArch64 已成为一个重要的平台。
     * 此次改动是为 Windows on ARM64（也称为 AArch64）平台提供官方支持
     */
    private void portWindowsAArch64() {
    }

    /**
     * 基于值基类的警告
     * <p>
     * Java 中的值基类（如 {@link Integer}，{@link java.util.Optional} 等注解有 {@linkplain jdk.internal.ValueBased @ValueBased} 的类及其子类）是为了表示不可变的值对象而设计的。
     * 然而，在实践中，开发者有时会错误地依赖于这些类的地址比较（例如使用 {@code ==} 进行比较），而不是基于内容比较（如使用 {@code equals()} 方法）。
     * <p>
     * Java 16 引入了新的编译器警告机制，当检测到对值基类的不当使用时会触发警告。
     */
    public void warningForValueBasedClass() {
        // new Integer(int) 已在 Java 9 中被弃用，建议使用静态方法构造
        Integer a = Integer.valueOf(1);
        Integer b = Integer.valueOf(1);
        // 编译器将发出警告，尽管 -128 到 127（默认）范围内的整数值是常量池中的对象，它们是相等的
        System.out.println(a == b);
    }

    /**
     * 打包工具
     * <p>
     * Java 16 之前，开发者通常需要依赖第三方工具（如 Install4j、Inno Setup 等）来创建可执行的安装包。
     * 这些工具虽然功能强大，但增加了开发和维护的成本，并且对于简单的打包需求来说可能过于复杂。
     * 此外，它们并不总是与最新的 Java 版本保持同步。<br>
     * Java 16 引入一个新的命令行工具 {@code jpackage}，用于将 Java 应用程序打包成独立的、平台特定的软件包。
     * 这使得 Java 应用程序可以更方便地分发给最终用户，而无需他们安装单独的 JDK 或 JRE。
     *
     * @see <a href="https://openjdk.org/jeps/343">JEP 343: Packaging Tool (Incubator)</a>
     */
    private void packagingTool() {
    }

    /**
     * 默认情况下强封装 JDK 内部结构
     * <p>
     * Java 16 通过默认强封装 JDK 内部 API，增强 Java 平台的安全性和稳定性。
     * 这一改进确保了应用程序和库不能随意访问或依赖于内部实现细节，从而减少了潜在的兼容性问题和安全风险。
     * 但实际上仍然可以通过命令行选项（如 {@code --add-opens} 或 {@code --illegal-access=permit}）来访问，并且也可以通过反射等方式访问。
     *
     * @see <a href="https://openjdk.org/jeps/260">JEP 260: Encapsulate Most Internal APIs</a>
     * @see <a href="https://openjdk.org/jeps/261">JEP 261: Module System</a>
     */
    private void encapsulateJDKInternal() {
    }
}


