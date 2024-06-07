package org.zero;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author zero
 * @since 2020/12/26
 */
public class Java5Test {
    Java5 java5 = new Java5();

    @Test
    public void introduceGeneric() {
        System.out.println("Current test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
        java5.introduceGeneric();
    }

    @Test
    public void enhanceForLoop() {
        System.out.println("Current test: " + new Exception().getStackTrace()[0].getMethodName());
        java5.enhanceForLoop();
    }

    @Test
    public void addAutoBoxingAndUnboxing() {
        System.out.println("Current test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
        java5.addAutoBoxingAndUnboxing();
    }

    @Test
    public void addTypesafeEnum() {
        System.out.println("Current test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
        java5.addTypesafeEnum();
    }

    @Test
    public void addVarargs() {
        System.out.println("Current test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
        java5.addVarargs();
    }

    @Test
    public void addStaticImport() {
        System.out.println("Current test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
        java5.addStaticImport();
    }

    @Test
    public void introduceAnnotation() {
        System.out.println("Current test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
        java5.introduceAnnotation();
    }

    @Before
    public void setUp() {
        System.out.println("************************************************** Start **************************************************");
    }

    @After
    public void tearDown() {
        System.out.println("*************************************************** End ***************************************************");
    }

    @BeforeClass
    public static void init() {
        System.out.println("Java 5 Test Start...");
    }

    @AfterClass
    public static void destroy() {
        System.out.println("Java 5 Test End");
        System.out.println("=============================================================================================================================\n");
    }
}