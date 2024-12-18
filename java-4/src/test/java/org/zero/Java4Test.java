package org.zero;

import junit.framework.TestCase;

/**
 * @author zero
 * @since 2020/12/26
 */
public class Java4Test extends TestCase {
    Java4 java4 = new Java4();

    /**
     * source mode 1.4 不支持注解，因此不使用 junit 4 提供的 annotations，而是使用 junit 3 继承自 TestCase 的用法
     * <p>
     * 注意：此用法测试用例要生效，方法名必须以 test 开头
     */
    public void testAssertionFacility() {
        java4.assertionFacility();
    }

    protected void setUp() {
        System.out.println("Java 4 Test Start...");
    }

    protected void tearDown() {
        System.out.println("Java 4 Test End");
    }
}