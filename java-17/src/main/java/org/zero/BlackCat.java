package org.zero;

/**
 * @author Zero (cnzeropro@qq.com)
 * @date 2021/11/17 19:47
 */
public class BlackCat extends Cat {
    public BlackCat(String name, Integer age) {
        super(name, age);
    }

    @Override
    public void sleep() {
        System.out.println(this.getAge() + "岁的黑色[" + this.getName() + "]爱睡觉");
    }
}
