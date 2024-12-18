package org.zero;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

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
    public void recordPattern() {
        java21.recordPattern();
    }

    @Test
    public void virtualThread() {
        java21.virtualThread();
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

    @Before
    public void setUp() {
        System.out.println("************************************************** Start **************************************************");
    }

    @After
    public void tearDown() {
        System.out.println("*************************************************** End ***************************************************\n");
    }

    @BeforeClass
    public static void init() {
        System.out.println("Java 21 Test Start...");
    }

    @AfterClass
    public static void destroy() {
        System.out.println("Java 21 Test End");
    }

    @Rule
    public TestWatcher watchman = new TestWatcher() {
        @Override
        protected void starting(Description description) {
            System.out.println("Current test: " + description.getMethodName());
        }
    };
}