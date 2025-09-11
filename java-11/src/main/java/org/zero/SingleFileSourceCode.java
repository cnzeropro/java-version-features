package org.zero;

/**
 * @author Zero (cnzeropro@163.com)
 * @since 2025/9/10
 */
public class SingleFileSourceCode {
    static class B {
        public static void main(String[] args) {
            // 这个会执行，因为 B 是第一个类
            System.out.println("B's main");
        }
    }

    public static class A {
        public static void main(String[] args) {
            System.out.println("A's main");
        }
    }
}
