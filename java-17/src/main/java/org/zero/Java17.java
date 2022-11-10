package org.zero;

/**
 * @author Zero (cnzeropro@qq.com)
 * @date 2021/11/17 19:41
 */
public class Java17 {
    /**
     * 新增sealed（密封）类
     */
    public void sealedClass() {
        Pet pet1 = new Cat("喵喵", 3);
        pet1.sleep();

        Pet pet2 = new Dog("旺旺", 5);
        pet2.sleep();
    }
}
