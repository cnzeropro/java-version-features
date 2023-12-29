package org.zero;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

/**
 * <a href="https://docs.oracle.com/javase/14/index.html">JDK 14 Documentation</a>
 * <h2>Language Changes</h2>
 * <ol>
 *     <li>【update】改进 Switch 表达式。{@link Java14#improveSwitchExpression()}</li>
 *     <li>【update】增强 instanceof 关键字。（预览）</li>
 *     <li>【new】新增 record 类型声明。（预览）</li>
 *     <li>【new】新增文本块。（预览）</li>
 * </ol>
 * <h2>Changes</h2>
 * <ol>
 *     <li>【new】支持会计币种格式。{@link Java14#supportAccountingCurrencyFormat()}</li>
 * </ol>
 *
 * @author Zero (cnzeropro@qq.com)
 * @since 2021/11/17 17:07
 */
public class Java14 {
    /**
     * Switch 表达式改进。
     * <p>
     * 新形式的开关标签，写成“case X ->”，表示如果标签匹配，则只执行标签右侧的代码，而不用再写 break。
     */
    public void improveSwitchExpression() {
        int day = ThreadLocalRandom.current().nextInt(1, 8);

        System.out.println("传统写法：");
        switch (day) {
            case 1:
                System.out.println("星期一");
                break;
            case 2:
                System.out.println("星期二");
                break;
            case 3:
                System.out.println("星期三");
                break;
            case 4:
                System.out.println("星期四");
                break;
            case 5:
                System.out.println("星期五");
                break;
            case 6:
                System.out.println("星期六");
                break;
            case 7:
                System.out.println("星期日");
                break;
            default:
                throw new IllegalArgumentException("Invalid day");
        }

        System.out.println("新式写法：");
        switch (day) {
            case 1 -> System.out.println("星期一");
            case 2 -> System.out.println("星期二");
            case 3 -> System.out.println("星期三");
            case 4 -> System.out.println("星期四");
            case 5 -> System.out.println("星期五");
            case 6 -> System.out.println("星期六");
            case 7 -> System.out.println("星期日");
            default -> throw new IllegalArgumentException("Invalid day");
        }

        System.out.println("新式写法（接收返回）：");
        String text = switch (day) {
            case 1 -> "星期一";
            case 2 -> "星期二";
            case 3 -> "星期三";
            case 4 -> "星期四";
            case 5 -> "星期五";
            case 6 -> "星期六";
            case 7 -> "星期日";
            default -> throw new IllegalArgumentException("Invalid day");
        };
        System.out.println(text);
    }

    public void supportAccountingCurrencyFormat() {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);
        String format = numberFormat.format(453.62);
        System.out.println("format: " + format);
    }
}

