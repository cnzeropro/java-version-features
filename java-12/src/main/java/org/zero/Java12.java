package org.zero;

import java.lang.constant.ConstantDesc;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * <a href="https://openjdk.org/projects/jdk/12/">JDK 12</a>
 * <a href="https://docs.oracle.com/javase/12/">JDK 12 Documentation</a>
 * <h1>Features</h1>
 * <ol>
 *     <li><a href="https://openjdk.org/jeps/189">189</a>：{@linkplain #shenandoahGC Shenandoah 垃圾收集器（Shenandoah: A Low-Pause-Time Garbage Collector (Experimental)）}</li>
 *     <li><a href="https://openjdk.org/jeps/230">230</a>：{@linkplain #microbenchmarkSuite 微基准测试套件（Microbenchmark Suite）}</li>
 *     <li><a href="https://openjdk.org/jeps/334">334</a>：{@linkplain #jvmConstantApi JVM 常量 API（JVM Constants API）}</li>
 *     <li><a href="https://openjdk.org/jeps/340">340</a>：{@linkplain #oneAArch64Port 保留唯一 AArch64 移植（One AArch64 Port, Not Two）}</li>
 *     <li><a href="https://openjdk.org/jeps/341">341</a>：{@linkplain #defaultCDSArchive 默认 CDS 存档（Default CDS Archives）}</li>
 *     <li><a href="https://openjdk.org/jeps/344">344</a>：{@linkplain #g1AbortableMixedCollection 可中止的 G1 混合垃圾回收（Abortable Mixed Collections for G1）}</li>
 *     <li><a href="https://openjdk.org/jeps/346">346</a>：{@linkplain #g1ReturnUnusedCommittedMemory 及时从 G1 返还未使用的已提交内存（Promptly Return Unused Committed Memory from G1）}</li>
 *     <li>{@linkplain #supportCompactNumberFormatting() 支持紧凑的数字格式（Support for Compact Number Formatting）}</li>
 * </ol>
 * <ol>
 *      <li><a href="https://openjdk.org/jeps/325">325</a>：Switch 表达式（Switch Expressions）[首次预览]</li>
 * </ol>
 *
 * @author Zero
 * @since 2019/08/08
 */
public class Java12 {
    /**
     * Shenandoah：一种低暂停时间的垃圾收集器
     * <p>
     * 添加一种名为 Shenandoah 的新垃圾回收算法，通过让 Java 线程在运行时并发执行回收工作来减少 GC 暂停时间。
     * 使用 Shenandoah 时，停顿时间与堆大小无关，这意味着无论程序堆是 200 MB 还是 200 GB，都会有相同的稳定的停顿时间。
     */
    private void shenandoahGC() {
    }

    /**
     * 微基准测试套件
     * <p>
     * 为 JDK 源代码添加一套基本的微基准测试，并让开发者能够轻松运行现有的微基准测试和创建新的测试。
     */
    private void microbenchmarkSuite() {
    }

    /**
     * JVM 常量 API
     * <p>
     * 引入一个 API 来对关键类文件和运行时实体的名义描述进行建模，特别是可以从常量池中加载的常量。
     *
     * @see <a href="https://openjdk.org/jeps/303">JEP 303: Intrinsics for the LDC and INVOKEDYNAMIC Instructions</a>
     */
    public void jvmConstantApi() {
        ConstantDesc constantDesc = 123;
        System.out.println(constantDesc);
    }

    /**
     * 保留唯一 AArch64 移植（淘汰另一个）
     * <p>
     * 在 JDK 12 之前，OpenJDK 代码库中实际上存在两个独立的 64 位 ARM 端口：
     * <ul>
     *     <li>arm64 端口：主要由 Oracle 贡献，源代码位于 src/hotspot/cpu/arm 目录下。</li>
     *     <li>aarch64 端口：主要由 Red Hat 和 Linux 社区开发，源代码位于 src/hotspot/cpu/aarch64 目录下。</li>
     * </ul>
     * 现在，移除所有与冗余的 arm64 端口相关的源代码，同时保留 32 位 ARM 端口和优化的 64 位 aarch64 端口。这使得 aarch64 端口成为 64 位 ARM 架构的默认且唯一的实现
     */
    private void oneAArch64Port() {
    }

    /**
     * 默认 CDS 存档
     * <p>
     * 此特性旨在简化类数据共享（CDS）功能的使用，以改善 Java 应用程序的启动时间。
     * JEP 341 之前想要利用 CDS 的优势，必须手动执行一个步骤（{@code java -Xshare:dump}）来生成归档文件，但现在，JDK 12 默认生成归档文件。
     */
    private void defaultCDSArchive() {
    }

    /**
     * 可中止的 G1 混合垃圾回收
     * <p>
     * 其允许 G1 垃圾回收器在混合收集（Mixed Collection）过程中，如果预测其无法在设定的暂停时间目标内完成，则中止本次收集
     */
    private void g1AbortableMixedCollection() {
    }

    /**
     * 及时从 G1 返回未使用的已提交内存
     * <p>
     * 它旨在增强 G1 垃圾回收器，使其能够在应用空闲时自动将未使用的 Java 堆内存返还给操作系统
     */
    private void g1ReturnUnusedCommittedMemory() {
    }

    public void supportCompactNumberFormatting() {
        NumberFormat fmt = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.SHORT);
        String n1 = fmt.format(1000);
        String n2 = fmt.format(1_000_000);
        String n3 = fmt.format(100_000);

        System.out.println("1000: " + n1);
        System.out.println("1000000: " + n2);
        System.out.println("100000: " + n3);
    }
}
