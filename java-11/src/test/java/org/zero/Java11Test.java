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
 * @since 2025/9/11
 */
public class Java11Test {
    Java11 java11 = new Java11();

    @Test
    public void nestBasedAccessControl() {
        java11.nestBasedAccessControl();
    }

    @Test
    public void httpClientApi() {
        java11.httpClientApi();
    }

    @Test
    public void curve25519AndCurve448KeyAgreement() {
        java11.curve25519AndCurve448KeyAgreement();
    }

    @Test
    public void unicode10() {
        java11.unicode10();
    }

    @Test
    public void flightRecorder() {
        java11.flightRecorder();
    }

    @Test
    public void chaCha20AndPoly1305CryptographicAlgorithm() {
        java11.chaCha20AndPoly1305CryptographicAlgorithm();
    }

    @Test
    public void lambdaParameterLocalVariableSyntax() {
        java11.lambdaParameterLocalVariableSyntax();
    }

    @Test
    public void addMethodsForString() {
        java11.addMethodsForString();
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