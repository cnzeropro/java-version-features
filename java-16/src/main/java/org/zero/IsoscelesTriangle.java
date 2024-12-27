package org.zero;

import java.util.Objects;

/**
 * @author Zero (cnzeropro@qq.com)
 * @since 2021/11/17 19:24
 */
public class IsoscelesTriangle implements Triangle {
    protected double a, b, c;

    public IsoscelesTriangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.init();
    }

    @Override
    public double a() {
        return a;
    }

    @Override
    public double b() {
        return b;
    }

    @Override
    public double c() {
        return c;
    }

    @Override
    public strictfp double area() {
        if (a == b) {
            double h = Math.sqrt(a * a - c * c / 4.0);
            return h * c / 2.0;
        }
        if (a == c) {
            double h = Math.sqrt(a * a - b * b / 4.0);
            return h * b / 2.0;
        }
        double h = Math.sqrt(b * b - a * a / 4.0);
        return h * a / 2.0;

    }

    private void init() {
        this.check();
        if (a != b && a != c && b != c) {
            throw new IllegalArgumentException("等腰三角形需两边相等");
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof IsoscelesTriangle that)) {
            return false;
        }
        return Double.compare(a, that.a) == 0 && Double.compare(b, that.b) == 0 && Double.compare(c, that.c) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }

    @Override
    public String toString() {
        return "IsoscelesTriangle{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                '}';
    }
}
