package org.zero;

import lombok.SneakyThrows;

import java.io.InputStream;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.TreeMap;

/**
 * <a href="https://docs.oracle.com/javase/15/index.html">JDK 15 Documentation</a>
 * <h2>Language Changes</h2>
 * <ol>
 *     <li>【new】引入文本块。{@link Java15#introduceTextBlock()}</li>
 *     <li>【update】增强 instanceof 关键字。（预览）</li>
 *     <li>【new】新增记录类。（预览）</li>
 *     <li>【new】新增密封类。（预览）</li>
 * </ol>
 * <h2>Changes</h2>
 * <ol>
 *     <li>【update】支持 Unicode 13.0。</li>
 *     <li>【new】新增 CharSequence 类方法。{@link Java15#addCharSequenceMethod()}</li>
 *     <li>【new】引入隐式类。{@link Java15#introduceHiddenClasses()}</li>
 *     <li>【new】重写 TreeMap 方法并改进性能。{@link Java15#overrideTreeMapMethod()}</li>
 * </ol>
 *
 * @author Zero (cnzeropro@qq.com)
 * @since 2021/11/17 19:28
 */
public class Java15 {

    /**
     * 引入文本块写法
     * <p>
     * 解决 xml、json 等语法书写难以排版的问题
     */
    public void introduceTextBlock() {
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

    public void addCharSequenceMethod() {
        CharSequence charSequence = "";
        System.out.println("CharSequence is empty: " + charSequence.isEmpty());
    }

    @SneakyThrows
    public void introduceHiddenClasses() {
        Class<?> clazz = HiddenClass.class;
        String className = clazz.getName();
        String classPath = className.replace('.', '/') + ".class";
        InputStream classStream = clazz.getClassLoader()
                .getResourceAsStream(classPath);
        byte[] classBytes = {};
        if (Objects.nonNull(classStream)) {
            try (classStream) {
                classBytes = classStream.readAllBytes();
            }
        }

        Class<?> proxyClass = MethodHandles.lookup()
                .defineHiddenClass(
                        classBytes,
                        true,
                        MethodHandles.Lookup.ClassOption.NESTMATE)
                .lookupClass();

        System.out.println("类名：" + proxyClass.getName());
        System.out.println("方法：");
        for (Method method : proxyClass.getDeclaredMethods()) {
            System.out.println(method.getName());
        }
        System.out.println("调用sayHello方法：");
        MethodHandle methodHandle = MethodHandles.lookup()
                .findStatic(proxyClass, "sayHello", MethodType.methodType(int.class, String.class));
        Object result = methodHandle.invokeExact("Bob");
        System.out.println("len: " + result);
    }

    /**
     * 重写 TreeMap 方法并改进性能
     * <p>
     * TreeMap 类现在提供了 putIfAbsent、computeIfAbsent、computeIfPresent、compute 和 merge 方法的重写实现并为新的实现提供了性能改进。
     */
    public void overrideTreeMapMethod() {
        TreeMap<String, Object> treeMap = new TreeMap<>();
    }
}
