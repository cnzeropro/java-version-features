package org.zero;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author zero
 */
public class Java12Test {
    Java12 java12 = new Java12();

    @Test
    public void addJvmConstantApi() {
        java12.addJvmConstantApi();
    }

    @Test
    public void supportCompactNumberFormatting() {
        java12.supportCompactNumberFormatting();
    }
}