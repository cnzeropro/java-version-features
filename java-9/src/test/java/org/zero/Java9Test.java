package org.zero;

import org.junit.Test;

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
}