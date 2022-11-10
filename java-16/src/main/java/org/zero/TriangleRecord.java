package org.zero;

/**
 * Java是一种面向对象的语言。我们可以创建类来保存数据，并使用封装来控制如何访问和修改该数据。
 * 但创建数据类型非常冗长，即使在最直接的情况下也需要大量代码。
 * 然而记录（record）却是表示数据类的一种简单得多的方法。
 * record 是一种新的类型。虽然它还是类的受限形式，就像枚举一样，但 record 具有名称和状态描述，用于定义记录的组成部分。
 * 比 jdk 16以前更为简单高效，可与Triangle.java进行对比。
 *
 * @author Zero (cnzeropro@qq.com)
 * @date 2021/11/17 19:21
 */
public record TriangleRecord(double a, double b, double c) {
    public TriangleRecord {
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
