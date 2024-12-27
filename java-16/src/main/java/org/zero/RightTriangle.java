package org.zero;

import java.util.Arrays;

/**
 * @author Zero (cnzeropro@qq.com)
 * @since 2021/11/17 19:21
 */
public record RightTriangle(double a, double b, double c) implements Triangle {
    /**
     * 记录类默认存在全参构造，此处定义是为了调用初始化方法
     */
    public RightTriangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.init();
    }

    @Override
    public strictfp double area() {
        double[] array = {a, b, c};
        Arrays.sort(array);
        return array[0] * array[1] / 2.0;
    }

    private void init() {
        this.check();
        if (a * a + b * b != c * c && a * a + c * c != b * b && b * b + c * c != a * a) {
            throw new IllegalArgumentException("直角三角形需两边平方和等于第三边平方");
        }
    }
}
