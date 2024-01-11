package org.zero;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author zero
 */
public class Java16Test {
    Java16 java16 = new Java16();

    @Test
    public void addRecordClasses() {
        java16.addRecordClasses();
    }

    @Test
    public void upgradeInstanceofKeyword() {
        java16.upgradeInstanceofKeyword();
    }

    @Test
    public void addMethodsForStream() {
        java16.addMethodsForStream();
    }
}