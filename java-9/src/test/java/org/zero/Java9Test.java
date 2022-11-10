package org.zero;

import org.junit.Test;

public class Java9Test {
    Java9 java9 = new Java9();

    @Test
    public void privateMethod() {
        java9.privateMethod();
    }

    @Test
    public void diamondOperator() {
        java9.diamondOperator();
    }

    @Test
    public void tryBlock() {
        java9.tryBlock();
    }

    @Test
    public void ofMethod() {
        java9.ofMethod();
    }

    @Test
    public void streamApi() {
        java9.streamApi();
    }
}