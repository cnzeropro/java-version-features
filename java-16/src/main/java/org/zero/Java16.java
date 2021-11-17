package org.zero;

/**
 * @author Zero (cnzeropro@qq.com)
 * @date 2021/11/17 19:19
 */
public class Java16 {

    /**
     * record关键字引入
     */
    public void recordTest() {
        Triangle triangle = new Triangle(9, 7, 6);
        System.out.println(triangle);
        TriangleRecord triangleRecord = new TriangleRecord(4, 8, 7);
        System.out.println(triangleRecord);
    }

    /**
     * instanceof关键字升级
     */
    public void instanceofTest() {
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


