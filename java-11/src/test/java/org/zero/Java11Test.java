package org.zero;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author yufa.wang (yufa.wang@ronganchina.com)
 * @since 2023/12/27
 */
public class Java11Test {
    Java11 java11 = new Java11();

    @Test
    public void enhanceVarKeyWord() {
        java11.enhanceVarKeyWord();
    }

    @Test
    public void addStringMethod() {
        java11.addStringMethod();
    }

    @Test
    public void addHttpClientApi() {
        java11.addHttpClientApi();
    }

    @Test
    public void overloadToArrayMethod() {
        java11.overloadToArrayMethod();
    }
}