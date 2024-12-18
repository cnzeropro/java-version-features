package org.zero;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 通过命令 {@code java Main.java} 启动程序
 * <p>
 * 注意：如果还引用了 jar 包的类，则可以通过 {@code --class-path lib/*} 来指定
 *
 * @author Zero (cnzeropro@163.com)
 * @since 2024/12/2
 */
public class Main {
    public static void main(String[] args) {
        var shapes = new Shape[]{new Circle<>(12L),
                new Rectangle<>(23, 16.6F),
                new Triangle<>(7.1, 6L, BigDecimal.TEN)};
        int i;
        if (args.length > 0) {
            i = Integer.parseInt(args[0]);
        } else {
            i = ThreadLocalRandom.current().nextInt(shapes.length);
        }
        var shape = shapes[i];
        var printString = shape.toPrintString();
        System.out.println(printString);
    }
}
