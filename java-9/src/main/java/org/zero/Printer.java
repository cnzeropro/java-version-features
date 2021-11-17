package org.zero;

/**
 * 抽象，默认，静态方法都是public方法
 *
 * @author Zero
 */
@FunctionalInterface
public interface Printer {
    /**
     * 打印字串函数1
     * <p>
     * 抽象方法，需实现
     *
     * @param msg 待打印的字串
     */
    void print1(String msg);

    /**
     * 打印字串函数2
     * <p>
     * 默认方法，调用该方法需实现接口中的抽象方法
     * <p>
     * 该方法可调用接口所有方法
     *
     * @param msg 待打印的字串
     */
    default void print2(String msg) {
        System.out.println(msg);
        print4("调用接口私有方法（由于无法通过对象名调用，所以在接口默认方法中调用）");
    }

    /**
     * 打印字串函数3
     * <p>
     * 静态方法，可通过接口名调用，调用时无需实现接口抽象方法
     * <p>
     * 该方法只能递归调用自己
     *
     * @param msg 待打印的字串
     */
    static void print3(String msg) {
        System.out.println(msg);
    }

    /**
     * 打印字串函数4
     * <p>
     * 私有方法，不能在本接口外部使用
     * <p>
     * 该方法可调用接口所有方法
     *
     * @param msg 待打印的字串
     */
    private void print4(String msg) {
        System.out.println(msg);
    }
}