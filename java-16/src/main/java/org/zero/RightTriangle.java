package org.zero;

/**
 * @author Zero (cnzeropro@qq.com)
 * @date 2021/11/17 19:24
 */
public class RightTriangle extends Triangle {
    public RightTriangle(double a, double b, double c) {
        super(a, b, c);
        this.init();
    }

    private void init() {
        if (a * a + b * b != c * c && a * a + c * c != b * b && b * b + c * c != a * a) {
            throw new IllegalArgumentException("直角三角形两边平方和需要等于第三边平方");
        }
    }

    @Override
    public String toString() {
        return "RightTriangle[" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                ']';
    }
}
