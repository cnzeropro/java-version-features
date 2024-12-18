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
 * @since 2024/5/24
 */
public class Java22Test {
    Java22 java22 = new Java22();

    @Test
    public void foreignFunctionAndMemoryApi() {
        java22.foreignFunctionAndMemoryApi();
    }

    @Test
    public void unnamedVariableAndPattern() {
        java22.unnamedVariableAndPattern();
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
        System.out.println("Java 22 Test Start...");
    }

    @AfterClass
    public static void destroy() {
        System.out.println("Java 22 Test End");
    }

    @Rule
    public TestWatcher watchman = new TestWatcher() {
        @Override
        protected void starting(Description description) {
            System.out.println("Current test: " + description.getMethodName());
        }
    };
}