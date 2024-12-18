package org.zero;

import lombok.SneakyThrows;

import java.lang.reflect.Method;
import java.net.spi.InetAddressResolverProvider;
import java.nio.charset.Charset;
import java.util.List;
import java.util.ServiceLoader;

/**
 * <a href="https://openjdk.org/projects/jdk/18/">JDK 18</a>
 * <a href="https://docs.oracle.com/javase/18/">JDK 18 Documentation</a>
 * <h1>Features</h1>
 * <ol>
 *     <li><a href="https://openjdk.org/jeps/400">400</a>：默认字符集为 UTF-8（UTF-8 by Default）{@link #changeDefaultCharset()}</li>
 *     <li><a href="https://openjdk.org/jeps/408">408</a>：简易 Web 服务器（Simple Web Server）{@link #simpleWebServer()}</li>
 *     <li><a href="https://openjdk.org/jeps/418">418</a>：引入互联网地址解析的 SPI（Internet-Address Resolution SPI）{@link #addInternetAddressResolverSpi()}</li>
 *     <li><a href="https://openjdk.org/jeps/413">413</a>：文档注释中引入代码片段（Code Snippets in Java API Documentation）{@link #codeSnippet()}</li>
 *     <li><a href="https://openjdk.org/jeps/416">416</a>：使用方法句柄重写核心反射（Reimplement Core Reflection with Method Handles）{@link #reimplementCoreReflection()}</li>
 *     <li><a href="https://openjdk.org/jeps/421">421</a>：废弃终结机制（Deprecate Finalization for Removal）{@link #deprecateFinalization()}</li>
 * </ol>
 * <ol>
 *     <li><a href="https://openjdk.org/jeps/420">420</a>：switch 模式匹配（Pattern Matching for switch）[第二次预览]</li>
 * </ol>
 * <ol>
 *     <li><a href="https://openjdk.org/jeps/417">417</a>：向量 API（Vector API）[第三次孵化]</li>
 *     <li><a href="https://openjdk.org/jeps/419">419</a>：外部函数和内存 API（Foreign Function & Memory API）[第四次孵化]</li>
 * </ol>
 *
 * @author Zero
 * @since 2022/08/30
 */
public class Java18 {
    /**
     * 更换默认字符集为 UTF-8
     */
    public void changeDefaultCharset() {
        Charset charset = Charset.defaultCharset();
        System.out.println(charset);
    }

    /**
     * 互联网地址解析的 SPI
     * <p>
     * 引入用于主机名和地址解析的服务提供程序接口（SPI），以便 java.net.InetAddress 可以使用平台内置解析器以外的解析器。
     */
    public void addInternetAddressResolverSpi() {
        ServiceLoader<InetAddressResolverProvider> serviceLoader = ServiceLoader.load(InetAddressResolverProvider.class);
        List<ServiceLoader.Provider<InetAddressResolverProvider>> providers = serviceLoader.stream().toList();
        System.out.println("InetAddressResolver providers: " + providers);
    }

    /**
     * 简易 Web 服务器
     * <p>
     * 提供一款开箱即用的静态 HTTP 文件服务器，易于设置且功能最小化。<br>
     * 通过 {@code jwebserver} 命令行工具，可以轻松地启动一个简单的 HTTP 服务器，而无需编写任何代码。
     * <table>
     *     <caption>常用参数</caption>
     *     <thead>
     *         <tr>
     *             <th>参数</th>
     *             <th>说明</th>
     *             <th>示例</th>
     *         </tr>
     *     </thead>
     *     <tbody>
     *         <tr>
     *             <td>-h</td>
     *             <td>打印帮助消息</td>
     *             <td></td>
     *         </tr>
     *         <tr>
     *             <td>-p</td>
     *             <td>指定绑定的端口。默认：8000</td>
     *             <td>-p 9000</td>
     *         </tr>
     *         <tr>
     *             <td>-b</td>
     *             <td>指定绑定的网络接口。{@code 0.0.0.0} 表示所有网络接口。默认：回环地址</td>
     *             <td>-b 0.0.0.0</td>
     *         </tr>
     *         <tr>
     *             <td>-d</td>
     *             <td>指定提供服务的目录。默认：从当前目录提供服务</td>
     *             <td>-d /root/zero/http/</td>
     *         </tr>
     *     </tbody>
     * </table>
     */
    private void simpleWebServer() {
    }

    /**
     * 文档注释中引入代码片段
     * <p>
     * 之前：
     * <pre>{@code
     * public void sayHello() {
     *     Console console = System.console();
     *     String name = console.readLine();
     *     System.out.println("Hello, " + name);
     * }
     * }</pre>
     * 现在：
     * {@snippet :
     * public void sayHello() {
     *     Console console = System.console();
     *     String name = console.readLine();
     *     System.out.println("Hello, " + name);
     * }
     *}
     */
    private void codeSnippet() {
    }

    /**
     * 使用方法句柄重写核心反射
     * <p>
     * 在 {@linkplain java.lang.invoke.MethodHandle 方法句柄} 之上，重新实现 {@linkplain java.lang.reflect.Method Method}、{@linkplain java.lang.reflect.Constructor Constructor} 和 {@linkplain java.lang.reflect.Field Field}。<br>
     * 之前 {@linkplain java.lang.reflect.Field Field} 的底层机制是使用 {@link sun.misc.Unsafe} 实现，
     * {@linkplain java.lang.reflect.Method Method} 的底层机制是使用 native 方法实现，
     * {@linkplain java.lang.reflect.Constructor Constructor}  的底层机制是使用 {@link sun.misc.Unsafe} 或 native 方法实现，<br>
     * 方法句柄的引入提供了更好的性能和一致性。
     */
    @SneakyThrows
    public void reimplementCoreReflection() {
        Method method = Integer.class.getMethod("toHexString", int.class);
        Object str = method.invoke(null, 123);
        System.out.println(str);
    }

    /**
     * 废弃终结机制
     * <p>
     * 在 Java 中，finalization 是指当一个对象即将被垃圾回收器回收之前，允许该对象执行一些清理工作的过程。<br>
     * 这通常通过定义一个类的 {@linkplain Object#finalize() finalize} 方法来实现。
     * {@linkplain Object#finalize() finalize} 方法会在对象的垃圾回收过程中自动调用，以便释放资源或执行其他必要的清理操作。
     * 它自 Java 1.0 引入，旨在帮助避免资源泄漏。<br>
     * 但不幸的是，终结机制一直存在几个关键、根本的缺陷：
     * <ol>
     *     <li>不可预测的延迟 - 对象变得不可达与其终结器被调用之间可能经过任意长的时间。事实上，垃圾回收器并不保证任何终结器会被调用。</li>
     *     <li>无约束行为 - 终结器代码可以执行任何操作。特别是，它可以保存正在终结的对象的引用，从而复活该对象，使其再次可访问。</li>
     *     <li>始终启用 - 终结化没有显式的注册机制。具有终结器的类为该类的每个实例启用终结化，无论是否需要。即使对象不再需要，也无法取消对象的终结化。</li>
     *     <li>未指定线程 - 终结器在未指定的线程上运行，顺序任意。无法控制线程或顺序。</li>
     * </ol>
     * 因此，从 Java 9 开始，废弃 {@link Object#finalize()} 方法，并引入了更安全的终结机制，以解决上述问题。<br>
     * 到现在 Java 18，在已禁用或删除终结的 JVM 中运行时，垃圾回收器永远不会调用 {@linkplain Object#finalize() finalize}。
     * 在启用 Finalization 的 JVM 中，垃圾回收器可能仅在无限期延迟后调用 {@linkplain Object#finalize() finalize}。
     */
    @SneakyThrows
    public void deprecateFinalization() {
        FinalizationExample example = new FinalizationExample();
        example.finalize();
    }
}