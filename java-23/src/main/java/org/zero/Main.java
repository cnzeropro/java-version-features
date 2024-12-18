package org.zero;

/**
 * main 方法选择的优先顺序是 static 的优于非 static 的，然后是有 args 的优于没有 args 的
 *
 * @author Zero (cnzeropro@163.com)
 * @since 2024/12/2
 */
public class Main {
    // public static void main(String[] args) {
    //     System.out.println("public static main with args (traditional)");
    //     init();
    // }

    // public static void main() {
    //     System.out.println("public static main without args");
    //     init();
    // }

    // static void main(String[] args) {
    //     System.out.println("static main with args");
    //     init();
    // }

    // static void main() {
    //     System.out.println("static main without args");
    //     init();
    // }

    // void main(String[] args) {
    //     System.out.println("main with args");
    //     init();
    // }

    void main() {
        System.out.println("main without args");
        init();
    }

    public static void init() {
        System.out.println("Hello World");
    }

    public String name = "A";

    public void m1(int i) {
        System.out.printf("%s.m1: %d%n", name, i);
    }

    public void m2(int i) {
        System.out.printf("%s.m2: %d%n", name, i);
    }
}
