package org.zero;

import org.junit.Test;

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
    public void printer() {
        java8.printer();
    }

    @Test
    public void streamAPI() {
        java8.streamAPI();
    }

    @Test
    public void optionalClass() {
        java8.optionalClass();
    }

    @Test
    public void dateTimeAPI() {
        java8.dateTimeAPI();
    }

    @Test
    public void base64() {
        java8.base64();
    }
}