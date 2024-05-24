package org.zero;

import junit.framework.TestCase;

/**
 * @author zero
 * @since 2020/12/26
 */
public class Java4Test extends TestCase {
    Java4 java4 = new Java4();

    protected void setUp() {
    }

    /**
     * source mode 1.4 不支持注解，因此不使用 junit annotations
     */
    public void addAssertionFacility() {
        java4.addAssertionFacility();
    }

    protected void tearDown() {
    }
}