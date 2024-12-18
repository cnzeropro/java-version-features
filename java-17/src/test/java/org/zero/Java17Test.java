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
 * @author Zero (cnzeropro@qq.com)
 * @date 2022/11/10
 */
public class Java17Test {
    Java17 java17 = new Java17();

    @Test
    public void sealedClass() {
        java17.sealedClasses();
    }

    @Test
    public void addHexFormatClass() {
        java17.addHexFormatClass();
    }

    @Test
    public void floatingPoint() {
        java17.floatingPoint();
    }

    @Test
    public void enhancePRNG() {
        java17.enhancePRNG();
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
        System.out.println("Java 17 Test Start...");
    }

    @AfterClass
    public static void destroy() {
        System.out.println("Java 17 Test End");
    }

    @Rule
    public TestWatcher watchman = new TestWatcher() {
        @Override
        protected void starting(Description description) {
            System.out.println("Current test: " + description.getMethodName());
        }
    };
}