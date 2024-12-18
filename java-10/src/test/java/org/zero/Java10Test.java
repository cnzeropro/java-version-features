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
 * @since 2023/12/27
 */
public class Java10Test {
    Java10 java10 = new Java10();

    @Test
    public void introduceVarIdentifier() {
        java10.introduceVarIdentifier();
    }

    @Test
    public void addSummaryInlineTag() {
        java10.addSummaryInlineTag();
    }

    @Test
    public void enhanceOptionalApi() {
        java10.enhanceOptionalApi();
    }

    @Test
    public void addCreatingUnmodifiableCollectionsApi() {
        java10.addCreatingUnmodifiableCollectionsApi();
    }

    @Test
    public void overloadToStringMethod() {
        java10.overloadToStringMethod();
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
        System.out.println("Java 10 Test Start...");
    }

    @AfterClass
    public static void destroy() {
        System.out.println("Java 10 Test End");
    }

    @Rule
    public TestWatcher watchman = new TestWatcher() {
        @Override
        protected void starting(Description description) {
            System.out.println("Current test: " + description.getMethodName());
        }
    };
}