package org.zero;

/**
 * 函数式接口(Functional Interface)有且仅有一个抽象方法，但是可以有多个非抽象方法。
 * 可用@FunctionalInterface注解标明
 *
 * @author Zero
 */
@FunctionalInterface
public interface Printer {
    /**
     * 打印字串函数1
     * 抽象方法（需实现）
     *
     * @param msg 待打印的字串
     */
    void print1(String msg);

    /**
     * 打印字串函数2
     * 预置方法
     * 调用该方法需实现接口中的抽象方法
     *
     * @param msg 待打印的字串
     */
    default void print2(String msg) {
        System.out.println(msg);
    }

    /**
     * 打印字串函数3
     * 静态方法
     * 可通过接口名调用，无需实现接口抽象方法
     *
     * @param msg 待打印的字串
     */
    static void print3(String msg) {
        System.out.println(msg);
    }
}