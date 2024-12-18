package org.zero;

import lombok.Data;

/**
 * @author Zero (cnzeropro@163.com)
 * @since 2024/12/6
 */
@Data
public class Person {
    private final Long id;
    private final String name;
    private final Integer age;
    private Integer height;
    private Integer weight;
}
