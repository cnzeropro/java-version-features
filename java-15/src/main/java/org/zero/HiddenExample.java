package org.zero;

import java.util.Arrays;

/**
 * @author zero
 * @since 2021/12/28
 */
public class HiddenExample {
    private String name;

    public HiddenExample() {
    }

    public HiddenExample(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void sayHello() {
        System.out.println("Hello " + name);
    }

    public static long sum(int... nums) {
        return Arrays.stream(nums).sum();
    }
}
