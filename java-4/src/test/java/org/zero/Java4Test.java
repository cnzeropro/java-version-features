package org.zero;

/**
 * @author zero
 * @since 2020/12/26
 */
public class Java4Test {
    static Java4 java4 = new Java4();

    /**
     * source mode 1.4 不支持注解
     */
    public static void main(String[] args) {
        java4.addAssertionFacility();
    }
}