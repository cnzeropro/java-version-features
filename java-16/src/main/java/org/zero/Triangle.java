package org.zero;

/**
 * @author Zero (cnzeropro@qq.com)
 * @date 2021/11/17 19:23
 */
public class Triangle {
    private final double a;
    private final double b;
    private final double c;

    Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                '}';
    }
}