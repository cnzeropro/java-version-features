package org.zero;

import lombok.SneakyThrows;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.UnixDomainSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
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
 *     <li><a href="https://openjdk.org/jeps/376">376</a>：{@linkplain #unixSocketChannel Unix 域套接字通道（Unix-Domain Socket Channels）}</li>
 *     <li><a href="https://openjdk.org/jeps/394">394</a>：{@linkplain #upgradeInstanceofKeyword instanceof 模式匹配（Pattern Matching for instanceof）}</li>
 *     <li><a href="https://openjdk.org/jeps/395">395</a>：{@linkplain #addRecordClasses 记录类（Records）}</li>
 *     <li>{@link #addMethodsForStream stream 类新增方法} </li>
 * </ol>
 * <ol>
 *     <li><a href="https://openjdk.org/jeps/397">397</a>：密封类（Sealed Classes）[第二次预览]</li>
 * </ol>
 * <ol>
 *     <li><a href="https://openjdk.org/jeps/338">338</a>：向量 API（Vector API）[首次孵化]</li>
 * </ol>
 *
 * @author Zero (cnzeropro@qq.com)
 * @since 2021/11/17 19:19
 */
public class Java16 {

    /**
     * 新增记录类
     * <p>
     * Java是一种面向对象的语言，这使其可以创建类来保存数据，并使用封装来控制如何访问和修改该数据。
     * 但创建数据类型非常冗长，即使在最直接的情况下也需要大量代码。
     * 然而记录（record）却是表示数据类的一种简单得多的方法。
     * record 是一种新的类型，虽然它还是类的受限形式，就像枚举一样，但 record 具有名称和状态描述，用于定义记录的组成部分。
     */
    public void addRecordClasses() {
        var triangle = new Triangle(9.1, 7, 6.9);
        System.out.println(triangle.getA());
        System.out.println(triangle);

        // 使用记录类让代码比 jdk 16 以前更为简单高效，可与Triangle.java进行对比
        var triangleRecord = new TriangleRecord(4, 8.2, 7);
        System.out.println(triangleRecord.a());
        System.out.println(triangleRecord);
    }

    /**
     * instanceof 模式匹配
     */
    public void upgradeInstanceofKeyword() {
        Triangle[] triangles = {new RightTriangle(3, 4, 5), new Triangle(7, 4, 6)};
        Triangle triangle = triangles[ThreadLocalRandom.current().nextInt(triangles.length)];
        System.out.println("Triangle: " + triangle);

        // 原来写法
        if (triangle instanceof RightTriangle) {
            RightTriangle rightTriangle = (RightTriangle) triangle;
            System.out.println("RightTriangle: " + rightTriangle);
        }

        // 现在写法
        if (triangle instanceof RightTriangle rightTriangle) {
            System.out.println("RightTriangle: " + rightTriangle);
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
     * @see SocketChannel
     */
    @SneakyThrows
    public void unixSocketChannel() {
        Path path = Paths.get(String.format("%s/%s", System.getProperty("java.io.tmpdir"), "unix.socket"));
        UnixDomainSocketAddress socketAddress = UnixDomainSocketAddress.of(path);

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (int i = 0; i < 3; i++) {
            executorService.submit(() -> {
                try (SocketChannel socketChannel = SocketChannel.open(socketAddress)) {
                    String message = "Message from server";
                    ByteBuffer byteBuffer = ByteBuffer.wrap(message.getBytes(StandardCharsets.UTF_8));
                    byteBuffer.flip();
                    socketChannel.write(byteBuffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        TimeUnit.SECONDS.sleep(1);

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
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            Files.deleteIfExists(path);
        }

        TimeUnit.SECONDS.sleep(10);
    }
}


