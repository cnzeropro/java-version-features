package org.zero;

import lombok.SneakyThrows;

import java.lang.foreign.Arena;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

/**
 * <a href="https://openjdk.org/projects/jdk/23/">JDK 23</a>
 * <a href="https://docs.oracle.com/en/java/javase/23/">JDK 23 Documentation</a>
 * <h1>Features</h1>
 * <ol>
 *     <li><a href="https://openjdk.org/jeps/467">467</a>：Markdown 文档注释（Markdown Documentation Comments）{@link #introduceMarkdownDoc()}</li>
 *     <li><a href="https://openjdk.org/jeps/471">471</a>：废弃 {@link sun.misc.Unsafe} 中的 Memory-Access 方法（Deprecate the Memory-Access Methods in sun.misc.Unsafe for Removal）{@link #deprecateMemoryAccess()}</li>
 *     <li><a href="https://openjdk.org/jeps/474">474</a>：ZGC 默认为分代模式（ZGC: Generational Mode by Default）{@link #zgcGenerationalMode()}</li>
 * </ol>
 * <ol>
 *     <li><a href="https://openjdk.org/jeps/455">455</a>：Patterns、instanceof 和 switch 中支持基本类型（Primitive Types in Patterns, instanceof, and switch）[首次预览]</li>
 *     <li><a href="https://openjdk.org/jeps/466">466</a>：类文件 API（Class-File API）[第二次预览]</li>
 *     <li><a href="https://openjdk.org/jeps/473">473</a>：流收集器（Stream Gatherers）[第二次预览]</li>
 *     <li><a href="https://openjdk.org/jeps/476">476</a>：模块导入声明（Module Import Declarations）[首次预览]</li>
 *     <li><a href="https://openjdk.org/jeps/477">477</a>：匿名类和实例主方法（Implicitly Declared Classes and Instance Main Methods）[第三次预览]</li>
 *     <li><a href="https://openjdk.org/jeps/480">480</a>：结构化并发（Structured Concurrency）[第三次预览]</li>
 *     <li><a href="https://openjdk.org/jeps/481">481</a>：作用域值（Scoped Values）[第三次预览]</li>
 *     <li><a href="https://openjdk.org/jeps/482">482</a>：灵活的构造函数主体（Flexible Constructor Bodies）[第二次预览]</li>
 * </ol>
 * <ol>
 *     <li><a href="https://openjdk.org/jeps/469">469</a>：向量 API（Vector API）[第八次孵化]</li>
 * </ol>
 *
 * @author Zero (cnzeropro@163.com)
 * @since 2024/12/2
 */
public class Java23 {
    /// # Markdown 文档注释
    /// Java 23 允许使用 Markdown 编写 JavaDoc 文档注释，而不仅仅是使用 HTML 和 JavaDoc @-tag 的混合编写。以下是一些常用元素
    /// ## 表格（table）
    /// | Latin | Greek |
    /// |-------|-------|
    /// | a     | alpha |
    /// | b     | beta  |
    /// | c     | gamma |
    /// ## 列表（list）
    /// - a
    /// - b
    /// - c
    /// ## 链接（link）
    /// - module [java.base/]
    /// - [the `java.base` module][java.base/]
    /// - package [java.util]
    /// - [the `java.util` package][java.util]
    /// - class [String]
    /// - [`String` class][String]
    /// - field [String#CASE_INSENSITIVE_ORDER]
    /// - [`CASE_INSENSITIVE_ORDER` field][String]
    /// - method [String#chars()]
    /// - [`chars` method][String#chars()]
    /// ## 代码块（code）
    /// ```java
    /// public class Example {
    ///     public void example() {
    ///     }
    /// }
    /// ```
    private void introduceMarkdownDoc() {
    }

    /// 废弃 [link sun.misc.Unsafe] 中的 Memory-Access 方法
    ///
    /// @see java.lang.invoke
    /// @see java.lang.foreign
    @SneakyThrows
    public void deprecateMemoryAccess() {
        Person person = new Person(1L, "Tom", 18);
        /*
         * 弃用 Unsafe Memory-Access API
         */
        // Java 23 sun.misc.Unsafe#getUnsafe() 方法变得更加严格，不再允许普通应用程序代码直接获取 Unsafe 实例，因此将抛出 java.lang.SecurityException: Unsafe
        // Unsafe unsafe = Unsafe.getUnsafe();
        // long personAgeAddress = unsafe.objectFieldOffset(Person.class.getDeclaredField("height"));
        // unsafe.putInt(person, personAgeAddress, 50);
        // System.out.println("Height: " + person.getAge());
        // long address = unsafe.allocateMemory(8);
        // unsafe.putLong(address, 0x123456789ABCDEF0L);
        // long num = unsafe.getLong(address);
        // System.out.println(num);
        // unsafe.freeMemory(address);

        /*
         * 使用 java.lang.invoke、java.lang.foreign 相关 API 替代
         */
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        // 私有成员访问
        MethodHandles.Lookup privateLookup = MethodHandles.privateLookupIn(Person.class, lookup);
        VarHandle varHandle = privateLookup.findVarHandle(Person.class, "height", Integer.class);
        varHandle.set(person, 150);
        System.out.println("Height: " + varHandle.get(person));
        boolean exchanged = varHandle.compareAndSet(person, 120, 180);
        System.out.println("Exchanged: " + exchanged);
        System.out.println("New Height: " + varHandle.get(person));

        Arena arena = Arena.ofAuto();
        MemorySegment memorySegment = arena.allocate(MemoryLayout.paddingLayout(8));
        memorySegment.set(ValueLayout.JAVA_LONG, 0, 0x123456789ABCDEF0L);
        System.out.println(memorySegment.get(ValueLayout.JAVA_LONG, 0));
    }

    /// ZGC 默认为分代模式
    ///
    /// 以下是常用的命令行参数
    /// | 参数 | 说明 |
    /// |-------|-------|
    /// | `-XX:+UseZGC` | 使用 ZGC |
    /// | `-XX:+ZGenerational` | 使用分代模式（ZGenerational 选项已弃用警告） |
    /// | `-XX:-ZGenerational` | 使用非分代模式（非分代模式已弃用 和 ZGenerational 选项已弃用警告） |
    private void zgcGenerationalMode() {
    }
}
