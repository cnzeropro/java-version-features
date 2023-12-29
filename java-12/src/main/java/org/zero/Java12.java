package org.zero;

import java.lang.constant.ConstantDesc;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * <a href="https://docs.oracle.com/javase/12/index.html">JDK 12 Documentation</a>
 * <h2>Language Changes</h2>
 * <ol>
 * </ol>
 * <h2>Changes</h2>
 * <ol>
 *     <li>【update】改进 Switch 表达式。（预览）</li>
 *     <li>【update】支持 Unicode 11。</li>
 *     <li>【new】JVM 常量 API。{@link Java12#addJvmConstantApi()}</li>
 *     <li>【new】支持紧凑的数字格式。{@link Java12#supportCompactNumberFormatting()}</li>
 *     <li>【update】ZGC支持类卸载。
 *     默认情况下，此功能处于启用状态，但可以使用命令行选项 -XX:-ClassUnloading 禁用此功能。</li>
 *     <li>【new】新增命令行标志。
 *     添加了命令行标志 -XX:+ExtensiveErrorReports（默认禁用），以允许在 hs_err<pid>.log 文件中更广泛地报告崩溃的相关信息。</li>
 * </ol>
 *
 * @author Zero
 */
public class Java12 {
    public void addJvmConstantApi() {
        ConstantDesc constantDesc;
    }

    public void supportCompactNumberFormatting() {
        NumberFormat fmt = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.SHORT);
        String n1 = fmt.format(1000);
        String n2 = fmt.format(1000000);

        System.out.println("1000: " + n1);
        System.out.println("1000000: " + n2);
    }
}
