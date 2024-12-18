package org.zero;

import java.util.Arrays;
import java.util.HexFormat;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

/**
 * <a href="https://openjdk.org/projects/jdk/17/">JDK 17</a>
 * <a href="https://docs.oracle.com/javase/17/">JDK 17 Documentation</a>
 * <h1>Features</h1>
 * <ol>
 *     <li><a href="https://openjdk.org/jeps/306">306</a>：恢复始终严格的浮点数语义（Restore Always-Strict Floating-Point Semantics）{@link #floatingPoint()}</li>
 *     <li><a href="https://openjdk.org/jeps/356">356</a>：增强伪随机数生成器（Enhanced Pseudo-Random Number Generators）{@link #enhancePRNG()}</li>
 *     <li><a href="https://openjdk.org/jeps/382">382</a>：新的 macOS 渲染管线（New macOS Rendering Pipeline）{@link #renderingPipeline()}</li>
 *     <li><a href="https://openjdk.org/jeps/391">391</a>：macOS/AArch64 移植（macOS/AArch64 Port）{@link #macOSAArch64Port()}</li>
 *     <li><a href="https://openjdk.org/jeps/398">398</a>：废弃 Applet API 以移除（Deprecate the Applet API for Removal）{@link #deprecateAppletAPI()}</li>
 *     <li><a href="https://openjdk.org/jeps/403">403</a>：严格封装 JDK 内部结构（Strongly Encapsulate JDK Internals）{@link #deprecateAppletAPI()}</li>
 *     <li><a href="https://openjdk.org/jeps/409">409</a>：密封类（Sealed Classes）{@link #sealedClasses()}</li>
 *     <li>增加 HexFormat 类（Hex Formatting and Parsing Utility）{@link #addHexFormatClass()}</li>
 * </ol>
 * <ol>
 *     <li><a href="https://openjdk.org/jeps/406">406</a>：switch 模式匹配（Pattern Matching for switch Expressions and Statements）[首次预览]</li>
 * </ol>
 *
 * @author Zero
 * @since 2022/01/21
 */
public class Java17 {
    /**
     * 密封类
     * <p>
     * 密封（sealed）类表示一个可扩展的类，但只能通过已知的子类型列表进行扩展，而不能通过其他任意扩展。
     * 另外子类也相应存在三种情况：
     * <ol>
     *     <li>确认密封性（final），无法被扩展。</li>
     *     <li>传递密封性（sealed）。</li>
     *     <li>显式放弃密封性（non-sealed），此状态下需要由下游调用方承担打破密封的风险。</li>
     * </ol>
     */
    public void sealedClasses() {
        Pet cat = new Cat("喵喵", 3);
        cat.sleep();
        Pet blackCat = new BlackCat("妙妙", 5);
        blackCat.sleep();

        Pet dog = new Dog("旺旺", 1);
        dog.sleep();
        Pet yellowDog = new YellowDog("汪汪", 7);
        yellowDog.sleep();

        Pet pig = new Pig("哼哼", 12);
        pig.sleep();
    }

    /**
     * 增加 HexFormat 类
     * <p>
     * 用于字节和字符以及十六进制编码字符串之间进行转换
     */
    public void addHexFormatClass() {
        byte[] bytes = new byte[16];
        ThreadLocalRandom.current().nextBytes(bytes);
        String hexStr = HexFormat.of().formatHex(bytes);
        System.out.println("Hex Str: " + hexStr);
    }

    /**
     * 恢复始终严格的浮点数语义
     * <p>
     * 这一变化是为了确保 Java 程序在所有平台上具有一致的浮点运算行为，而不是同时具有严格的浮点语义（strictfp）和细微不同的默认浮点语义。<br>
     * 具体来说，通过移除对宽松浮点语义的支持来实现的，从而保证浮点计算始终遵循 IEEE 754 标准，并且在不同硬件和操作系统之间提供一致的结果。<br>
     * Java 的浮点运算语义主要由两个方面控制：
     * <ul>
     *     <li>{@code strictfp} 关键字：当应用于类、接口或方法时，{@code strictfp} 强制这些元素中的所有表达式都使用严格的浮点语义，这意味着它们必须严格遵守 IEEE 754 规范进行浮点运算。</li>
     *     <li>默认浮点语义：如果不使用 {@code strictfp}，那么根据 JVM 的实现，某些平台可能会采用更宽松的浮点语义，以优化性能。这种宽松模式可能不完全符合 IEEE 754 标准，导致在不同平台上得到不同的结果。</li>
     * </ul>
     * 但从 Java 17 开始，所有的浮点运算都是严格的，因此 {@code strictfp} 关键字变得多余。
     */
    public void floatingPoint() {
        double a = 0.1;
        double b = 0.2;
        double strictAdd = StrictFpExample.strictAdd(a, b);
        double noStrictAdd = StrictFpExample.noStrictAdd(a, b);
        System.out.println("strictAdd: " + strictAdd);
        System.out.println("noStrictAdd: " + noStrictAdd);
    }

    /**
     * 增强伪随机数生成器
     * <p>
     * JDK 17 引入了增强的伪随机数生成器（Enhanced Pseudo-Random Number Generators, PRNGs），这是为了提供更强大、更灵活和更高效的随机数生成功能。<br>
     * 这些改进主要体现在新的 {@link java.util.random} 包中，该包引入了一系列新的类和接口来支持不同的随机数生成需求。
     */
    public void enhancePRNG() {
        Map<String, List<String>> randomGeneratorFactories = RandomGeneratorFactory.all()
                .collect(groupingBy(RandomGeneratorFactory::group, Collectors.mapping(RandomGeneratorFactory::name, toList())));
        System.out.println("Random Generator Factories: " + randomGeneratorFactories);

        // L32X64MixRandom
        int[] array = RandomGenerator.getDefault()
                .ints(10, 0, 100)
                .toArray();
        System.out.println("Default random generate int array: " + Arrays.toString(array));
    }

    /**
     * 新的 macOS 渲染管线
     * <p>
     * 引入新的 macOS 渲染管道（Rendering Pipeline），以改善在 macOS 上图形渲染的性能和质量。<br>
     * 此项提供 Metal API 支持，更好的 HiDPI 支持，预防苹果从未来的 macOS 版本中移除 OpenGL API
     */
    private void renderingPipeline() {
    }

    /**
     * 将 JDK 移植到 macOS/AArch64
     * <p>
     * 从苹果公司宣布 Macintosh 电脑系列从 x64 过渡到 AArch64（即 Apple Silicon 或 ARM64）。JDK 出现了移植到 macOS/AArch64 的广泛需求。<br>
     * 虽然现有的 macOS 内置的 Rosetta 2 翻译器可运行 macOS/x64 版本的 JDK，但却存在显著的性能损失。<br>
     * 因此 JDK 17 引入了 macOS/AArch64 的支持，以提供更优的编译和运行环境。
     */
    private void macOSAArch64Port() {
    }

    /**
     * 弃用 Applet API 以移除
     * <p>
     * Applets 是一种早期的 Java 技术，允许开发者创建可以在 Web 浏览器中运行的小型应用程序。
     * 它们曾经是 Java 应用程序与 Web 集成的重要方式之一。然而，随着时间的推移，Web 技术的发展使得 Applets 显得过时：
     * <ul>
     *     <li>浏览器支持减少：大多数现代浏览器已经停止了对 NPAPI（Netscape Plugin Application Programming Interface）的支持。</li>
     *     <li>安全性问题：Applets 涉及到将代码下载到客户端并在用户的机器上执行，这带来了潜在的安全风险。</li>
     *     <li>性能和复杂性：与现代 Web 技术相比，如 JavaScript 和 H5，Applets 的启动时间和资源消耗显得不够高效，并且其开发和维护也更加复杂。</li>
     * </ul>
     * 尽管 Applet API 在 Java 9 已被废弃，但直到 Java 17 才确认未来移除（forRemoval = true）。
     */
    private void deprecateAppletAPI() {
    }

    /**
     * 严格封装 JDK 内部以保护
     * <p>
     * 严格封装 JDK 的所有内部元素，除了关键的内部 API，如：{@link sun.misc.Unsafe}。<br>
     * 此举旨在提高 Java 平台的安全性和稳定性，防止开发者直接访问和使用 JDK 的内部实现细节。<br>
     * Java 9 之前，开发者可以通过反射机制或其他方式访问 JDK 的内部类和方法，即使这些类和方法不是公开 API 的一部分。这为某些高级用途提供了灵活性，但它也带来了潜在的风险。<br>
     * 从 Java 9 开始，引入了模块系统（Java Platform Module System），它将 JDK 分割成多个模块，并默认情况下只导出公共 API。
     * 然而，在早期版本中，仍然可以通过命令行选项（如 {@code --add-opens} 或 {@code --illegal-access=permit}）来访问内部 API。<br>
     * 现在，Java 17 中，提供更严格的模块边界，增强了模块系统的完整性，并默认禁止非法访问，鼓励使用正式 API。
     */
    private void encapsulateJDKInternal() {
    }
}
