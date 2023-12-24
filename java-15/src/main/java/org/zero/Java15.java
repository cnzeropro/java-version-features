package org.zero;

/**
 * 引入文本块 {@link Java15#textBlock()}
 *
 * @author Zero (cnzeropro@qq.com)
 * @date 2021/11/17 19:28
 */
public class Java15 {

    /**
     * 引入文本块写法
     * 解决xml，json等语法书写难以排版的问题
     */
    public void textBlock() {
        // 传统写法
        String str1 = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "   <head>\n" +
                "       <meta charset='utf-8'>\n" +
                "       <title>test</title>\n" +
                "   </head>\n" +
                "   <body>\n" +
                "       <h1>TEST</h1>\n" +
                "   </body>\n" +
                "</html>\n";
        System.out.println("传统多行文本写法：\n" + str1);

        // 文本块写法
        String str2 = """
                <!DOCTYPE html>
                <html>
                    <head>
                        <meta charset="utf-8">
                        <title>test</title>
                    </head>
                    <body>
                        <h1>TEST</h1>
                    </body>
                </html>
                """;
        System.out.println("文本块写法：\n" + str2);
    }
}
