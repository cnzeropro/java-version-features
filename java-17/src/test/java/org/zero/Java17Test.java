package org.zero;

import org.junit.Test;

/**
 * @author Zero (cnzeropro@qq.com)
 * @date 2022/11/10
 */
public class Java17Test {
    Java17 java17 = new Java17();

    @Test
    public void addSealedClass() {
        java17.addSealedClasses();
    }

    @Test
    public void addHexFormatClass() {
        java17.addHexFormatClass();
    }
}