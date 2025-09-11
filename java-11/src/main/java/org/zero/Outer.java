package org.zero;

import lombok.SneakyThrows;

/**
 * @author Zero (cnzeropro@163.com)
 * @since 2025/9/3
 */
public class Outer {
    private String outerPrivateField = "Hello from Outer's private field!";

    public class Inner {
        public void accessOuterPrivate() {
            System.out.println(outerPrivateField);
        }

        private void innerPrivateMethod() {
            System.out.println("Hello from Inner's private method!");
        }
    }

    /**
     * 在 Java 11 之前，通过反射访问内部类的私有方法会抛出 IllegalAccessException；在 Java 11 及之后，由于 JEP 181，这将正常工作
     */
    @SneakyThrows
    public void tryReflection() {
        Inner.class.getDeclaredMethod("innerPrivateMethod").invoke(new Inner());
    }
}
