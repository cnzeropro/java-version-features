package org.zero;

import java.nio.charset.Charset;

/**
 * <a href="https://docs.oracle.com/javase/18/index.html">JDK 18 Documentation</a>
 * <h2>Language Changes</h2>
 * <ol>
 *     <li>【update】增强 switch 表达式。（第二次预览）</li>
 * </ol>
 * <h2>Changes</h2>
 * <ol>
 *     <li>【update】字符集默认为 UTF-8。{@link Java18#changeDefaultCharset()}</li>
 *     <li>【new】引入互联网地址解析的 SPI。{@link Java18#addInternetAddressResolutionSpi()}</li>
 * </ol>
 *
 * @author @author Zero
 * @since 2018/12/25
 */
public class Java18 {
    public void changeDefaultCharset() {
        Charset charset = Charset.defaultCharset();
        System.out.println(charset);
    }

    /**
     * 引入互联网地址解析的 SPI
     * <p>
     * 引入用于主机名和地址解析的服务提供程序接口 （SPI），以便 java.net.InetAddress 可以使用平台内置解析器以外的解析器。
     */
    public void addInternetAddressResolutionSpi() {

    }
}