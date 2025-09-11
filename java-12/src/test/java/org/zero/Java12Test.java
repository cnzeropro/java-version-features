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
public class Java12Test {
    Java12 java12 = new Java12();

    @Test
    public void jvmConstantApi() {
        java12.jvmConstantApi();
    }

    @Test
    public void supportCompactNumberFormatting() {
        java12.supportCompactNumberFormatting();
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
        System.out.println("Java 12 Test Start...");
    }

    @AfterClass
    public static void destroy() {
        System.out.println("Java 12 Test End");
    }

    @Rule
    public TestWatcher watchman = new TestWatcher() {
        @Override
        protected void starting(Description description) {
            System.out.println("Current test: " + description.getMethodName());
        }
    };
}