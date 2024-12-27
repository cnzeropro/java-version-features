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
public class Java16Test {
    Java16 java16 = new Java16();

    @Test
    public void recordClass() {
        java16.recordClass();
    }

    @Test
    public void instanceofPatternMatching() {
        java16.instanceofPatternMatching();
    }

    @Test
    public void addMethodsForStream() {
        java16.addMethodsForStream();
    }

    @Test
    public void warningForValueBasedClass(){
        java16.warningForValueBasedClass();
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
        System.out.println("Java 16 Test Start...");
    }

    @AfterClass
    public static void destroy() {
        System.out.println("Java 16 Test End");
    }

    @Rule
    public TestWatcher watchman = new TestWatcher() {
        @Override
        protected void starting(Description description) {
            System.out.println("Current test: " + description.getMethodName());
        }
    };
}