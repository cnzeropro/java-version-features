package org.zero;

/**
 * 密封（sealed）类表示一个可扩展的类
 * 但只能通过已知的子类型列表进行扩展，而不能通过其他任意扩展，并且其子类也无法被扩展，所以子类只能是 final 类型
 *
 * @author Zero (cnzeropro@qq.com)
 * @date 2021/11/17 19:43
 */
public sealed class Pet permits Cat, Dog {
    private final String name;
    private final Integer age;

    public Pet(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public void sleep() {
        System.out.println(age + "岁的" + name + "会睡觉");
    }
}
