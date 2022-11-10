package org.zero;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Zero (cnzeropro@qq.com)
 * @date 2022/11/10
 */
public class Java17Test {
    Java17 java17 = new Java17();

    @Test
    public void sealedClass() {
        java17.sealedClass();
    }
}