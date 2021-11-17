package org.zero;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * @author Zero
 */
public class Java10 {

    /**
     * 增加局部变量 var 关键字
     * 将前端思想var关键字引入java后端，自动检测所属类型，一种情况除外，不能为null，因为不能判断具体类型，会报异常。
     */
    public void varKeyword() {
        var numByte = 127;
        var numShort = 32767;
        var numInt = 2147483647;
        var numLong = 9223372036854775807L;

        var numFloat = 2.1718F;
        var numDouble = 3.1415926;

        var bool = true;

        var ch = 'A';

        var string = "i love java";
        var list = new ArrayList<>();
        var set = new HashSet<>();
        var map = new HashMap<>(16);

        list.add("this var is list");
        set.add("this var is set");
        map.put(1, "this var is map");

        System.out.println(numByte);
        System.out.println(numShort);
        System.out.println(numInt);
        System.out.println(numLong);

        System.out.println(numFloat);
        System.out.println(numDouble);

        System.out.println(bool);

        System.out.println(ch);

        System.out.println(string);
        System.out.println(list.toString());
        System.out.println(set.toString());
        System.out.println(map.toString());
    }

    /**
     * 增加copyOf方法
     * 在java.util.List、java.util.Set、java.util.Map新增加了一个静态方法copyOf。
     * 这些方法按照其迭代顺序返回一个不可修改的列表、集合或映射包含了给定的元素的集合。
     * 如果将返回后的集合继续修改，那么会报异常。
     */
    public void copyOfMethod() {
        var list = new ArrayList<>();

        list.add("first");
        list.add("second");
        list.add("third");

        var result = List.copyOf(list);

        //抛出 UnsupportedOperationException
//        result.add("aaa");
        System.out.println(result);
    }

    /**
     * 重载Java.io.ByteArrayOutputStream的toString()方法
     * ByteArrayOutputStream.toString()通过使用指定的字符集编码字节，将缓冲区的内容转换为字符串。
     * 以前是默认没有参数，现在加了一个可设定编码字符方法。
     */
    public void toStringMethod() {
        String str = "I Love Java!!!";

        ByteArrayInputStream bais = new ByteArrayInputStream(str.getBytes(StandardCharsets.ISO_8859_1));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (bais; baos) {
            int charByte;
            while ((charByte = bais.read()) != -1) {
                baos.write(charByte);
            }

            //toString() 默认的使用的UTF-8编码，也可设定编码字符
            System.out.println(baos.toString(StandardCharsets.ISO_8859_1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
