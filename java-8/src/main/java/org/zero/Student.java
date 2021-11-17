package org.zero;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 测试用的数据类
 *
 * @author Zero
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private String id;
    private String name;
    private Character sex;
    private Integer age;
    private Double score;
}