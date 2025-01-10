package org.zero;

import jdk.jfr.Configuration;
import jdk.jfr.consumer.RecordingStream;
import lombok.SneakyThrows;

import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.NumberFormat;
import java.time.Duration;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

/**
 * <a href="https://openjdk.org/projects/jdk/14/">JDK 14</a>
 * <a href="https://docs.oracle.com/javase/14/">JDK 14 Documentation</a>
 * <h1>Features</h1>
 * <ol>
 *     <li><a href="https://openjdk.org/jeps/345">345</a>：{@linkplain #g1NUMAAware G1 引入 NUMA-aware 内存分配（NUMA-Aware Memory Allocation for G1）}</li>
 *     <li><a href="https://openjdk.org/jeps/349">349</a>：{@linkplain #jfrEventStreaming JFR 事件流（JFR Event Streaming）}</li>
 *     <li><a href="https://openjdk.org/jeps/352">352</a>：{@linkplain #nonVolatileMappedByteBuffer 非易失性映射字节缓冲区（Non-Volatile Mapped Byte Buffers）}</li>
 *     <li><a href="https://openjdk.org/jeps/361">361</a>：{@linkplain #improveSwitchExpression switch 表达式（Switch Expressions）}</li>
 *     <li>{@linkplain #supportAccountingCurrencyFormat 支持会计币种格式（Accounting Currency Format Support）}</li>
 * </ol>
 * <ol>
 *     <li><a href="https://openjdk.org/jeps/305">305</a>：instanceof 模式匹配（Pattern Matching for instanceof）[首次预览]</li>
 *     <li><a href="https://openjdk.org/jeps/359">359</a>：记录类（Records）[首次预览]</li>
 *     <li><a href="https://openjdk.org/jeps/368">368</a>：文本块（Text Blocks）[第二次预览]</li>
 * </ol>
 * <ol>
 *     <li><a href="https://openjdk.org/jeps/343">343</a>：打包工具（Packaging Tool）[首次孵化]</li>
 *     <li><a href="https://openjdk.org/jeps/354">354</a>：外部内存访问 API（Foreign-Memory Access API）[首次孵化]</li>
 * </ol>
 *
 * @author Zero (cnzeropro@qq.com)
 * @since 2021/11/17 17:07
 */
public class Java14 {
    /**
     * switch 表达式改进
     * <p>
     * 新形式的开关标签，写成“case X ->”，表示如果标签匹配，则只执行标签右侧的代码，而不用再写 break。
     */
    public void improveSwitchExpression() {
        int day = ThreadLocalRandom.current().nextInt(1, 8);

        /*
         * Switch Statement（switch 语句）
         */
        // 传统写法
        System.out.println("传统写法：");
        switch (day) {
            case 1:
                System.out.println("星期一");
                break;
            case 2:
                System.out.println("星期二");
                break;
            case 3:
                System.out.println("星期三");
                break;
            case 4:
                System.out.println("星期四");
                break;
            case 5:
                System.out.println("星期五");
                break;
            case 6:
                System.out.println("星期六");
                break;
            case 7:
                System.out.println("星期日");
                break;
            default:
                throw new IllegalArgumentException("Invalid day");
        }
        // switch 语句通过 yield 关键字返回值
        System.out.println("新式写法（接收返回）：");
        String text0 = switch (day) {
            case 1:
                yield "星期一";
            case 2:
                yield "星期二";
            case 3:
                yield "星期三";
            case 4:
                yield "星期四";
            case 5:
                yield "星期五";
            case 6:
                yield "星期六";
            case 7:
                yield "星期日";
            default:
                throw new IllegalArgumentException("Invalid day");
        };
        System.out.println(text0);

        /*
         * Switch Expression（switch 表达式）
         */
        // switch 表达式
        System.out.println("新式写法（使用 switch 表达式）：");
        switch (day) {
            case 1 -> System.out.println("星期一");
            case 2 -> System.out.println("星期二");
            case 3 -> System.out.println("星期三");
            case 4 -> System.out.println("星期四");
            case 5 -> System.out.println("星期五");
            case 6 -> System.out.println("星期六");
            case 7 -> System.out.println("星期日");
            default -> throw new IllegalArgumentException("Invalid day");
        }
        // 使用 switch 表达式并直接接收返回
        System.out.println("新式写法（使用 switch 表达式并直接接收返回）：");
        String text1 = switch (day) {
            case 1 -> "星期一";
            case 2 -> "星期二";
            case 3 -> "星期三";
            case 4 -> "星期四";
            case 5 -> "星期五";
            case 6 -> "星期六";
            case 7 -> "星期日";
            default -> throw new IllegalArgumentException("Invalid day");
        };
        System.out.println(text1);
        // 使用 switch 表达式并进行某些操作后再接收返回
        System.out.println("新式写法（使用 switch 表达式并进行某些操作后再接收返回）：");
        String text2 = switch (day) {
            case 1 -> {
                System.out.println("Monday");
                yield "星期一";
            }
            case 2 -> {
                System.out.println("Tuesday");
                yield "星期二";
            }
            case 3 -> {
                System.out.println("Wednesday");
                yield "星期三";
            }
            case 4 -> {
                System.out.println("Thursday");
                yield "星期四";
            }
            case 5 -> {
                System.out.println("Friday");
                yield "星期五";
            }
            case 6 -> {
                System.out.println("Saturday");
                yield "星期六";
            }
            case 7 -> {
                System.out.println("Sunday");
                yield "星期日";
            }
            default -> throw new IllegalArgumentException("Invalid day");
        };
        System.out.println(text2);
    }

    /**
     * 支持会计币种格式
     */
    public void supportAccountingCurrencyFormat() {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);
        String format = numberFormat.format(453.62);
        System.out.println("format: " + format);
    }

    /**
     * G1 引入 NUMA-aware 内存分配
     * <p>
     * 非均匀内存访问（Non-Uniform Memory Access, NUMA）架构是一种多处理器计算机系统的内存设计。
     * 因为内存与每个插槽或核心的距离并不相等，插槽之间的内存访问具有不同的性能特征，通常更远的插槽访问具有更高的延迟。
     * 基于这个特性，所以为每个处理器分配了自己的本地内存。<br>
     * 为了优化性能，特别是在大规模并行处理环境中，JVM 的 G1 垃圾收集器引入了 NUMA-aware 内存分配策略。<br>
     * 要启用 G1 垃圾收集器的 NUMA-aware 内存分配功能，可以在启动 JVM 时指定以下参数：
     * <pre>{@code
     * -XX:+UseG1GC -XX:+UseNUMA
     * }</pre>
     *
     * @see <a href="https://openjdk.org/jeps/157">JEP 157: G1 GC: NUMA-Aware Allocation</a>
     */
    private void g1NUMAAware() {
    }

    /**
     * JFR 事件流
     * <p>
     * Java 14 引入 Java Flight Recorder (JFR) 事件流（Event Streaming）功能，允许实时地将 JFR 事件流式传输到外部系统。
     * 这一特性使得开发者和运维人员能够更及时地监控和分析应用程序的行为，尤其是在生产环境中。
     */
    @SneakyThrows
    public void jfrEventStreaming() {
        Configuration defaultConfiguration = Configuration.getConfiguration("default");
        // Configuration profileConfiguration = Configuration.getConfiguration("profile");
        try (RecordingStream recordingStream = new RecordingStream(defaultConfiguration)) {
            recordingStream.setMaxSize(Long.MAX_VALUE);

            // 配置事件
            recordingStream.enable("jdk.SocketWrite").withoutThreshold();
            recordingStream.enable("jdk.SocketRead").withoutThreshold();
            recordingStream.enable("jdk.CPULoad").withPeriod(Duration.ofSeconds(1));
            recordingStream.enable("jdk.PhysicalMemory");

            recordingStream.onEvent(event -> {
                // 模拟事件的处理逻辑，如：通过 http 将事件发送到外部系统
                System.out.println(event);
            });
            // recordingStream.onEvent("jdk.CPULoad", System.out::println);
            // recordingStream.onEvent("jdk.GarbageCollection", System.out::println);
            // recordingStream.onEvent("jdk.JVMInformation", System.out::println);

            // 启动事件流
            recordingStream.startAsync();

            Thread.sleep(10_000);

            // 将事件流导出到文件（Java 17）
            // recordingStream.dump(Path.of("events.jfr"));

            // 停止事件流（Java 20）
            // recordingStream.stop();
        }
    }

    /**
     * 非易失性映射字节缓冲区
     * <p>
     * Java 14 将 {@link MappedByteBuffer} 升级以支持访问非易失性存储器（NVM），其中映射的缓冲区是持久化的，即使在系统断电后数据也不会丢失。
     */
    @SneakyThrows
    private void nonVolatileMappedByteBuffer() {
        try (FileChannel channel = FileChannel.open(Path.of("example.dat"), StandardOpenOption.READ, StandardOpenOption.WRITE)) {
            // 将文件映射到内存中
            long size = 1024L * 1024;
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, size);

            // 写入一些数据
            String data = "Hello, Non-Volatile Mapped ByteBuffer!";
            buffer.put(data.getBytes(StandardCharsets.UTF_8));
            // 强制写入到持久存储
            buffer.force();

            // 读取数据并打印出来
            buffer.rewind();
            byte[] readData = new byte[data.length()];
            buffer.get(readData);
            System.out.println(new String(readData, StandardCharsets.UTF_8));
        }
    }
}

