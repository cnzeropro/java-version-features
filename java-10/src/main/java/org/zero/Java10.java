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
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <a href="https://docs.oracle.com/javase/10/index.html">JDK 10 Documentation</a>
 * <h2>Language Updates</h2>
 * <ol>
 *     <li>【new】var关键字。{@link Java10#introduceVarKeyword()}</li>
 * </ol>
 * <h2>Changes</h2>
 * <ol>
 *     <li>【update】类文件版本号变更为54.0。</li>
 *     <li>【new】集合类新增 copyOf 方法。{@link Java10#addCopyOfMethod()}</li>
 *     <li>【new】新增Collectors（收集器）API。{@link Java10#addCollectorsApi()}</li>
 *     <li>【update】改进 for-each 的字节码生成。</li>
 *     <li>【new】新增 @summary 内联标记。{@link Java10#addSummaryInlineTag()}</li>
 *     <li>【new】新增 Optional 类方法。{@link Java10#enhanceOptionalApi()}</li>
 *     <li>【new】重载 ByteArrayOutputStream 的 toString 方法。{@link Java10#overloadToStringMethod()}</li>
 *     <li>【new】引入新的系统属性 jdk.disableLastUsageTracking。
 *     该属性用于禁用正在运行的 VM 的 JRE 上次使用情况跟踪。可以在命令行中使用 -Djdk.disableLastUsageTracking=true 或 -Djdk.disableLastUsageTracking 设置此属性。</li>
 *     <li>【update】删除javah工具，使用<code>javac -h</code>代替</li>
 *     <li>【delete】删除旧的 LookAndFeel 支持。</li>
 *     <li>【delete】删除过时的 -X 选项。
 *     删除过时的 HotSpot VM 选项（-Xoss、-Xsqnopause、-Xoptimize、-Xboundthreads、-Xusealtsigs）</li>
 * </ol>
 *
 * @author Zero
 */
public class Java10 {
    /**
     * 引入 var 关键字
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
     */
    public void introduceVarKeyword() {
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
     * 新增Optional类方法
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
     * 增加 copyOf 方法
     * <p>
     * 在 java.util.List、java.util.Set、java.util.Map 中新增加了一个 copyOf 静态方法。
     * 这些方法按照其迭代顺序返回一个不可修改的列表、集合或映射包含了给定的元素的集合。
     * 如果将返回后的集合继续修改，那么会报异常。
     */
    public void addCopyOfMethod() {
        var list = List.of("first", "second", "third");
        var resultList = List.copyOf(list);
        var set = Set.of("first", "second", "third");
        var resultSet = Set.copyOf(set);
        var map = Map.of("first", 1, "second", 2, "third", 3);
        var resultMap = Map.copyOf(map);

        // 抛出 UnsupportedOperationException
        // resultList.add("aaa");

        System.out.println("list:" + resultList);
        System.out.println("set:" + resultSet);
        System.out.println("map:" + resultMap);
    }

    /**
     * 新增 Collectors（收集器）方法
     * <p>
     * 新增方法包括 toUnmodifiableList()、toUnmodifiableSet()、toUnmodifiableMap(Function, Function)、toUnmodifiableMap(Function, Function, BinaryOperator)
     */
    public void addCollectorsApi() {
        var list = Stream.of(1, 2, 3).collect(Collectors.toUnmodifiableList());
        var set = Stream.of(1, 2, 3).collect(Collectors.toUnmodifiableSet());
        var map = Stream.of(1, 2, 3, 2).collect(Collectors.toUnmodifiableMap(Function.identity(), i -> "a" + i));
        var map1 = Stream.of(1, 2, 3, 2).collect(Collectors.toUnmodifiableMap(Function.identity(), i -> "a" + i, (v1, v2) -> v2));

        System.out.println("list: " + list);
        System.out.println("set: " + set);
        System.out.println("map: " + map);
        System.out.println("map1: " + map1);
    }

    /**
     * 重载 ByteArrayOutputStream#toString() 方法
     * <p>
     * 新增 java.io.ByteArrayOutputStream.toString(Charset)，该方法通过使用指定的字符集编码字节，将缓冲区的内容转换为字符串。
     */
    public void overloadToStringMethod() {
        var str = "Hello! 你好！";
        System.out.println("原始字符串：" + str);

        var bais = new ByteArrayInputStream(str.getBytes(StandardCharsets.ISO_8859_1));
        var baos = new ByteArrayOutputStream();
        try (bais; baos) {
            var bytes = new byte[1024];
            int len;
            while ((len = bais.read(bytes)) != -1) {
                baos.write(bytes, 0, len);
            }

            var s = baos.toString(StandardCharsets.ISO_8859_1);
            System.out.println("字符串：" + s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
