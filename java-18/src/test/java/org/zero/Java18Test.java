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
 * @author yufa.wang (yufa.wang@ronganchina.com)
 * @since 2024/1/10
 */
public class Java18Test {
    Java18 java18 = new Java18();

    @Test
    public void changeDefaultCharset() {
        java18.changeDefaultCharset();
    }

    @Test
    public void addInternetAddressResolverSpi() {
        java18.addInternetAddressResolverSpi();
    }

    @Test
    public void reimplementCoreReflection() {
        java18.reimplementCoreReflection();
    }

    @Test
    public void deprecateFinalization() {
        java18.deprecateFinalization();
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
        System.out.println("Java 18 Test Start...");
    }

    @AfterClass
    public static void destroy() {
        System.out.println("Java 18 Test End");
    }

    @Rule
    public TestWatcher watchman = new TestWatcher() {
        @Override
        protected void starting(Description description) {
            System.out.println("Current test: " + description.getMethodName());
        }
    };
}