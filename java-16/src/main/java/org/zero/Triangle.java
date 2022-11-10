package org.zero;

import lombok.Data;

/**
 * @author Zero (cnzeropro@qq.com)
 * @date 2021/11/17 19:23
 */
@Data
public class Triangle {
    private double a;
    private double b;
    private double c;

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
        init();
    }

    private void init() {
        if (a <= 0 || b <= 0 || c <= 0) {
            throw new IllegalArgumentException("三角形三条边都必须大于0");
        } else if (a + b <= c || a + c <= b || b + c <= a) {
            throw new IllegalArgumentException("三角形两边之和需大于第三边");
        }
    }
}