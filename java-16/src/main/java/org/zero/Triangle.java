package org.zero;

import java.util.Objects;

public class Triangle {
    protected double a;
    protected double b;
    protected double c;

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.init();
    }

    private void init() {
        if (a <= 0 || b <= 0 || c <= 0) {
            throw new IllegalArgumentException("三角形三条边都必须大于0");
        } else if (a + b <= c || a + c <= b || b + c <= a) {
            throw new IllegalArgumentException("三角形两边之和需大于第三边");
        }
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Triangle triangle = (Triangle) o;
        return Double.compare(getA(), triangle.getA()) == 0 && Double.compare(getB(), triangle.getB()) == 0 && Double.compare(getC(), triangle.getC()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getA(), getB(), getC());
    }

    @Override
    public String toString() {
        return "Triangle[" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                ']';
    }
}