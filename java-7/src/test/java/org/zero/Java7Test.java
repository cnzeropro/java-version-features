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
 * @since 2023/12/26
 */
public class Java7Test {
    Java7 java7 = new Java7();

    @Test
    public void binaryLiteral() {
        java7.binaryLiteral();
    }

    @Test
    public void underscoreInNumericLiteral() {
        java7.underscoreInNumericLiteral();
    }

    @Test
    public void stringInSwitchStatement() {
        java7.stringInSwitchStatement();
    }

    @Test
    public void typeInference() {
        java7.typeInference();
    }

    @Test
    public void compilerWarningAndError() {
        java7.compilerWarningAndError();
    }

    @Test
    public void tryWithResource() {
        java7.tryWithResource();
    }

    @Test
    public void catchingMultipleException() {
        java7.catchingMultipleException();
    }

    @Test
    public void threadLocalRandom() {
        java7.threadLocalRandom();
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
        System.out.println("Java 7 Test Start...");
    }

    @AfterClass
    public static void destroy() {
        System.out.println("Java 7 Test End");
    }

    @Rule
    public TestWatcher watchman = new TestWatcher() {
        @Override
        protected void starting(Description description) {
            System.out.println("Current test: " + description.getMethodName());
        }
    };
}