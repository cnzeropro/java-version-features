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
 * @author Zero (cnzeropro@163.com)
 * @since 2024/12/4
 */
public class Java19Test {
    Java19 java19 = new Java19();

    @Test
    public void testAddPreallocatedHashes() {
        java19.addPreallocatedHashes();
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
        System.out.println("Java 19 Test Start...");
    }

    @AfterClass
    public static void destroy() {
        System.out.println("Java 19 Test End");
    }

    @Rule
    public TestWatcher watchman = new TestWatcher() {
        @Override
        protected void starting(Description description) {
            System.out.println("Current test: " + description.getMethodName());
        }
    };
}