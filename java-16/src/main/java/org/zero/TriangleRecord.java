package org.zero;

/**
 * @author Zero (cnzeropro@qq.com)
 * @date 2021/11/17 19:21
 */
public record TriangleRecord(double a, double b, double c) {

    /**
     * 记录类默认存在全参构造，此处定义是为了调用初始化方法
     */
    public TriangleRecord(double a, double b, double c) {
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
}
