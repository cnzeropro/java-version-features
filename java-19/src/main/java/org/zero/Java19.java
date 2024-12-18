package org.zero;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.WeakHashMap;

/**
 * <a href="https://openjdk.org/projects/jdk/19/">JDK 19</a>
 * <a href="https://docs.oracle.com/javase/19/">JDK 19 Documentation</a>
 * <h1>Features</h1>
 * <ol>
 *     <li><a href="https://openjdk.org/jeps/422">422</a>：Linux/RISC-V 移植（Linux/RISC-V Port）{@link #linuxRISCVPort()}</li>
 *     <li>创建预分配 hash 集合的方法（New Methods to Create Preallocated HashMaps and HashSets）{@link #addPreallocatedHashes()}</li>
 * </ol>
 * <ol>
 *     <li><a href="https://openjdk.org/jeps/427">427</a>：switch 模式匹配（Pattern Matching for switch）[第三次预览]</li>
 *     <li><a href="https://openjdk.org/jeps/405">405</a>：记录模式（Record Patterns）[首次预览]</li>
 *     <li><a href="https://openjdk.org/jeps/425">425</a>：虚拟线程（Virtual Threads）[首次预览]</li>
 *     <li><a href="https://openjdk.org/jeps/424">424</a>：外部函数和内存 API（Foreign Function & Memory API）[首次预览]</li>
 * </ol>
 * <ol>
 *     <li><a href="https://openjdk.org/jeps/428">428</a>：结构化并发（Structured Concurrency）[首次孵化]</li>
 *     <li><a href="https://openjdk.org/jeps/426">426</a>：向量 API（Vector API）[第四次孵化]</li>
 * </ol>
 *
 * @author Zero
 * @since 2023/03/21
 */
public class Java19 {
    /**
     * 将 JDK 移植到 Linux/RISC-V
     * <p>
     * RISC-V 是一种开源指令集架构（ISA），因其灵活性、模块化和可扩展性而受到广泛关注，并且在嵌入式系统、物联网（IoT）设备以及高性能计算领域有着应用潜力。<br>
     * 对于 JDK 19 来说，对 Linux/RISC-V 的支持意味着开发者可以在基于 RISC-V 架构的硬件上运行 Java 应用程序
     */
    private void linuxRISCVPort() {
    }

    /**
     * 创建预分配 hash 集合的方法
     */
    public void addPreallocatedHashes() {
        // Map
        // 其中的参数是预期的元素数量（即可存放个数，超过此数量会进行扩容操作），与构造器中参数不同的是，构造器中参数是初始容量
        // 因此 HashMap.newHashMap(12) = new HashMap(16);
        var hashMap = HashMap.newHashMap(12);
        System.out.println("HashMap: " + hashMap);
        var linkedHashMap = LinkedHashMap.newLinkedHashMap(24);
        System.out.println("LinkedHashMap: " + linkedHashMap);
        var weakHashMap = WeakHashMap.newWeakHashMap(3);
        System.out.println("WeakHashMap: " + weakHashMap);

        // Set
        var hashSet = HashSet.newHashSet(6);
        System.out.println("HashSet: " + hashSet);
        var linkedHashSet = LinkedHashSet.newLinkedHashSet(3);
        System.out.println("LinkedHashSet: " + linkedHashSet);
    }
}