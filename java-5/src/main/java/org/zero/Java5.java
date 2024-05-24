package org.zero;

import java.util.Arrays;
import java.util.List;

import static org.zero.Constant.SEASON_SPRING;
import static org.zero.Constant.SEASON_WINTER;

/**
 * @author Zero
 * @since 2018/12/25
 */
public class Java5 {
    /**
     * 引入泛型
     */
    public void addGenerics() {
        // 以前写法
        System.out.println("Before:");
        List list0 = Arrays.asList("aaa", "bbb", "ccc", "ddd");
        for (int i = 0; i < list0.size(); i++) {
            String s = (String) list0.get(i);
            System.out.println(s);
        }

        // 现在写法
        System.out.println("Now:");
        List<String> list1 = Arrays.asList("aaa", "bbb", "ccc", "ddd");
        for (int i = 0; i < list1.size(); i++) {
            String s = list1.get(i);
            System.out.println(s);
        }
    }

    /**
     * 增强 for 循环
     */
    public void enhanceForLoop() {
        String[] a = new String[]{"aaa", "bbb", "ccc", "ddd"};

        // 以前写法
        System.out.println("jdk 1.5 之前：");
        for (int i = 0; i < a.length; i++) {
            String s = a[i];
            System.out.println(s);
        }

        // 现在写法
        System.out.println("jdk 1.5 之后：");
        for (String s : a) {
            System.out.println(s);
        }
    }

    /**
     * 自动装箱、拆箱
     */
    public void addAutoBoxingAndUnboxing() {
        // 以前写法
        Integer integer = Integer.valueOf(45);
        int i = integer.intValue();
        System.out.printf("integer: %d, int: %d%n", integer, i);

        // 现在写法
        Character character = 'c';
        char c = character;
        System.out.printf("character: %c, char: %c%n", character, c);
    }

    /**
     * 类型安全枚举
     */
    public void addTypesafeEnums() {
        // 以前写法
        System.out.println(SEASON_SPRING);

        // 现在写法
        System.out.println(Season.SPRING);
    }

    /**
     * 可变长参数
     */
    public void addVarargs() {
        // 以前的方法定义
        m0(new String[]{"aaa", "bbb"});

        // 现在的方法定义
        m1("aaa", "bbb");
    }

    private void m0(String[] args) {
        System.out.println("args: " + Arrays.toString(args));
    }

    private void m1(String... args) {
        System.out.println("args: " + Arrays.toString(args));
    }

    /**
     * 静态导入
     */
    public void addStaticImport() {
        // 以前写法
        System.out.println(Constant.SEASON_AUTUMN);

        // 现在写法
        System.out.println(SEASON_WINTER);
    }

    /**
     * 引入注解
     */
    @Deprecated
    public void addAnnotations() {

    }
}