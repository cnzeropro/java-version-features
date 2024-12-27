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
 * @author zero
 */
public class Java15Test {
    Java15 java15 = new Java15();

    @Test
    public void textBlock() {
        java15.textBlock();
    }

    @Test
    public void addMethodForCharSequence() {
        java15.addMethodForCharSequence();
    }

    @Test
    public void hiddenClasses() {
        java15.hiddenClasses();
    }

    @Test
    public void overrideTreeMapMethod() {
        java15.overrideTreeMapMethod();
    }

    @Test
    public void edDsa() {
        java15.edDsa();
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
        System.out.println("Java 15 Test Start...");
    }

    @AfterClass
    public static void destroy() {
        System.out.println("Java 15 Test End");
    }

    @Rule
    public TestWatcher watchman = new TestWatcher() {
        @Override
        protected void starting(Description description) {
            System.out.println("Current test: " + description.getMethodName());
        }
    };
}