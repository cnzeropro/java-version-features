package org.zero;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

public class Java9Test {
    Java9 java9 = new Java9();

    @Test
    public void updateVersioningScheme() {
        System.out.println("Current test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
        java9.updateVersioningScheme();
    }

    @Test
    public void addPrivateMethod() {
        System.out.println("Current test: " + new Exception().getStackTrace()[0].getMethodName());
        java9.addPrivateMethodInInterface();
    }

    @Test
    public void diamondOperator() {
        System.out.println("Current test: " + new Object() {
        }.getClass().getEnclosingMethod().getName());
        java9.upgradeDiamondOperator();
    }

    @Test
    public void tryBlock() {
        StackWalker.getInstance().walk(frames -> frames
                        .findFirst()
                        .map(StackWalker.StackFrame::getMethodName))
                .ifPresent(s -> System.out.println("Current test: " + s));
        java9.improveTryWithResourcesStatement();
    }

    @Test
    public void ofMethod() {
        java9.addOfMethod();
    }

    @Test
    public void streamApi() {
        java9.enhanceStreamApi();
    }

    @Test
    public void deprecatedAnnotation() {
        java9.improveDeprecatedAnnotation();
    }

    @Test
    public void introduceDeprecationWarning() {
        java9.introduceDeprecationWarning();
    }

    @Test
    public void optionalApi() {
        java9.enhanceOptionalApi();
    }

    @Test
    public void processApi() throws IOException {
        java9.enhanceProcessApi();
    }

    @Test
    public void completableFutureApi() {
        java9.enhanceCompletableFutureApi();
    }

    @Test
    public void enhanceSafeVarargsAnnotation() {
        java9.enhanceSafeVarargsAnnotation();
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
        System.out.println("Java 9 Test Start...");
    }

    @AfterClass
    public static void destroy() {
        System.out.println("Java 9 Test End");
        System.out.println("=============================================================================================================================\n");
    }
}