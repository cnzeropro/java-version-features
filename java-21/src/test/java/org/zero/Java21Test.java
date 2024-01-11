package org.zero;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Zero
 * @since 2023/11/10
 */
public class Java21Test {
    Java21 java21 = new Java21();

    @Test
    public void enhanceSwitchGrammar() {
        java21.enhanceSwitchGrammar();
    }

    @Test
    public void enhanceInstanceofKeyword() {
        java21.enhanceInstanceofKeyword();
    }

    @Test
    public void addVirtualThread() {
        java21.addVirtualThread();
    }

    @Test
    public void addSequencedCollections() {
        java21.addSequencedCollections();
    }

    @Test
    public void addMethodsForCharacter() {
        java21.addMethodsForCharacter();
    }

    @Test
    public void emojiInRegEx() {
        java21.emojiInRegEx();
    }

    @Test
    public void addRepeatMethod() {
        java21.addRepeatMethod();
    }
}