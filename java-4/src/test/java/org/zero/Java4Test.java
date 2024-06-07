package org.zero;

import junit.framework.TestCase;

/**
 * @author zero
 * @since 2020/12/26
 */
public class Java4Test extends TestCase {
    Java4 java4 = new Java4();

    protected void setUp() {
        System.out.println("Java 4 Test Start...");
    }

    /**
     * source mode 1.4 不支持注解，因此不使用 junit 4 支持的 annotations，而是使用 junit 3 继承 TestCase 的用法
     * <p>
     * 注意：此用法测试用例要生效，方法名必须以 test 开头
     */
    public void testAssertionFacility() {
        java4.assertionFacility();
    }

    protected void tearDown() {
        System.out.println("Java 4 Test End");
        System.out.println("===================================================================================\n");
    }
}