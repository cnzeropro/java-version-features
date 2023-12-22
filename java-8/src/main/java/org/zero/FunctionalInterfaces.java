package org.zero;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 四大函数式接口
 *
 * @author Zero
 */
public class FunctionalInterfaces {
    /**
     * @param supplier 供给型接口
     * @return 生产的数据
     */
    public Student supplierTest(Supplier<Student> supplier) {
        return supplier.get();
    }

    /**
     * @param student  数据
     * @param consumer 消费型接口
     */
    public void consumerTest(Student student, Consumer<Student> consumer) {
        consumer.accept(student);
    }

    /**
     * @param student   数据
     * @param predicate 断言型接口
     * @return 断言结果
     */
    public boolean predicateTest(Student student, Predicate<Student> predicate) {
        return predicate.test(student);
    }

    /**
     * @param student  数据
     * @param function 函数型接口
     * @return 映射结果
     */
    public String functionTest(Student student, Function<Student, String> function) {
        return function.apply(student);
    }
}