package org.zero;

/**
 * @author Zero
 */
public class Java11 {

    /**
     * 增加了许多字符串类自带方法
     */
    public void strTest() {
        String str = "  i love java!   ";
        //判断字符串是否空白
        boolean isBlank = str.isBlank();
        //去除开头和结尾空白
        String result1 = str.strip();
        //去除首部空白
        String result2 = str.stripLeading();
        //去除尾部空白
        String result3 = str.stripTrailing();
        //复制字符串x遍
        String copiedStr = str.repeat(2);
        //行数统计
        long lineCount = str.lines().count();

        System.out.println("原始字串：" + str);
        System.out.println("是否空白：" + isBlank);
        System.out.println("去除开头和结尾空白：" + result1);
        System.out.println("去除首部空白：" + result2);
        System.out.println("去除尾部空白：" + result3);
        System.out.println("复制两遍字符串：" + copiedStr);
        System.out.println("行数：" + lineCount);
    }
}
