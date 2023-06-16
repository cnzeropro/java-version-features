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

/**
 * java 10 新特性
 * <p>
 * 1.【new】局部变量类型推断：var关键字
 * <p>
 * 2.【update】集合类新增copyOf方法
 * <p>
 * 3.【update】重载java.io.ByteArrayOutputStream#toString()方法
 * <p>
 * 4.【delete】删除 javah 工具，使用 javac -h 代替
 *
 * @author Zero
 */
public class Java10 {

    /**
     * 增加局部变量 var 关键字
     * <p>
     * 将前端思想var关键字引入java后端，自动检测所属类型，一种情况除外，不能为null，因为不能判断具体类型，会报异常。
     */
    public void varKeyword() {
        var numInt = 2147483647;
        var numLong = 9223372036854775807L;
        var numFloat = 2.1718F;
        var numDouble = 3.1415926;
        var bool = true;
        var ch = 'A';
        var string = "this var is str";
        var list = new ArrayList<>();
        var set = new HashSet<>();
        var map = new HashMap<>(16);
        var numByte = Byte.MAX_VALUE;
        var numShort = Short.MAX_VALUE;

        list.add("this var is list");
        set.add("this var is set");
        map.put("tip", "this var is map");

        System.out.println(numInt);
        System.out.println(numLong);
        System.out.println(numFloat);
        System.out.println(numDouble);
        System.out.println(bool);
        System.out.println(ch);
        System.out.println(string);
        System.out.println(list);
        System.out.println(set);
        System.out.println(map);
        System.out.println(numByte);
        System.out.println(numShort);
    }

    /**
     * 增加copyOf方法
     * <p>
     * 在java.util.List、java.util.Set、java.util.Map新增加了一个静态方法copyOf。
     * 这些方法按照其迭代顺序返回一个不可修改的列表、集合或映射包含了给定的元素的集合。
     * 如果将返回后的集合继续修改，那么会报异常。
     */
    public void copyOfMethod() {
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
     * 重载java.io.ByteArrayOutputStream#toString()方法
     * <p>
     * ByteArrayOutputStream.toString()通过使用指定的字符集编码字节，将缓冲区的内容转换为字符串。
     * 以前是默认没有参数，现在加了一个可设定编码字符方法。
     */
    public void toStringMethod() {
        var str = "I love Java!";

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
}
