package org.zero;

import org.junit.Test;

/**
 * @author Zero (cnzeropro@qq.com)
 * @date 2022/11/10
 */
public class Java11Test {
    Java11 java11 = new Java11();

    @Test
    public void lambdaWithVar() {
        java11.lambdaWithVar();
    }

    @Test
    public void stringApi() {
        java11.stringApi();
    }

    @Test
    public void httpClientModule() throws Exception {
        java11.httpClientModule();
    }
}