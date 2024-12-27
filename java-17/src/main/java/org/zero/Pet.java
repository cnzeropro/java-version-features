package org.zero;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * @author Zero (cnzeropro@qq.com)
 * @date 2021/11/17 19:43
 */
@Getter
@AllArgsConstructor
public sealed class Pet implements Serializable permits Cat, Dog, Pig {
    private String name;
    private Integer age;

    public void sleep() {
        System.out.println(age + "岁的[" + name + "]爱睡觉。这个年纪你这么睡得着！！");
    }
}
