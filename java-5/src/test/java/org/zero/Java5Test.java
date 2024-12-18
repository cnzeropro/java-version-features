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
 * @since 2020/12/26
 */
public class Java5Test {
    Java5 java5 = new Java5();

    @Test
    public void generic() {
        java5.generic();
    }

    @Test
    public void enhanceForLoop() {
        java5.enhanceForLoop();
    }

    @Test
    public void autoBoxingAndUnboxing() {
        java5.autoBoxingAndUnboxing();
    }

    @Test
    public void typesafeEnum() {
        java5.typesafeEnum();
    }

    @Test
    public void vararg() {
        java5.vararg();
    }

    @Test
    public void staticImport() {
        java5.staticImport();
    }

    @Test
    public void annotation() {
        java5.annotation();
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
        System.out.println("Java 5 Test Start...");
    }

    @AfterClass
    public static void destroy() {
        System.out.println("Java 5 Test End");
    }

    @Rule
    public TestWatcher watchman = new TestWatcher() {
        @Override
        protected void starting(Description description) {
            System.out.println("Current test: " + description.getMethodName());
        }
    };
}