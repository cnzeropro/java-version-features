package org.zero;

/**
 * @author Zero (cnzeropro@qq.com)
 * @date 2021/11/17 17:07
 */
public class Java14 {
    /**
     * Switch表达式改进
     * 新形式的开关标签，写成“case X ->”，表示如果标签匹配，则只执行标签右侧的代码，而不用再写break。
     */
    public void switchExpression(int day) {
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
                System.out.println("不存在该星期");
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
            default -> System.out.println("不存在该星期");
        }
    }
}

