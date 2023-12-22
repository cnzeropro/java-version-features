package org.zero;

import org.junit.Test;

import javax.script.ScriptException;

public class Java8Test {

    Java8 java8 = new Java8();

    @Test
    public void introduceLambdaExpression() {
        java8.introduceLambdaExpression();
    }

    @Test
    public void addMethodReference() {
        java8.addMethodReference();
    }

    @Test
    public void addConstructorReference() {
        java8.addConstructorReference();
    }

    @Test
    public void addArrayReference() {
        java8.addArrayReference();
    }

    @Test
    public void add4CoreFunctionalInterface() {
        java8.add4CoreFunctionalInterface();
    }

    @Test
    public void addDefaultAndStaticMethod() {
        java8.addDefaultAndStaticMethod();
    }

    @Test
    public void addStreamApi() {
        java8.addStreamApi();
    }

    @Test
    public void addOptionalClass() {
        java8.addOptionalClass();
    }

    @Test
    public void addDateTimeApi() {
        java8.addDateTimeApi();
    }

    @Test
    public void addBase64Class() {
        java8.addBase64Class();
    }

    @Test
    public void addNashornScriptEngine() throws ScriptException {
        java8.addNashornScriptEngine();
    }

    @Test
    public void addRepeatableAnnotation() {
        java8.addRepeatableAnnotation();
    }

    @Test
    public void addArraysApi() {
        java8.addArraysApi();
    }

    @Test
    public void addJucClass() {
        java8.addJucClass();
    }
}