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
 * <a href="https://openjdk.org/projects/jdk/15/">JDK 15</a>
 * <a href="https://docs.oracle.com/javase/15/">JDK 15 Documentation</a>
 * <h1>Features</h1>
 * <ol>
 *     <li><a href="https://openjdk.org/jeps/378">378</a>：文本块（Text Blocks）{@link #introduceTextBlock()}</li>
 *     <li><a href="https://openjdk.org/jeps/371">371</a>：隐式类（Hidden Classes）{@link #introduceHiddenClasses()}</li>
 *     <li>【new】CharSequence 类新增 isEmpty 方法（Added isEmpty Default Method to CharSequence）{@link #addMethodForCharSequence()}</li>
 *     <li>【new】重写 TreeMap 方法并改进性能（Specialized Implementations of TreeMap Methods）{@link #overrideTreeMapMethod()}</li>
 * </ol>
 * <ol>
 *     <li><a href="https://openjdk.org/jeps/375">375</a>：instanceof 模式匹配（Pattern Matching for instanceof）[第二次预览]</li>
 *     <li><a href="https://openjdk.org/jeps/384">384</a>：记录类（Record Classes）[第二次预览]</li>
 *     <li><a href="https://openjdk.org/jeps/360">360</a>：密封类（Sealed Classes）[首次预览]</li>
 * </ol>
 *
 * @author Zero (cnzeropro@qq.com)
 * @since 2021/11/17 19:28
 */
public class Java15 {

    /**
     * 引入文本块写法
     * <p>
     * 主要用于解决 xml、json 等语法书写难以排版的问题
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

    public void addMethodForCharSequence() {
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
        int result = (int) methodHandle.invokeExact("Bob");
        System.out.println("len: " + result);
    }

    /**
     * 重写 TreeMap 方法并改进性能
     * <p>
     * TreeMap 类现在提供了 putIfAbsent、computeIfAbsent、computeIfPresent、compute 和 merge 方法的重写实现并为新的实现提供了性能改进。
     */
    public void overrideTreeMapMethod() {
        var treeMap = new TreeMap<String, Object>();
        treeMap.putIfAbsent("a", 12);
        treeMap.putIfAbsent("c", 28);
        treeMap.putIfAbsent("b", 5);
        System.out.println(treeMap);
    }
}
