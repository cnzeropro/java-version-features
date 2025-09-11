package org.zero;

import lombok.SneakyThrows;

import java.nio.ByteBuffer;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Set;

/**
 * <a href="https://openjdk.org/projects/jdk/13/">JDK 13</a>
 * <a href="https://docs.oracle.com/javase/13/">JDK 13 Documentation</a>
 * <h1>Features</h1>
 * <ol>
 *     <li><a href="https://openjdk.org/jeps/350">350</a>：{@linkplain #dynamicCDSArchive 动态 CDS 存档（Dynamic CDS Archives）}</li>
 *     <li><a href="https://openjdk.org/jeps/351">351</a>：{@linkplain #zgcUncommitUnusedMemory ZGC 取消提交未使用的内存（ZGC: Uncommit Unused Memory (Experimental)）}</li>
 *     <li><a href="https://openjdk.org/jeps/353">353</a>：{@linkplain #reimplementLegacySocketAPI 重新实现遗留套接字 API（Reimplement the Legacy Socket API）}</li>
 *     <li>{@linkplain #overloadMethodsForFileSystems FileSystems 新增 newFileSystem 重构方法（Added FileSystems.newFileSystem(Path, Map&ltString, ?&gt) Method）}</li>
 *     <li>{@linkplain #addMethodsForByteBuffer ByteBuffer 新增批量 get/put 方法（New java.nio.ByteBuffer Bulk get/put Methods Transfer Bytes Without Regard to Buffer Position）}</li>
 * </ol>
 * <ol>
 *     <li><a href="https://openjdk.org/jeps/355">355</a>：文本块（Text Blocks）[首次预览]</li>
 *     <li><a href="https://openjdk.org/jeps/354">354</a>：Switch 表达式（Switch Expressions）[第二次预览]</li>
 * </ol>
 *
 * @author Zero
 * @since 2020/04/01
 */
public class Java13 {
    /**
     * 动态 CDS（Class-Data Sharing）存档
     * <p>
     * 其核心思想是：在 Java 应用程序正常退出时，自动将其运行期间加载的所有应用程序类和库类（不包括已存在于基础 CDS 归档中的 JDK 类）动态归档到一个文件（.jsa）中。
     *
     * @see <a href="https://openjdk.org/jeps/310">JEP 310: Application Class-Data Sharing</a>
     *
     */
    private void dynamicCDSArchive() {
    }

    /**
     * ZGC 取消提交未使用的内存
     * <p>
     * 增强 ZGC 以将未使用的堆内存返回给操作系统。
     * 这对于担心内存占用的应用程序和环境非常有用。
     * 默认情况下，此功能处于启用状态，但可以使用 {@code -XX:-ZUncommit} 显式禁用。
     * 另外，如果将最小堆大小（-Xms）和最大堆大小（-Xmx）配置相等，则表示隐式禁用此功能。
     *
     * @see <a href="https://openjdk.org/jeps/333">JEP 333: ZGC: A Scalable Low-Latency Garbage Collector (Experimental)</a>
     * @see <a href="https://openjdk.org/jeps/377">JEP 377: ZGC: A Scalable Low-Latency Garbage Collector (Production)</a>
     */
    private void zgcUncommitUnusedMemory() {
    }

    /**
     * 重新实现遗留的套接字 API
     * <p>
     * 此特性旨在用更现代、易于维护和调试的实现来替换 {@link java.net.Socket} 和 {@link java.net.ServerSocket} API 所使用的遗留底层实现。<br>
     * 旧的实现（称为 PlainSocketImpl）可以追溯到 JDK 1.0 时代，是 Java 和 C 代码的混合体，存在许多问题：
     * <ul>
     *     <li>难以维护和调试：遗留代码结构复杂，难以理解和修改</li>
     *     <li>并发问题：旧实现存在一些并发问题，需要大量重写才能正确解决</li>
     *     <li>与现代 Java 特性不兼容：尤其是与 Project Loom 探索的用户模式线程（纤程，Fibers）不兼容</li>
     *     <li>使用线程栈作为I/O缓冲区：这种方法多次导致需要增加默认线程栈大小</li>
     *     <li>异步关闭机制不可靠：使用本地数据结构支持异步关闭，是多年來可靠性和可移植性问题的根源</li>
     * </ul>
     * 新的实现叫做 NioSocketImpl，它不是一个新的 API，而是底层实现机制的替换。原有的 {@link java.net.Socket} 和 {@link java.net.ServerSocket} 的代码仍然可以继续工作，只是底层变得更好用了。
     */
    private void reimplementLegacySocketAPI() {
    }

    /**
     * FileSystems 类新增 newFileSystem 的重构方法
     * <ul>
     *     <li>{@link FileSystems#newFileSystem(Path)}</li>
     *     <li>{@link FileSystems#newFileSystem(Path, Map)}</li>
     *     <li>{@link FileSystems#newFileSystem(Path, Map, ClassLoader)}</li>
     * </ul>
     */
    @SneakyThrows
    public void overloadMethodsForFileSystems() {
        FileSystem fileSystem = FileSystems.newFileSystem(Path.of("C:\\Users\\"));
        try (fileSystem) {
            Set<String> attributeViews = fileSystem.supportedFileAttributeViews();
            System.out.println(attributeViews);
        }
    }

    /**
     * ByteBuffer 新增批量 get/put 方法
     * <ul>
     *     <li>{@link ByteBuffer#put(int, byte[], int, int)}</li>
     *     <li>{@link ByteBuffer#put(int, byte[])}</li>
     *     <li>{@link ByteBuffer#get(int, byte[])}</li>
     *     <li>{@link ByteBuffer#get(int, byte[], int, int)}</li>
     *     <li>{@link ByteBuffer#slice(int, int)}</li>
     * </ul>
     */
    public void addMethodsForByteBuffer() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put(1, new byte[1024], 0, 512);
        System.out.println(byteBuffer);
    }
}
