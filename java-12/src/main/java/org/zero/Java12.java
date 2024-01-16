package org.zero;

import java.lang.constant.ConstantDesc;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * <a href="https://docs.oracle.com/javase/12">JDK 12 Documentation</a>
 * <h2>Language Features</h2>
 * <ol>
 * </ol>
 *
 * <h2>Previews and Incubator</h2>
 * <ol>
 *      <li>【update】switch 表达式（Switch Expressions）（首次预览）</li>
 * </ol>
 *
 * <h2>Libraries Improvements</h2>
 * <ol>
 *     <li>【new】JVM 常量 API（JVM Constants API）{@link Java12#addJvmConstantApi()}</li>
 *     <li>【new】支持紧凑的数字格式（Support for Compact Number Formatting）{@link Java12#supportCompactNumberFormatting()}</li>
 * </ol>
 *
 * <h2>Changes</h2>
 * <ol>
 *     <li>【update】支持 Unicode 11（Support for Unicode 11）</li>
 *     <li>【update】ZGC支持类卸载（ZGC Concurrent Class Unloading）。
 *     默认情况下，此功能处于启用状态，但可以使用命令行选项 -XX:-ClassUnloading 禁用此功能。</li>
 *     <li>【new】增添新的命令行标志。
 *     <ul>
 *         <li>-XX:+ExtensiveErrorReports（默认禁用）：以允许在 hs_err<pid>.log 文件中更广泛地报告崩溃的相关信息。</li>
 *     </ul>
 *     </li>
 * </ol>
 *
 * @author Zero
 */
public class Java12 {
    public void addJvmConstantApi() {
        ConstantDesc constantDesc = Integer.valueOf(123);
        System.out.println(constantDesc);
    }

    public void supportCompactNumberFormatting() {
        NumberFormat fmt = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.SHORT);
        String n1 = fmt.format(1000);
        String n2 = fmt.format(1_000_000);
        String n3 = fmt.format(100_000);

        System.out.println("1000: " + n1);
        System.out.println("1000000: " + n2);
        System.out.println("100000: " + n3);
    }
}
