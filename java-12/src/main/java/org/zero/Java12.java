package org.zero;

import java.lang.constant.ConstantDesc;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * <a href="https://openjdk.org/projects/jdk/12/">JDK 12</a>
 * <a href="https://docs.oracle.com/javase/12/">JDK 12 Documentation</a>
 * <h1>Features</h1>
 * <ol>
 *     <li><a href="https://openjdk.org/jeps/334">334</a>：JVM 常量 API（JVM Constants API）{@link #addJvmConstantApi()}</li>
 *     <li>支持紧凑的数字格式（Support for Compact Number Formatting）{@link #supportCompactNumberFormatting()}</li>
 * </ol>
 * <ol>
 *      <li><a href="https://openjdk.org/jeps/325">325</a>：switch 表达式（Switch Expressions）[首次预览]</li>
 * </ol>
 *
 * @author Zero
 * @since 2019/08/08
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
