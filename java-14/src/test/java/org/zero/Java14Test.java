package org.zero;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Zero (cnzeropro@qq.com)
 * @date 2022/11/10
 */
public class Java14Test {
    Java14 java14 = new Java14();

    @Test
    public void switchExpression() {
        java14.switchExpression(5);
    }
}