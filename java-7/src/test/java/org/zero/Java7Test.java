package org.zero;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author zero
 * @since 2023/12/26
 */
public class Java7Test {
    Java7 java7 = new Java7();

    @Test
    public void addBinaryLiterals() {
        System.out.println("Current test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
        java7.addBinaryLiterals();
    }

    @Test
    public void addUnderscoresInNumericLiterals() {
        System.out.println("Current test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
        java7.addUnderscoresInNumericLiterals();
    }

    @Test
    public void addStringsInSwitchStatements() {
        System.out.println("Current test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
        java7.addStringsInSwitchStatements();
    }

    @Test
    public void addTypeInference() {
        System.out.println("Current test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
        java7.addTypeInference();
    }

    @Test
    public void improveCompilerWarningsAndErrors() {
        System.out.println("Current test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
        java7.improveCompilerWarningsAndErrors();
    }

    @Test
    public void addTryWithResourcesStatement() {
        System.out.println("Current test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
        java7.addTryWithResourcesStatement();
    }

    @Test
    public void improveCatchingMultipleException() {
        System.out.println("Current test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
        java7.improveCatchingMultipleException();
    }

    @Test
    public void addThreadLocalRandomClass() {
        System.out.println("Current test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
        java7.addThreadLocalRandomClass();
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
        System.out.println("Java 7 Test Start...");
    }

    @AfterClass
    public static void destroy() {
        System.out.println("Java 7 Test End");
        System.out.println("=============================================================================================================================\n");
    }
}