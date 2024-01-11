package org.zero;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

/**
 * <a href="https://docs.oracle.com/javase/14">JDK 14 Documentation</a>
 * <h2>Language Features</h2>
 * <ol>
 *     <li>【update】switch 表达式（Switch Expressions）{@link Java14#improveSwitchExpression()}</li>
 * </ol>
 *
 * <h2>Previews and Incubator</h2>
 * <ol>
 *     <li>【update】instanceof 模式匹配（Pattern Matching for the instanceof Operator）（首次预览）</li>
 *     <li>【new】记录类（Records）（首次预览）</li>
 *     <li>【new】文本块（Text Blocks）（第二次预览）</li>
 * </ol>
 *
 * <h2>Libraries Improvements</h2>
 * <ol>
 *     <li>【new】支持会计币种格式（Accounting Currency Format Support）{@link Java14#supportAccountingCurrencyFormat()}</li>
 * </ol>
 *
 * <h2>Changes</h2>
 * <ol>
 * </ol>
 *
 * @author Zero (cnzeropro@qq.com)
 * @since 2021/11/17 17:07
 */
public class Java14 {
    /**
     * switch 表达式改进
     * <p>
     * 新形式的开关标签，写成“case X ->”，表示如果标签匹配，则只执行标签右侧的代码，而不用再写 break。
     */
    public void improveSwitchExpression() {
        int day = ThreadLocalRandom.current().nextInt(1, 8);

        /*
         * Switch Statement（switch 语句）
         */
        // 传统写法
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
        // switch 语句通过 yield 关键字返回值
        System.out.println("新式写法（接收返回）：");
        String text0 = switch (day) {
            case 1:
                yield "星期一";
            case 2:
                yield "星期二";
            case 3:
                yield "星期三";
            case 4:
                yield "星期四";
            case 5:
                yield "星期五";
            case 6:
                yield "星期六";
            case 7:
                yield "星期日";
            default:
                throw new IllegalArgumentException("Invalid day");
        };
        System.out.println(text0);

        /*
         * Switch Expression（switch 表达式）
         */
        // switch 表达式
        System.out.println("新式写法（使用 switch 表达式）：");
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
        // 使用 switch 表达式并直接接收返回
        System.out.println("新式写法（使用 switch 表达式并直接接收返回）：");
        String text1 = switch (day) {
            case 1 -> "星期一";
            case 2 -> "星期二";
            case 3 -> "星期三";
            case 4 -> "星期四";
            case 5 -> "星期五";
            case 6 -> "星期六";
            case 7 -> "星期日";
            default -> throw new IllegalArgumentException("Invalid day");
        };
        System.out.println(text1);
        // 使用 switch 表达式并进行某些操作后再接收返回
        System.out.println("新式写法（使用 switch 表达式并进行某些操作后再接收返回）：");
        String text2 = switch (day) {
            case 1 -> {
                System.out.println("Monday");
                yield "星期一";
            }
            case 2 -> {
                System.out.println("Tuesday");
                yield "星期二";
            }
            case 3 -> {
                System.out.println("Wednesday");
                yield "星期三";
            }
            case 4 -> {
                System.out.println("Thursday");
                yield "星期四";
            }
            case 5 -> {
                System.out.println("Friday");
                yield "星期五";
            }
            case 6 -> {
                System.out.println("Saturday");
                yield "星期六";
            }
            case 7 -> {
                System.out.println("Sunday");
                yield "星期日";
            }
            default -> throw new IllegalArgumentException("Invalid day");
        };
        System.out.println(text2);
    }

    public void supportAccountingCurrencyFormat() {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);
        String format = numberFormat.format(453.62);
        System.out.println("format: " + format);
    }
}

