package org.zero;

/**
 * @author Zero (cnzeropro@qq.com)
 * @date 2021/11/17 19:28
 */
public class Java15 {

    /**
     * 引入文本块写法
     * 解决xml，json等语法书写难以排版的问题
     */
    public void textBlockTest() {
        // 传统写法
        String str1 = "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "<meta charset='utf-8'>" +
                "<title></title>" +
                "</head>" +
                "<body>" +
                "</body>" +
                "</html>";
        System.out.println("传统多行文本写法：" + str1);

        // 文本块写法
        String str2 = """
                <!DOCTYPE html>
                <html>
                    <head>
                        <meta charset="utf-8">
                        <title></title>
                    </head>
                    <body>
                    </body>
                </html>
                   """;
        System.out.println("文本块写法：" + str2);
    }
}
