package org.zero;

import java.util.HexFormat;
import java.util.concurrent.ThreadLocalRandom;

/**
 * <a href="https://docs.oracle.com/javase/17">JDK 17 Documentation</a>
 * <h2>Language Features</h2>
 * <ol>
 *     <li>【new】密封类（Sealed Classes）{@link Java17#addSealedClasses()}</li>
 * </ol>
 *
 * <h2>Previews and Incubator</h2>
 * <ol>
 *     <li>【update】switch 模式匹配（Pattern Matching for switch Expressions and Statements）（首次预览） </li>
 * </ol>
 *
 * <h2>Libraries Improvements</h2>
 * <ol>
 *     <li>【new】增加 HexFormat 类（Hex Formatting and Parsing Utility）{@link Java17#addHexFormatClass()}</li>
 * </ol>
 *
 * <h2>Changes</h2>
 * <ol>
 * </ol>
 *
 * @author Zero
 * @since 2021/11/29
 */
public class Java17 {
    /**
     * 新增密封类
     * <p>
     * 密封（sealed）类表示一个可扩展的类，但只能通过已知的子类型列表进行扩展，而不能通过其他任意扩展。
     * 另外子类也相应存在三种情况：
     * 1、确认密封性（final），无法被扩展。
     * 2、传递密封性（sealed）。
     * 3、显式放弃密封性（non-sealed），此状态下需要由下游调用方承担打破密封的风险。
     */
    public void addSealedClasses() {
        Pet cat = new Cat("喵喵", 3);
        cat.sleep();
        Pet blackCat = new BlackCat("妙妙", 5);
        blackCat.sleep();

        Pet dog = new Dog("旺旺", 1);
        dog.sleep();
        Pet yellowDog = new YellowDog("汪汪", 7);
        yellowDog.sleep();

        Pet pig = new Pig("哼哼", 12);
        pig.sleep();
    }

    /**
     * 增加 HexFormat 类
     * <p>
     * 用于字节和字符以及十六进制编码字符串之间进行转换
     */
    public void addHexFormatClass() {
        byte[] bytes = new byte[16];
        ThreadLocalRandom.current().nextBytes(bytes);
        String hexStr = HexFormat.of().formatHex(bytes);
        System.out.println("hexStr: " + hexStr);
    }
}
