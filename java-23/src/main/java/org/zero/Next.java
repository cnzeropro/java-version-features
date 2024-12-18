package org.zero;

import jdk.incubator.vector.IntVector;
import jdk.incubator.vector.VectorSpecies;
import lombok.SneakyThrows;

import java.lang.classfile.ClassFile;
import java.lang.classfile.ClassModel;
import java.lang.classfile.Label;
import java.lang.constant.ClassDesc;
import java.lang.constant.ConstantDescs;
import java.lang.constant.MethodTypeDesc;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.StructuredTaskScope;
import java.util.stream.Gatherers;
import java.util.stream.Stream;

/**
 * @author Zero (cnzeropro@163.com)
 * @since 2024/12/2
 */
public class Next {
    /**
     * 允许在构造函数的 super(...) 调用之前执行语句
     */
    public void statementsBeforeSuper() {
        new A();
    }

    private static class A {
        public A() {
            System.out.println("Before Super");
            super();
            System.out.println("After Super");
        }
    }

    /**
     * 类文件 API
     */
    @SneakyThrows
    public void classFileApi() {
        String className = "ClassFileApiCreatedTest";
        ClassFile classFile = ClassFile.of();
        // 构建类
        byte[] bytes = classFile.build(ClassDesc.of(className),
                classBuilder -> {
                    classBuilder.withMethod("m1",
                            MethodTypeDesc.of(ConstantDescs.CD_void, ConstantDescs.CD_boolean, ConstantDescs.CD_int),
                            Modifier.PUBLIC,
                            methodBuilder -> {
                                methodBuilder.withCode(codeBuilder -> {
                                    Label label1 = codeBuilder.newLabel();
                                    Label label2 = codeBuilder.newLabel();
                                    codeBuilder.iload(1)
                                            .ifeq(label1)
                                            .aload(0)
                                            .iload(2)
                                            .invokevirtual(ClassDesc.of("Main"), "m1", MethodTypeDesc.of(ConstantDescs.CD_void, ConstantDescs.CD_int))
                                            .goto_(label2)
                                            .labelBinding(label1)
                                            .aload(0)
                                            .iload(2)
                                            .invokevirtual(ClassDesc.of("Main"), "m2", MethodTypeDesc.of(ConstantDescs.CD_void, ConstantDescs.CD_int))
                                            .labelBinding(label2);
                                });
                            });
                });
        Files.write(Path.of(STR."\{className}.class"), bytes);
        Class<?> clazz = new ByteClassLoader().defineClass(STR."org.zero.\{className}", bytes);
        clazz.getDeclaredMethod("m1", boolean.class, int.class)
                .invoke(clazz.getConstructor().newInstance(), true, 0);

        // 解析类
        ClassModel classModel = classFile.parse(Path.of("org/zero/A.class"));
        System.out.println("fields:\n");
        classModel.fields().forEach(fieldModel -> {
            System.out.println(fieldModel.fieldName());
        });
        System.out.println("methods:\n");
        classModel.methods().forEach(methodModel -> {
            System.out.println(methodModel.methodName());
        });
    }

    static class ByteClassLoader extends ClassLoader {
        public Class<?> defineClass(String name, byte[] b) {
            return super.defineClass(name, b, 0, b.length);
        }
    }

    /**
     * 模板字符串
     */
    public void stringTemplate() {
        String title = "Web Page";
        String text = "Hello, world";

        // jdk 1.0
        String html1 = "<html>\n" +
                "  <head>\n" +
                "    <title>" + title + "</title>\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <p>" + text + "</p>\n" +
                "  </body>\n" +
                "</html>";
        System.out.println("传统写法：\n" + html1);

        // jdk 1.1
        String html2 = MessageFormat.format("<html>\n" +
                        "  <head>\n" +
                        "    <title>{0}</title>\n" +
                        "  </head>\n" +
                        "  <body>\n" +
                        "    <p>{1}</p>\n" +
                        "  </body>\n" +
                        "</html>",
                title, text);
        System.out.println("MessageFormat.format()：\n" + html2);

        // jdk 1.5
        String html3 = new StringBuilder("<html>\n")
                .append("  <head>\n")
                .append("    <title>").append(title).append("</title>\n")
                .append("  </head>\n")
                .append("  <body>\n")
                .append("    <p>").append(text).append("</p>\n")
                .append("  </body>\n")
                .append("</html>")
                .toString();
        System.out.println("StringBuilder：\n" + html3);
        String html4 = String.format("<html>\n" +
                        "  <head>\n" +
                        "    <title>%s</title>\n" +
                        "  </head>\n" +
                        "  <body>\n" +
                        "    <p>%s</p>\n" +
                        "  </body>\n" +
                        "</html>",
                title, text);
        System.out.println("String.format()：\n" + html4);

        // jdk 15
        String html5 = """
                <html>
                  <head>
                    <title>%s</title>
                  </head>
                  <body>
                    <p>%s</p>
                  </body>
                </html>
                """.formatted(title, text);
        System.out.println("String.formatted()：\n" + html5);

        // jdk 22
        String html6 = STR."""
        <html>
          <head>
            <title>\{title}</title>
          </head>
          <body>
            <p>\{text}</p>
          </body>
        </html>
        """;
        System.out.println("STR.：\n" + html6);
    }

    /**
     * 流的收集器
     */
    public void streamGatherer() {
        String str = Stream.of(1, 2, 3, 4, 5)
                .gather(Gatherers.fold(() -> "", (string, number) -> string + number))
                .findFirst()
                .orElse(null);
        System.out.println(str);

        List<String> list =
                Stream.of(1, 2, 3, 4, 5)
                        .gather(Gatherers.scan(() -> "", (string, number) -> string + number))
                        .toList();
        System.out.println(list);

        List<Integer> list1 = Stream.of(1, 2, 3, 4, 5)
                .gather(Gatherers.mapConcurrent(5, number -> number * 2))
                .toList();
        System.out.println(list1);

        List<List<Integer>> list2 = Stream.of(1, 2, 3, 4, 5)
                .gather(Gatherers.windowFixed(3))
                .toList();
        System.out.println(list2);

        List<List<Integer>> list3 = Stream.of(1, 2, 3, 4, 5)
                .gather(Gatherers.windowSliding(3))
                .toList();
        System.out.println(list3);
    }

    /**
     * 结构化并发
     */
    @SneakyThrows
    public void structuredConcurrency() {
        var scope = new StructuredTaskScope<>();
        try (scope) {
            // fork 子任务
            var subtask1 = scope.fork(() -> 12);
            var subtask2 = scope.fork(() -> 16.4F);
            // 等待运行
            scope.join();

            System.out.println(STR."subtask1: \{subtask1.get()}");
            System.out.println(STR."subtask2: \{subtask2.get()}");
        }
    }


    /**
     * 作用域值
     * <p>
     * 主要还是为了解决 ThreadLocal 一些痛点问题而引入的：
     * 1、ThreadLocal 变量的生命周期一般很长。
     * 当使用 set 方法为当前线程设置值之后，这个值在线程的整个生命周期中都会保留，直到调用 remove 方法才会删除。
     * 要是没有主动 remove，很可能造成内存泄漏。
     * 2、ThreadLocal 变量值是可变的，任何运行在当前线程中的代码都可以修改该变量的值，很容易产生一些难以调试的 bug。
     * 3、ThreadLocal 变量可以被继承。如果一个子线程从父线程中继承 ThreadLocal 变量，那么该子线程需要独立存储父线程中的全部 ThreadLocal 变量，这会产生比较大的内存开销。
     * 4、由于引入了虚拟线程，其同样可以使用 ThreadLocal，但虚拟线程的特点是数量巨大，每个虚拟线程的生命周期较短，因此不容易产生内存泄漏问题，却会由于线程继承所带来的巨大内存开销问题。
     * <p>
     * 正是由于 ThreadLocal 的这些问题。Java 20 引入了一种新的对象 ScopedValue，可以作为 ThreadLocal 之外的另外一种选择。
     * ScopedValue 具备 ThreadLocal 的核心特征，也就是每个线程只有一个值。
     * 但与 ThreadLocal 不同的是，ScopedValue 是不可变的，并且有确定的作用域，这也是名字中 scoped 的含义。
     */
    @SneakyThrows
    public void scopedValue() {
        ScopedValue<String> scopedValue = ScopedValue.newInstance();

        var _ = ScopedValue.where(scopedValue, "xyz");

        ScopedValue.runWhere(scopedValue, "abc", () -> {
            var scope = new StructuredTaskScope.ShutdownOnFailure();
            try (scope) {
                System.out.println(STR."scopedValue: \{scopedValue.get()}");
                var subtask1 = scope.fork(() -> Math.PI);
                var subtask2 = scope.fork(() -> Boolean.FALSE);
                scope.join().throwIfFailed();
                System.out.println(STR."subtask1: \{subtask1.get()}");
                System.out.println(STR."subtask2: \{subtask2.get()}");
            }
        });

        var _ = ScopedValue.callWhere(scopedValue, "mn", () -> 1);
    }

    /**
     * 向量的支持与运算
     */
    public void vectorApi() {
        IntVector originalVector = IntVector.fromArray(VectorSpecies.ofPreferred(Integer.class), new int[]{1, 2, 3, 4}, 0);
        IntVector mulledVector = originalVector.mul(2);

        System.out.println(STR."Original vector: \{Arrays.toString(originalVector.toArray())}");
        System.out.println(STR."Mulled vector: \{Arrays.toString(mulledVector.toArray())}");
    }

}
