package org.zero;

import org.junit.Test;

public class Java9Test {
    Java9 java9 = new Java9();

    @Test
    public void printer() {
        java9.printer();
    }

    @Test
    public void diamondOperatorUpgrade() {
        java9.diamondOperatorUpgrade();
    }

    @Test
    public void tryBlock() {
        java9.tryBlock();
    }

    @Test
    public void of() {
        java9.of();
    }

    @Test
    public void enhanceStreamAPI() {
        java9.enhanceStreamAPI();
    }
}