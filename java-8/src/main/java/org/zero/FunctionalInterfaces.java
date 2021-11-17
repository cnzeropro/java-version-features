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
     * @return
     */
    public Student supplierTest(Supplier<Student> supplier) {
        return supplier.get();
    }

    /**
     * @param student
     * @param consumer 消费型接口
     */
    public void consumerTest(Student student, Consumer<Student> consumer) {
        consumer.accept(student);
    }

    /**
     * @param student
     * @param predicate 断言型接口
     * @return
     */
    public boolean predicateTest(Student student, Predicate<Student> predicate) {
        return predicate.test(student);
    }

    /**
     * @param student
     * @param function 函数型接口
     * @return
     */
    public String functionTest(Student student, Function<Student, String> function) {
        return function.apply(student);
    }
}