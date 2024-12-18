package org.zero;

/**
 * 在 JDK 17 及以后版本中，{@code strictfp} 变得多余，因为所有的浮点运算默认都是严格的。
 *
 * @author Zero (cnzeropro@163.com)
 * @since 2024/12/17
 */
public class StrictFpExample {
    public static strictfp double strictAdd(double a, double b) {
        return a + b;
    }

    public static double noStrictAdd(double a, double b) {
        return a + b;
    }
}
