package org.zero;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class Java8Test {
    Java8 java8 = new Java8();

    @Test
    public void lambdaExpression() {
        java8.lambdaExpression();
    }

    @Test
    public void methodReference() {
        java8.methodReference();
    }

    @Test
    public void constructorReference() {
        java8.constructorReference();
    }

    @Test
    public void arrayReference() {
        java8.arrayReference();
    }

    @Test
    public void coreFunctionalInterface() {
        java8.coreFunctionalInterface();
    }

    @Test
    public void defaultAndStaticMethod() {
        java8.defaultAndStaticMethod();
    }

    @Test
    public void stream() {
        java8.stream();
    }

    @Test
    public void optional() {
        java8.optional();
    }

    @Test
    public void dateTimeApi() {
        java8.dateTimeApi();
    }

    @Test
    public void base64() {
        java8.base64();
    }

    @Test
    public void nashornScriptEngine() {
        java8.nashornScriptEngine();
    }

    @Test
    public void repeatableAnnotation() {
        java8.repeatableAnnotation();
    }

    @Test
    public void parallelArray() {
        java8.parallelArray();
    }

    @Test
    public void juc() {
        java8.juc();
    }

    @Test
    public void typeInference() {
        java8.typeInference();
    }

    /**
     * Method under test: {@link Java8#typeAnnotation()}
     */
    @Test
    public void typeAnnotation() {
        java8.typeAnnotation();
    }

    /**
     * Method under test: {@link Java8#methodParameterReflection()}
     */
    @Test
    public void methodParameterReflection() {
        java8.methodParameterReflection();
    }

    /**
     * Method under test: {@link Java8#hashMap()}
     */
    @Test
    public void hashMap() {
        java8.hashMap();
    }

    /**
     * Method under test: {@link Java8#unsignedArithmetic()}
     */
    @Test
    public void unsignedArithmetic() {
        java8.unsignedArithmetic();
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
        System.out.println("Java 8 Test Start...");
    }

    @AfterClass
    public static void destroy() {
        System.out.println("Java 8 Test End");
    }

    @Rule
    public TestWatcher watchman = new TestWatcher() {
        @Override
        protected void starting(Description description) {
            System.out.println("Current test: " + description.getMethodName());
        }
    };
}