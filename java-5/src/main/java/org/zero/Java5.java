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
    public void introduceGeneric() {
        System.out.println("Before:");
        // 以前写法
        List list0 = Arrays.asList("aaa", "bbb", "ccc", "ddd");
        for (int i = 0; i < list0.size(); i++) {
            String s = (String) list0.get(i);
            System.out.println(s);
        }

        System.out.println("Now:");
        // 现在写法
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

        System.out.println("Before:");
        // 以前写法
        for (int i = 0; i < a.length; i++) {
            String s = a[i];
            System.out.println(s);
        }

        System.out.println("Now:");
        // 现在写法
        for (String s : a) {
            System.out.println(s);
        }
    }

    /**
     * 自动装箱、拆箱
     */
    public void addAutoBoxingAndUnboxing() {
        System.out.println("Before:");
        // 以前写法
        Integer integer = Integer.valueOf(45);
        int i = integer.intValue();
        System.out.printf("integer: %d, int: %d%n", integer, i);

        System.out.println("Now:");
        // 现在写法
        Character character = 'c';
        char c = character;
        System.out.printf("character: %c, char: %c%n", character, c);
    }

    /**
     * 类型安全枚举
     */
    public void addTypesafeEnum() {
        System.out.println("Before:");
        // 以前写法
        System.out.println(SEASON_SPRING);

        System.out.println("Now:");
        // 现在写法
        System.out.println(Season.SPRING);
    }

    /**
     * 可变长参数
     */
    public void addVarargs() {
        System.out.println("Before:");
        // 以前的方法定义
        m0(new String[]{"aaa", "bbb"});

        System.out.println("Now:");
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
        System.out.println("Before:");
        // 以前写法
        System.out.println(Constant.SEASON_AUTUMN);

        System.out.println("Now:");
        // 现在写法
        System.out.println(SEASON_WINTER);
    }

    /**
     * 引入注解
     */
    @Deprecated
    public void introduceAnnotation() {
        System.out.println("This method has '@Deprecated' annotation");
    }
}