package org.zero;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.io.IOException;

public class Java9Test {
    Java9 java9 = new Java9();

    @Test
    public void updateVersioningScheme() {
        java9.updateVersioningScheme();
    }

    @Test
    public void addPrivateMethod() {
        java9.addPrivateMethodInInterface();
    }

    @Test
    public void diamondOperator() {
        java9.upgradeDiamondOperator();
    }

    @Test
    public void tryBlock() {
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
        System.out.println("*************************************************** End ***************************************************\n");
    }

    @BeforeClass
    public static void init() {
        System.out.println("Java 9 Test Start...");
    }

    @AfterClass
    public static void destroy() {
        System.out.println("Java 9 Test End");
    }

    @Rule
    public TestWatcher watchman = new TestWatcher() {
        @Override
        protected void starting(Description description) {
            System.out.println("Current test: " + description.getMethodName());
        }
    };
}