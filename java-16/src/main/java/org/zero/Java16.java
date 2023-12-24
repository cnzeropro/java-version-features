package org.zero;

/**
 * 新增record类 {@link Java16#record()}
 * <p>
 * instanceof关键字升级 {@link Java16#instanceofKeyword()}
 *
 * @author Zero (cnzeropro@qq.com)
 * @date 2021/11/17 19:19
 */
public class Java16 {

    /**
     * 新增record（记录）
     */
    public void record() {
        Triangle triangle = new Triangle(9, 7, 6);
        System.out.println(triangle);
        TriangleRecord triangleRecord = new TriangleRecord(4, 8, 7);
        System.out.println(triangleRecord);
    }

    /**
     * instanceof关键字升级
     */
    public void instanceofKeyword() {
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
}


