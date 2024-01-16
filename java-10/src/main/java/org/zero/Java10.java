package org.zero;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <a href="https://docs.oracle.com/javase/10">JDK 10 Documentation</a>
 * <h2>Language Features</h2>
 * <ol>
 *     <li>【new】局部变量类型推断（Local Variable Type Inference）{@link Java10#introduceVarIdentifier}</li>
 * </ol>
 *
 * <h2>Previews and Incubator</h2>
 * <ol>
 * </ol>
 *
 * <h2>Libraries Improvements</h2>
 * <ol>
 *     <li>【new】新增创建不可修改集合的API（APIs for Creating Unmodifiable Collections）{@link Java10#addCreatingUnmodifiableCollectionsApi}</li>
 *     <li>【new】新增 @summary 内联标记（Comment Tag for Summary of an API Description）{@link Java10#addSummaryInlineTag}</li>
 *     <li>【new】新增 Optional 类方法（Optional.orElseThrow() Method）{@link Java10#enhanceOptionalApi}</li>
 *     <li>【new】重载 ByteArrayOutputStream 的 toString 方法。{@link Java10#overloadToStringMethod}</li>
 * </ol>
 *
 * <h2>Changes</h2>
 * <ol>
 *     <li>【update】类文件版本号变更为54.0（Class File Version Number is 54.0）</li>
 *     <li>【update】改进 for-each 的字节码生成（Bytecode Generation for Enhanced for Loop）</li>
 *     <li>【update】删除 native-header 工具（Remove the Native-Header Generation Tool (javah)）<p>
 *     native-header 工具（javah）已被删除，现使用{@code javac -h}代替
 *     </li>
 *     <li>【delete】删除旧的 LookAndFeel 支持（Removal of Support for Using Old LookAndFeel）</li>
 *     <li>【delete】删除过时的 -X 选项（Removal of Obsolete -X Options）<p>
 *     删除过时的 HotSpot VM 选项，包括：-Xoss、-Xsqnopause、-Xoptimize、-Xboundthreads、-Xusealtsigs</li>
 * </ol>
 *
 * @author Zero
 * @since 2018/05/16
 */
public class Java10 {
    /**
     * 引入 var 标识符
     * <p>
     * 用于局部变量类型推断，这使得代码更具可读性，并减少了所需的样板代码量。
     * 通过将前端思想 var 关键字引入 java 后端，自动从上下文推断局部变量类型。但一种情况除外，不能为 null，因为不能判断具体类型。
     * <p>
     * var 主要用于以下类型的变量：
     * <ul>
     *     <li>使用初始值设定项的局部变量声明</li>
     *     <li>增强 for 循环的索引</li>
     *     <li>在传统 for 循环中声明的索引变量</li>
     *     <li>try-with-resources 中的变量</li>
     * </ul>
     * 请注意，var 不是关键字，而是一个保留类型名，这意味着用作变量、方法或包名的代码不会受到影响，但用作类或接口名称的代码将会受到影响。
     */
    public void introduceVarIdentifier() {
        // 不能为null
        // var a = null;

        var numInt = 2147483647;
        var numLong = 9223372036854775807L;
        var numFloat = 2.1718F;
        var numDouble = 3.1415926;
        var bool = true;
        var ch = 'A';

        var str = "this var is str";
        var wrappedByte = Byte.MAX_VALUE;
        var wrappedInt = Integer.MAX_VALUE;

        // var list = new ArrayList<>();
        // 建议指定数据泛型，否则什么都装，容易出现安全问题
        var list = new ArrayList<String>();
        list.add("this var is list");
        var set = new HashSet<String>();
        set.add("this var is set");
        var map = new HashMap<String, Object>(16);
        map.put("tip", "this var is map");

        var obj = new Object();

        System.out.println(numInt);
        System.out.println(numLong);
        System.out.println(numFloat);
        System.out.println(numDouble);
        System.out.println(bool);
        System.out.println(ch);
        System.out.println(str);
        System.out.println(list);
        System.out.println(set);
        System.out.println(map);
        System.out.println(wrappedByte);
        System.out.println(wrappedInt);
        System.out.println(obj);
    }

    /**
     * 添加新的内联标记：@summary
     * <p>
     * 该标记用于显式指定用作 API 描述摘要的文本。
     * 例如：{@summary 这是一段摘要文本}
     */
    public void addSummaryInlineTag() {

    }

    /**
     * 新增 Optional 类方法
     * <p>
     * 新增了一个 orElseThrow 的重构方法，可作为 get 方法的首选替代方案。
     */
    public void enhanceOptionalApi() {
        Optional<String> strOpt = Optional.of(String.class).map(Class::getName);
        String className0 = strOpt.get();
        String className1 = strOpt.orElseThrow(() -> new RuntimeException("get failed"));
        String className2 = strOpt.orElseThrow();

        System.out.println("ClassName0: " + className0);
        System.out.println("ClassName1: " + className1);
        System.out.println("ClassName2: " + className2);
    }

    /**
     * 新增创建不可修改集合的API
     * <p>
     * 在{@link java.util.List}、{@link java.util.Set}、{@link java.util.Map}中新增加了一个 copyOf 静态方法。
     * 该方法按照其原有迭代顺序返回一个不可修改的列表、集合或映射。
     * <p>
     * Collectors（收集器）新增方法，
     * 共包括4个:
     * {@link java.util.stream.Collectors#toUnmodifiableList()}、
     * {@link java.util.stream.Collectors#toUnmodifiableSet()}、
     * {@link java.util.stream.Collectors#toUnmodifiableMap(java.util.function.Function, java.util.function.Function)}、
     * {@link java.util.stream.Collectors#toUnmodifiableMap(java.util.function.Function, java.util.function.Function, java.util.function.BinaryOperator)}
     */
    public void addCreatingUnmodifiableCollectionsApi() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        // Collectors
        var list = random.ints(12, 10, 100).boxed()
                .collect(Collectors.toUnmodifiableList());
        var set = random.ints(12, 10, 100).boxed()
                .collect(Collectors.toUnmodifiableSet());
        var map = random.ints(12, 10, 100).boxed()
                .collect(Collectors.toUnmodifiableMap(i -> i + "a", Function.identity()));
        var map1 = random.ints(12, 10, 100).boxed()
                .collect(Collectors.toUnmodifiableMap(i -> i + "a", Function.identity(), (v1, v2) -> v2));
        System.out.println("list: " + list);
        System.out.println("set: " + set);
        System.out.println("map: " + map);
        System.out.println("map1: " + map1);

        // copyOf
        var resultList = List.copyOf(list);
        var resultSet = Set.copyOf(set);
        var resultMap = Map.copyOf(map);
        // 抛出 UnsupportedOperationException
        // resultList.add(100);
        System.out.println("resultList:" + resultList);
        System.out.println("resultSet:" + resultSet);
        System.out.println("resultMap:" + resultMap);
    }

    /**
     * 重载{@link ByteArrayOutputStream#toString}方法
     * <p>
     * 新增{@link java.io.ByteArrayOutputStream#toString(java.nio.charset.Charset)}，该方法通过使用指定的字符集编码字节，将缓冲区的内容转换为字符串。
     */
    public void overloadToStringMethod() {
        var str = "Hello! 你好！";
        System.out.println("原始字符串：" + str);

        var bais = new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8));
        var baos = new ByteArrayOutputStream();
        try (bais; baos) {
            var bytes = new byte[1024];
            int len;
            while ((len = bais.read(bytes)) != -1) {
                baos.write(bytes, 0, len);
            }

            var s = baos.toString(StandardCharsets.UTF_8);
            System.out.println("字符串：" + s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
