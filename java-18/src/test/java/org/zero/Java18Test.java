package org.zero;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author yufa.wang (yufa.wang@ronganchina.com)
 * @since 2024/1/10
 */
public class Java18Test {
    Java18 java18 = new Java18();

    @Test
    public void changeDefaultCharset() {
        java18.changeDefaultCharset();
    }

    @Test
    public void addInternetAddressResolverSpi() {
        java18.addInternetAddressResolverSpi();
    }
}