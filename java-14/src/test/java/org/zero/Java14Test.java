package org.zero;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author zero
 */
public class Java14Test {
    Java14 java14 = new Java14();

    @Test
    public void improveSwitchExpression() {
        java14.improveSwitchExpression();
    }

    @Test
    public void supportAccountingCurrencyFormat() {
        java14.supportAccountingCurrencyFormat();
    }
}