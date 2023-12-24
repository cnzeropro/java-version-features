package org.zero;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * java 10 新特性
 * <p>
 * 1.【new】局部变量类型推断：var关键字 {@link Java10#introduceVarKeyword()}
 * <p>
 * 2.【new】集合类新增copyOf方法 {@link Java10#addCopyOfMethod()}
 * <p>
 * 3.【new】重载java.io.ByteArrayOutputStream#toString()方法，新增toString(Charset) {@link Java10#overloadToStringMethod()}
 * <p>
 * 4.【delete】删除javah工具，使用<code>javac -h</code>代替
 * <p>
 * 5.【new】新增Collectors（收集器）部分API {@link Java10#addCollectorsApi()}
 *
 * @author Zero
 */
public class Java10 {
    /**
     * 增加局部变量var关键字
     * <p>
     * 将前端思想var关键字引入java后端，自动检测所属类型。但一种情况除外，不能为null，因为不能判断具体类型。
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

        // 建议指定数据泛型，否则什么都装，容易出现安全问题
        // var list = new ArrayList<>();
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
     * 增加copyOf方法
     * <p>
     * 在java.util.List、java.util.Set、java.util.Map中新增加了一个copyOf静态方法。
     * 这些方法按照其迭代顺序返回一个不可修改的列表、集合或映射包含了给定的元素的集合。
     * 如果将返回后的集合继续修改，那么会报异常。
     */
    public void addCopyOfMethod() {
        var list = List.of("first", "second", "third");
        var resultList = List.copyOf(list);
        var set = Set.of("first", "second", "third");
        var resultSet = Set.copyOf(set);
        var map = Map.of("1", "first", "2", "second", "3", "third");
        var resultMap = Map.copyOf(map);

        // 抛出 UnsupportedOperationException
//        resultList.add("aaa");

        System.out.println("list:" + resultList);
        System.out.println("set:" + resultSet);
        System.out.println("map:" + resultMap);
    }

    /**
     * 重载java.io.ByteArrayOutputStream#toString()方法，新增toString(Charset)
     * <p>
     * ByteArrayOutputStream.toString(Charset)通过使用指定的字符集编码字节，将缓冲区的内容转换为字符串。
     */
    public void overloadToStringMethod() {
        var str = "Hello! 你好！";

        var bais = new ByteArrayInputStream(str.getBytes(StandardCharsets.ISO_8859_1));
        var baos = new ByteArrayOutputStream();
        try (bais; baos) {
            var bytes = new byte[1024];
            int len;
            while ((len = bais.read(bytes)) != -1) {
                baos.write(bytes, 0, len);
            }

            var s = baos.toString(StandardCharsets.ISO_8859_1);
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 新增Collectors（收集器）部分API
     * <p>
     * 包括toUnmodifiableList()、toUnmodifiableSet()、toUnmodifiableMap(Function, Function)、toUnmodifiableMap(Function, Function, BinaryOperator)
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
}
