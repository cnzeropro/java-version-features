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
public class Java11Test {
    Java11 java11 = new Java11();

    @Test
    public void enhanceVarIdentifier() {
        java11.enhanceVarIdentifier();
    }

    @Test
    public void addMethodsForString() {
        java11.addMethodsForString();
    }

    @Test
    public void addHttpClientApi() {
        java11.addHttpClientApi();
    }

    @Test
    public void overloadToArrayMethod() {
        java11.overloadToArrayMethod();
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
        System.out.println("Java 11 Test Start...");
    }

    @AfterClass
    public static void destroy() {
        System.out.println("Java 11 Test End");
    }

    @Rule
    public TestWatcher watchman = new TestWatcher() {
        @Override
        protected void starting(Description description) {
            System.out.println("Current test: " + description.getMethodName());
        }
    };
}