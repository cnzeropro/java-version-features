package org.zero;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

/**
 * <a href="https://docs.oracle.com/en/java/javase/21/">JDK 21 Documentation</a>
 * <h2>Language Changes</h2>
 * <ol>
 *     <li>【update】增强 switch 表达式。{@link Java21#enhanceSwitchExpression()}</li>
 *     <li>【update】增强 instanceof 关键字。{@link Java21#enhanceInstanceofKeyword()}</li>
 *     <li>【new】新增字符串模板。（首次预览）</li>
 *     <li>【new】未命名的模式和变量。（首次预览）</li>
 *     <li>【new】未命名的类和 main 方法。（首次预览）</li>
 * </ol>
 * <h2>Changes</h2>
 * <ol>
 *     <li>【new】Unicode 表情符号属性。{@link Java21#addMethodsForCharacter()}</li>
 *     <li>【new】新增 repeat 方法。{@link Java21#addRepeatMethod()}</li>
 * </ol>
 *
 * @author @author Zero
 * @since 2018/12/25
 */
public class Java21 {

    /**
     * 增强 switch 表达式
     * <p>
     * switch 表达式增强，使其可以匹配 null，也可以进行类型转换和类型推断。
     * 另外还新增<code>case xxx when xxx -> xxx</code>支持
     */
    public void enhanceSwitchExpression() {
        /*
         * 示例1
         */
        Shape[] shapes = {new Triangle(3, 4, 5), null, new Circle(5.8)};
        Shape shape = shapes[ThreadLocalRandom.current().nextInt(shapes.length)];
        System.out.println(Optional.ofNullable(shape).map(Shape::toPrintString).orElse("shape is null"));

        // 以前
        double perimeter0;
        if (Objects.isNull(shape)) {
            perimeter0 = -1.0;
        } else if (shape instanceof Circle c) {
            perimeter0 = 2 * Math.PI * c.radius();
        } else if (shape instanceof Triangle(var a, var b, var c)) {
            perimeter0 = a + b + c;
        } else {
            throw new IllegalArgumentException("Unrecognized shape");
        }
        System.out.println("The shape perimeter is " + perimeter0);

        // 现在
        double perimeter1 = switch (shape) {
            // Null 匹配标签
            case null -> -1.0;
            case Circle c -> 2 * Math.PI * c.radius();
            // 记录模式中类型参数的推断
            case Triangle(var a, var b, var c) -> a + b + c;
            default -> throw new IllegalArgumentException("Unrecognized shape");
        };
        System.out.println("The shape perimeter is " + perimeter1);

        /*
         * 示例2
         */
        Card[] cards = {Poker.HEART, Tarot.HEART, Poker.SPADE, Tarot.SPADE, Poker.DIAMOND, Tarot.DIAMOND, Poker.CLUB, Tarot.CLUB, Tarot.TRUMP, Tarot.EXCUSE};
        Card card = cards[ThreadLocalRandom.current().nextInt(cards.length)];

        // 以前
        // Switch Statement（switch 语句）
        switch (card) {
            case Poker p:
                if (p == Poker.SPADE) {
                    System.out.println("Poker Spades");
                } else {
                    System.out.println(p.name());
                }
                break;
            case Tarot t:
                if (t == Tarot.SPADE) {
                    System.out.println("Tarot Spades");
                } else {
                    System.out.println(t.name());
                }
                break;
            default:
                throw new IllegalArgumentException("Unexpected value: " + card);
        }

        // 现在
        // Switch Expression（switch 表达式）
        String text0 = switch (card) {
            case Poker p when p == Poker.SPADE -> "Poker Spades";
            case Poker p when p == Poker.HEART -> "Poker Hearts";
            case Poker p when p == Poker.DIAMOND -> "Poker Diamonds";
            case Poker p when p == Poker.CLUB -> "Poker Clubs";
            case Tarot t when t == Tarot.SPADE -> "Tarot Spades";
            case Tarot t when t == Tarot.HEART -> "Tarot Hearts";
            case Tarot t when t == Tarot.DIAMOND -> "Tarot Diamonds";
            case Tarot t when t == Tarot.CLUB -> "Tarot Clubs";
            case Tarot t -> t.name();
            default -> throw new IllegalArgumentException("Unexpected value: " + card);
        };
        System.out.println(text0);

        // 因为 switch 语句和表达式允许限定 enum 常量，所以可以改写成下面两个示例：
        // Switch Statement（switch 语句）
        switch (card) {
            case Poker.SPADE:
                System.out.println("Poker Spades");
                break;
            case Poker.HEART:
                System.out.println("Poker Hearts");
                break;
            case Poker.DIAMOND:
                System.out.println("Poker Diamonds");
                break;
            case Poker.CLUB:
                System.out.println("Poker Clubs");
                break;
            case Tarot.SPADE:
                System.out.println("Tarot Spades");
                break;
            case Tarot.HEART:
                System.out.println("Tarot Hearts");
                break;
            case Tarot.DIAMOND:
                System.out.println("Tarot Diamonds");
                break;
            case Tarot.CLUB:
                System.out.println("Tarot Clubs");
                break;
            case Tarot.TRUMP:
                System.out.println("Tarot Trumps");
                break;
            case Tarot.EXCUSE:
                System.out.println("Tarot Excuses");
                break;
            default:
                throw new IllegalArgumentException("Unexpected value: " + card);
        }
        // Switch Expression（switch 表达式）
        String text1 = switch (card) {
            case Poker.SPADE -> "Poker Spades";
            case Poker.HEART -> "Poker Hearts";
            case Poker.DIAMOND -> "Poker Diamonds";
            case Poker.CLUB -> "Poker Clubs";
            case Tarot.SPADE -> "Tarot Spades";
            case Tarot.HEART -> "Tarot Hearts";
            case Tarot.DIAMOND -> "Tarot Diamonds";
            case Tarot.CLUB -> "Tarot Clubs";
            case Tarot.TRUMP -> "Tarot Trumps";
            case Tarot.EXCUSE -> "Tarot Excuses";
            default -> throw new IllegalArgumentException("Unexpected value: " + card);
        };
        System.out.println(text1);
    }

    public void enhanceInstanceofKeyword() {
        Shape[] shapes = {new Circle(7.9), new Rectangle<>(2.4F, 3.6F)};
        int i = ThreadLocalRandom.current().nextInt(shapes.length);
        Shape shape = shapes[i];

        // Type Pattern
        if (shape instanceof Circle c) {
            System.out.println("This area is " + Math.PI * Math.pow(c.radius(), 2.0));
        }

        // Record Pattern
        if (shape instanceof Rectangle(var a, var b)) {
            System.out.println("This area is " + new BigDecimal(a.toString()).multiply(new BigDecimal(b.toString())));
        }
    }

    /**
     * Character 类新增方法
     * <p>
     * 一共新增6个方法，包括：
     * {@link java.lang.Character#isEmoji}、
     * {@link java.lang.Character#isEmojiPresentation}、
     * {@link java.lang.Character#isEmojiModifier}、
     * {@link java.lang.Character#isEmojiModifierBase}、
     * {@link java.lang.Character#isEmojiComponent}、
     * {@link java.lang.Character#isExtendedPictographic}
     */
    public void addMethodsForCharacter() {
        // ⛵
        char emoji = '\u26F5';
        boolean isEmoji = Character.isEmoji(emoji);
        System.out.println("isEmoji: " + isEmoji);
    }

    /**
     * 新增 repeat 方法
     * <p>
     * StringBuilder 和 StringBuffer 新增 repeat 方法
     */
    public void addRepeatMethod() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.repeat("abc", 10);
        System.out.println(stringBuilder);
    }
}