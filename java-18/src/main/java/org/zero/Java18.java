package org.zero;

import java.net.spi.InetAddressResolverProvider;
import java.nio.charset.Charset;
import java.util.List;
import java.util.ServiceLoader;

/**
 * <a href="https://docs.oracle.com/javase/18">JDK 18 Documentation</a>
 * <h2>Language Features</h2>
 * <ol>
 * </ol>
 *
 * <h2>Previews and Incubator</h2>
 * <ol>
 *     <li>【update】switch 模式匹配（Pattern Matching for switch Expressions and Statements）（第二次预览）</li>
 * </ol>
 *
 * <h2>Libraries Improvements</h2>
 * <ol>
 *     <li>【new】引入互联网地址解析的 SPI（Internet-Address Resolution SPI）{@link Java18#addInternetAddressResolverSpi()}</li>
 * </ol>
 *
 * <h2>Changes</h2>
 * <ol>
 *     <li>【update】默认字符集为 UTF-8（UTF-8 by Default）{@link Java18#changeDefaultCharset()}</li>
 * </ol>
 *
 * @author Zero
 * @since 2022/08/30
 */
public class Java18 {
    public void changeDefaultCharset() {
        Charset charset = Charset.defaultCharset();
        System.out.println(charset);
    }

    /**
     * 互联网地址解析的 SPI
     * <p>
     * 引入用于主机名和地址解析的服务提供程序接口（SPI），以便 java.net.InetAddress 可以使用平台内置解析器以外的解析器。
     */
    public void addInternetAddressResolverSpi() {
        ServiceLoader<InetAddressResolverProvider> serviceLoader = ServiceLoader.load(InetAddressResolverProvider.class);
        List<ServiceLoader.Provider<InetAddressResolverProvider>> providers = serviceLoader.stream().toList();
        System.out.println(providers);
    }
}