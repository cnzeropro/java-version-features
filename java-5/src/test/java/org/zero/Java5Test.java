package org.zero;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author zero
 * @since 2020/12/26
 */
public class Java5Test {
    Java5 java5 = new Java5();

    @Test
    public void addGenerics() {
        java5.addGenerics();
    }

    @Test
    public void enhanceForLoop() {
        java5.enhanceForLoop();
    }

    @Test
    public void addAutoBoxingAndUnboxing() {
        java5.addAutoBoxingAndUnboxing();
    }

    @Test
    public void addTypesafeEnums() {
        java5.addTypesafeEnums();
    }

    @Test
    public void addVarargs() {
        java5.addVarargs();
    }

    @Test
    public void addStaticImport() {
        java5.addStaticImport();
    }

    @Test
    public void addAnnotations() {
        java5.addAnnotations();
    }
}