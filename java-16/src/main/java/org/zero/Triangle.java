package org.zero;

import java.io.Serializable;

/**
 * @author Zero (cnzeropro@163.com)
 * @since 2024/12/19
 */
public interface Triangle extends Serializable {
    /**
     * 面积
     * <p>
     * 海伦公式（Heron's formula）：<br>
     * <pre>{@code
     * p = (a+b+c)/2
     * S = √(p(p-a)(p-b)(p-c))
     * }
     * </pre>
     *
     * @return 面积
     */
    default strictfp double area() {
        double p = (this.a() + this.b() + this.c()) / 2.0;
        return Math.sqrt(p * (p - this.a()) * (p - this.b()) * (p - this.c()));
    }

    default void check() {
        if (this.a() <= 0 || this.b() <= 0 || this.c() <= 0) {
            throw new IllegalArgumentException("三角形三条边都必须大于0");
        } else if (this.a() + this.b() <= this.c() || this.a() + this.c() <= this.b() || this.b() + this.c() <= this.a()) {
            throw new IllegalArgumentException("三角形两边之和需大于第三边");
        }
    }

    double a();

    double b();

    double c();
}
