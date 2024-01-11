package org.zero;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author zero
 */
public class Java15Test {
    Java15 java15 = new Java15();

    @Test
    public void introduceTextBlock() {
        java15.introduceTextBlock();
    }

    @Test
    public void addMethodForCharSequence() {
        java15.addMethodForCharSequence();
    }

    @Test
    public void introduceHiddenClasses() {
        java15.introduceHiddenClasses();
    }

    @Test
    public void overrideTreeMapMethod() {
        java15.overrideTreeMapMethod();
    }
}