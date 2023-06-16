package org.zero;

import org.junit.Test;

import java.io.IOException;

public class Java9Test {
    Java9 java9 = new Java9();

    @Test
    public void privateMethod() {
        java9.privateMethod();
    }

    @Test
    public void diamondOperator() {
        java9.diamondOperator();
    }

    @Test
    public void tryBlock() {
        java9.tryBlock();
    }

    @Test
    public void ofMethod() {
        java9.ofMethod();
    }

    @Test
    public void streamApi() {
        java9.streamApi();
    }

    @Test
    public void deprecatedAnnotation() {
        java9.deprecatedAnnotation();
    }

    @Test
    public void optionalApi() {
        java9.optionalApi();
    }

    @Test
    public void processApi() throws IOException {
        java9.processApi();
    }

    @Test
    public void completableFutureApi() {
        java9.completableFutureApi();
    }
}