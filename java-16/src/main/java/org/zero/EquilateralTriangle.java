package org.zero;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author Zero (cnzeropro@163.com)
 * @since 2024/12/19
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class EquilateralTriangle extends IsoscelesTriangle {
    public EquilateralTriangle(double a, double b, double c) {
        super(a, b, c);
        this.init();
    }

    @Override
    public strictfp double area() {
        return Math.sqrt(3.0) * a * a / 4.0;
    }

    private void init() {
        if (a != b || a != c) {
            throw new IllegalArgumentException("等边三角形需三边相等");
        }
    }
}
