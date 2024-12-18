package org.zero;

import lombok.SneakyThrows;

import java.lang.foreign.Arena;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.Linker;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SequenceLayout;
import java.lang.foreign.SymbolLookup;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * <a href="https://openjdk.org/projects/jdk/22/">JDK 22</a>
 * <a href="https://docs.oracle.com/en/java/javase/22/">JDK 22 Documentation</a>
 * <h2>Features</h2>
 * <ol>
 *     <li><a href="https://openjdk.org/jeps/454">454</a>：外部函数和内存 API（Foreign Function & Memory API）{@link #foreignFunctionAndMemoryApi()}</li>
 *     <li><a href="https://openjdk.org/jeps/458">458</a>：启动多文件源代码程序（Launch Multi-File Source-Code Programs）{@link #launchMultiFileProgram()}</li>
 *     <li><a href="https://openjdk.org/jeps/456">456</a>：匿名变量和模式（Unnamed Variables & Patterns）{@link #unnamedVariableAndPattern()}</li>
 *     <li><a href="https://openjdk.org/jeps/423">423</a>：G1 引入区域固定机制（Region Pinning for G1）{@link #g1RegionPinning()}</li>
 * </ol>
 * <ol>
 *     <li><a href="https://openjdk.org/jeps/447">447</a>：允许在构造函数的 super(...) 调用之前执行语句（Statements Before super(...)）[首次预览]</li>
 *     <li><a href="https://openjdk.org/jeps/457">457</a>：类文件 API（Class-File API）[首次预览]</li>
 *     <li><a href="https://openjdk.org/jeps/459">459</a>：字符串模板（String Templates）[第二次预览]</li>
 *     <li><a href="https://openjdk.org/jeps/461">461</a>：流收集器（Stream Gatherers）[首次预览]</li>
 *     <li><a href="https://openjdk.org/jeps/462">462</a>：结构化并发（Structured Concurrency）[第二次预览]</li>
 *     <li><a href="https://openjdk.org/jeps/463">463</a>：匿名类和实例主方法（Implicitly Declared Classes and Instance Main Methods）[第二次预览]</li>
 *     <li><a href="https://openjdk.org/jeps/464">464</a>：作用域值（Scoped Values）[第二次预览]</li>
 * </ol>
 * <ol>
 *     <li><a href="https://openjdk.org/jeps/460">460</a>：向量 API（Vector API）[第七次孵化]</li>
 * </ol>
 *
 * @author Zero
 * @since 2024/11/10
 */
public class Java22 {
    /**
     * 外部函数和内存 API
     * <p>
     * Foreign Function & Memory API（FFM）是 JDK 19 引入的一项实验性功能，到如今已逐渐成熟。<br>
     * 它旨在提供一种安全且高效的方式与外部代码（如 C 库）和外部内存（非 JVM 内存）进行交互。<br>
     * <b>主要特性</b>
     * <ol>
     *     <li>Foreign Function<br>
     *     FF 允许 Java 程序调用外部函数（通常是 C 编写），可以避免编写繁琐的 JNI（Java Native Interface）代码，使得与本地库的集成更加简单和直接。
     *     </li>
     *     <li>Foreign Memory<br>
     *     FM 提供了一套新的类和方法来管理和操作非 Java 堆上的内存。相比传统的 {@link sun.misc.Unsafe} 类，FM 提供了更好的封装性和安全性，同时保持了必要的性能优势。
     *     </li>
     * </ol>
     */
    @SneakyThrows
    public void foreignFunctionAndMemoryApi() {
        /*
         * 示例1
         */
        // Arena 有 4 种创建方式：
        // 1. Arena.ofConfined()：创建一个 confined Arena，适用于单线程环境，需要手动 close
        // 2. Arena.ofShared()：创建一个 shared Arena，可以在多个线程之间共享，需要手动 close 并且只有当最后一个引用被释放时才会关闭
        // 3. Arena.ofAuto()：创建一个自动 Arena，提供有界生命周期，由 JVM 自动管理，无需手动 close
        // 4. Arena.global()：创建一个全局 Arena，在 JVM 退出时自动关闭，无需手动 close
        Arena offHeap = Arena.ofAuto();
        // 分配一个 1024 个 int 的内存段
        SequenceLayout layout = MemoryLayout.sequenceLayout(1024, ValueLayout.JAVA_INT);
        MemorySegment memorySegment = offHeap.allocate(layout);
        // 分别将 64、512 写入到内存段中的第 101、1000 个 int 数据位置
        memorySegment.setAtIndex(ValueLayout.JAVA_INT, 100, 64);
        memorySegment.setAtIndex(ValueLayout.JAVA_INT, 1000, 512);
        // 获取内存段所有 int 数据总和
        int sum = memorySegment.elements(ValueLayout.JAVA_INT)
                .parallel()
                .mapToInt(s -> s.get(ValueLayout.JAVA_INT, 0))
                .sum();
        System.out.println(sum);

        Linker linker = Linker.nativeLinker();
        SymbolLookup symbolLookup = linker.defaultLookup();

        /*
         * 示例2
         * 调用 C 函数 strlen
         */
        // size_t strlen(const char *str)
        MemorySegment strlenAddress = symbolLookup.find("strlen").orElseThrow();
        FunctionDescriptor strlenDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS);
        MethodHandle strlenHandle = linker.downcallHandle(strlenAddress, strlenDescriptor);
        try (Arena arena = Arena.ofConfined()) {
            MemorySegment strAddress = arena.allocateFrom("Hello");
            long len = (long) strlenHandle.invokeExact(strAddress);
            System.out.println(len);
        }

        MethodHandles.Lookup lookup = MethodHandles.lookup();
        /*
         * 示例3
         * C 函数 qsort 与 Java QSort.compare 函数联合使用
         */
        // void qsort(void *base, size_t nitems, size_t size, int (*compar)(const void *, const void *));
        MemorySegment qsortAddress = symbolLookup.find("qsort").orElseThrow();
        FunctionDescriptor qsortDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_LONG, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS);
        MethodHandle qsortHandle = linker.downcallHandle(qsortAddress, qsortDescriptor);
        FunctionDescriptor compareDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS.withTargetLayout(ValueLayout.JAVA_INT), ValueLayout.ADDRESS.withTargetLayout(ValueLayout.JAVA_INT));
        MethodHandle compareHandle = lookup.findStatic(QSort.class, "compare", compareDescriptor.toMethodType());
        try (Arena arena = Arena.ofConfined()) {
            MemorySegment compareAddress = linker.upcallStub(compareHandle, compareDescriptor, arena);
            MemorySegment arrayAddress = arena.allocateFrom(ValueLayout.JAVA_INT, 0, 9, 3, 4, 6, 5, 1, 8, 2, 7);
            int[] array = arrayAddress.toArray(ValueLayout.JAVA_INT);
            System.out.println("array: " + Arrays.toString(array));
            qsortHandle.invokeExact(arrayAddress, 10L, 4L, compareAddress);
            int[] sorted = arrayAddress.toArray(ValueLayout.JAVA_INT);
            System.out.println("sorted: " + Arrays.toString(sorted));
        }

        /*
         * 示例4
         * 调用 C 函数 printf
         */
        // int printf(const char *format, ...)
        MemorySegment printfAddress = symbolLookup.find("printf").orElseThrow();
        FunctionDescriptor printfDescriptor = FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT);
        MethodHandle printfHandle = linker.downcallHandle(printfAddress, printfDescriptor, Linker.Option.firstVariadicArg(1));
        try (Arena arena = Arena.ofConfined()) {
            int size = (int) printfHandle.invokeExact(arena.allocateFrom("%s is %d years old"), arena.allocateFrom("Bob"), 18);
            System.out.println(size);
        }

        /*
         * 示例5
         * 加载外部库调用 say
         */
        // try (Arena arena = Arena.ofConfined()) {
        //     SymbolLookup libDemoLookup = SymbolLookup.libraryLookup("lib_demo", arena);
        //     MemorySegment sayAddress = libDemoLookup.find("say").orElseThrow();
        //     FunctionDescriptor sayDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS);
        //     MethodHandle sayHandle = linker.downcallHandle(sayAddress, sayDescriptor);
        //     sayHandle.invokeExact(arena.allocateFrom("Hello!"));
        // }
    }

    /**
     * 启动多文件源代码程序
     * <p>
     * JAVA 11 增强了 Java 应用程序启动器，使其能够直接运行 {@code .java} 源文件，而无需明确的编译步骤。<br>
     * 但却限制程序的所有源代码都必须放在同一个 {@code .java} 文件中<br>
     * 现在 JAVA 22 优化了该项限制，允许源代码放在不同的 {@code .java} 文件中，甚至可以放在其他 jar 中
     *
     * @see org.zero.Main
     */
    private void launchMultiFileProgram() {
    }

    /**
     * 匿名变量和模式
     *
     * <ol>
     *     <li>各种代码块中的局部变量</li>
     *     <li>lambda 表达式的形参</li>
     *     <li>try-with-resources 资源常量</li>
     *     <li>for 循环变量</li>
     *     <li>foreach 循环变量</li>
     *     <li>catch 块的异常参数</li>
     * </ol>
     */
    public void unnamedVariableAndPattern() {
        /*
         * 示例1
         */
        var list = List.of(1, 2, 3);
        int count = 0;
        for (var _ : list) {
            count++;
        }
        System.out.println("list size: " + count);

        /*
         * 示例2
         */
        Shape[] shapes = {new Circle<>(7.9), new Rectangle<>(2.4F, 3.6F), new Triangle<>(4, 6, 5)};
        int i = ThreadLocalRandom.current().nextInt(shapes.length);
        Shape shape = shapes[i];
        if (shape instanceof Triangle(var _, var y, var z)) {
            var temp = y.toString() + z.toString();
            int num = 0;
            try {
                num = Integer.parseInt(temp);
            } catch (NumberFormatException _) {
                System.out.println("temp is not number: " + temp);
            }
            var circle = new Circle<>(num);
            System.out.printf("""
                    The
                    circle: %s
                    is from this
                    triangle: %s
                    """, circle, shape);
        } else {
            System.out.printf("shape is not a triangle: %s%n", shape);
        }
    }

    /**
     * G1(Garbage-First Garbage Collector) 引入区域固定机制
     * <p>
     * 引入 Region Pinning 来降低使用 JNI (Java Native Interface) 时 G1 收集器的延时
     */
    private void g1RegionPinning() {

    }
}