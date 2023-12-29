package org.zero;

import java.util.Set;

/**
 * <a href="https://docs.oracle.com/javase/16/index.html">JDK 16 Documentation</a>
 * <h2>Language Changes</h2>
 * <ol>
 *     <li>【update】增强 instanceof 关键字。{@link Java16#upgradeInstanceofKeyword()}</li>
 *     <li>【new】新增记录类。{@link Java16#addRecordClasses()}</li>
 *     <li>【new】新增密封类。（预览）</li>
 * </ol>
 * <h2>Changes</h2>
 * <ol>
 *     <li>【new】新增 Stream 类方法。{@link Java16#addStreamMethods()} </li>
 * </ol>
 *
 * @author Zero (cnzeropro@qq.com)
 * @since  2021/11/17 19:19
 */
public class Java16 {

    /**
     * 新增 record 类（记录）
     */
    public void addRecordClasses() {
        var triangle = new Triangle(9.1, 7, 6.9);
        System.out.println(triangle.getA());
        System.out.println(triangle);

        var triangleRecord = new TriangleRecord(4, 8.2, 7);
        System.out.println(triangleRecord.a());
        System.out.println(triangleRecord);
    }

    /**
     * 增强 instanceof 关键字
     */
    public void upgradeInstanceofKeyword() {
        Triangle triangle = new RightTriangle(3, 4, 5);

        // 原来写法
        if (triangle instanceof RightTriangle) {
            RightTriangle rightTriangle = (RightTriangle) triangle;
            System.out.println(rightTriangle);
        }

        // 现在写法
        if (triangle instanceof RightTriangle rightTriangle) {
            System.out.println(rightTriangle);
        }
    }

    /**
     * 新增 Stream 类方法
     * <p>
     * 新增方法包括：{@link java.util.stream.Stream#toList}、
     * {@link java.util.stream.Stream#mapMulti}、
     * {@link java.util.stream.Stream#mapMultiToInt}、
     * {@link java.util.stream.Stream#mapMultiToLong}、
     * {@link java.util.stream.Stream#mapMultiToDouble}
     */
    public void addStreamMethods() {
        var list = Set.of("a", "b", "c", "d", "e").stream().toList();
        System.out.println(list);
    }
}


