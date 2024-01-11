package org.zero;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

/**
 * <a href="https://docs.oracle.com/javase/16">JDK 16 Documentation</a>
 * <h2>Language Features</h2>
 * <ol>
 *     <li>【update】instanceof 模式匹配（Pattern Matching for instanceof）{@link Java16#upgradeInstanceofKeyword()}</li>
 *     <li>【new】记录类（Record Classes）{@link Java16#addRecordClasses()}</li>
 * </ol>
 *
 * <h2>Previews and Incubator</h2>
 * <ol>
 *     <li>【new】密封类（Sealed Classes）（第二次预览）</li>
 * </ol>
 *
 * <h2>Libraries Improvements</h2>
 * <ol>
 *     <li>【new】stream 类新增方法。{@link Java16#addMethodsForStream()} </li>
 * </ol>
 *
 * <h2>Changes</h2>
 * <ol>
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
}


