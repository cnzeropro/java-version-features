package org.zero;

import org.junit.Test;

/**
 * @author yufa.wang (yufa.wang@ronganchina.com)
 * @since 2023/12/26
 */
public class Java7Test {
    Java7 java7 = new Java7();

    @Test
    public void addBinaryLiterals() {
        java7.addBinaryLiterals();
    }

    @Test
    public void addUnderscoresInNumericLiterals() {
        java7.addUnderscoresInNumericLiterals();
    }

    @Test
    public void addStringsInSwitchStatements() {
        java7.addStringsInSwitchStatements();
    }

    @Test
    public void addTypeInference() {
        java7.addTypeInference();
    }

    @Test
    public void improveCompilerWarningsAndErrors() {
        java7.improveCompilerWarningsAndErrors();
    }

    @Test
    public void addTryWithResourcesStatement() {
        java7.addTryWithResourcesStatement();
    }

    @Test
    public void improveCatchingMultipleException() {
        java7.improveCatchingMultipleException();
    }

    @Test
    public void addThreadLocalRandomClass() {
        java7.addThreadLocalRandomClass();
    }
}