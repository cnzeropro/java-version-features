package org.zero;

import org.junit.Test;

import javax.script.ScriptException;

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
    public void functionalInterface() {
        java8.functionalInterface();
    }

    @Test
    public void defaultAndStaticMethod() {
        java8.defaultAndStaticMethod();
    }

    @Test
    public void streamApi() {
        java8.streamApi();
    }

    @Test
    public void optionalClass() {
        java8.optionalClass();
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
    public void nashorn() throws ScriptException {
        java8.nashorn();
    }

    @Test
    public void repeatableAnnotation() {
        java8.repeatableAnnotation();
    }


    @Test
    public void parallelArray() {
        java8.parallelArray();
    }
}