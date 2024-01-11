package org.zero;

import org.junit.Test;

/**
 * @author yufa.wang (yufa.wang@ronganchina.com)
 * @since 2023/12/27
 */
public class Java10Test {
    Java10 java10 = new Java10();

    @Test
    public void introduceVarIdentifier() {
        java10.introduceVarIdentifier();
    }

    @Test
    public void addSummaryInlineTag() {
        java10.addSummaryInlineTag();
    }

    @Test
    public void enhanceOptionalApi() {
        java10.enhanceOptionalApi();
    }

    @Test
    public void addCreatingUnmodifiableCollectionsApi() {
        java10.addCreatingUnmodifiableCollectionsApi();
    }

    @Test
    public void overloadToStringMethod() {
        java10.overloadToStringMethod();
    }
}