package org.zero;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author zero
 */
public class Java11Test {
    Java11 java11 = new Java11();

    @Test
    public void enhanceVarIdentifier() {
        java11.enhanceVarIdentifier();
    }

    @Test
    public void addMethodsForString() {
        java11.addMethodsForString();
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