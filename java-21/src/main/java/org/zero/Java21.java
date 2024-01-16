package org.zero;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.SequencedCollection;
import java.util.SequencedMap;
import java.util.SequencedSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;

/**
 * <a href="https://docs.oracle.com/en/java/javase/21">JDK 21 Documentation</a>
 * <h2>Language Features</h2>
 * <ol>
 *     <li>【update】switch 模式匹配（Pattern Matching for switch Expressions and Statements）{@link Java21#enhanceSwitchGrammar()}</li>
 *     <li>【update】记录模式（Record Patterns）{@link Java21#enhanceInstanceofKeyword()}</li>
 * </ol>
 *
 * <h2>Previews and Incubator</h2>
 * <ol>
 *     <li>【new】字符串模板（String Templates）（首次预览）</li>
 *     <li>【new】匿名模式和变量（Unnamed Patterns and Variables）（首次预览）</li>
 *     <li>【new】匿名类和实例主方法（Unnamed Classes and Instance Main Methods）（首次预览）</li>
 *     <li>【new】作用域值（Scoped Values）（首次预览）</li>
 *     <li>【new】结构化并发（Structured Concurrency）（首次预览）</li>
 *     <li>【new】外部函数和内存API（Foreign Function & Memory API）（第三次预览）</li>
 *     <li>【new】向量 API（Vector API）（第六次孵化）</li>
 * </ol>
 *
 * <h2>Libraries Improvements</h2>
 * <ol>
 *     <li>【new】虚拟线程（Virtual Threads）{@link Java21#addVirtualThread()}</li>
 *     <li>【new】有序集合（Sequenced Collections）{@link Java21#addSequencedCollections()}</li>
 *     <li>【new】新增 Unicode 表情字符相关方法（Unicode Emoji Properties）{@link Java21#addMethodsForCharacter()}</li>
 *     <li>【new】新增 repeat 方法（New StringBuilder and StringBuffer repeat Methods）{@link Java21#addRepeatMethod()}</li>
 *     <li>【update】正则匹配支持 Emoji 字符（Emoji Related Binary Properties in RegEx）{@link Java21#emojiInRegEx()}</li>
 * </ol>
 *
 * <h2>Changes</h2>
 * <ol>
 *     <li>【update】分代 ZGC（Generational ZGC）。
 *     通过扩展 ZGC 来维护年轻对象和年老对象的独立生成，从而提高应用程序性能。
 *     主要为了以下三点：降低分配停滞的风险，降低所需的堆内存开销，以及降低垃圾回收 CPU 开销。
 *     可使用命令行选项 -XX:+UseZGC -XX:+ZGenerational 启用分代 ZGC。
 *     </li>
 *     <li>【update】支持 GB18030-2022（Support for GB18030-2022）。
 *     这个新标准的字符集现在已经取代了以前的2000标准。但是，却与之前的实现有一些不兼容的变化。
 *     对于那些需要使用旧映射字符集的用户，引入了新的系统属性 jdk.charset.GB18030。
 *     通过将其值设置为2000，GB18030 Charset 将使用以前的 JDK 版本的映射。
 *     </li>
 * </ol>
 *
 * @author Zero
 * @since 2023/11/10
 */
public class Java21 {
    /**
     * switch 中的模式匹配
     * <p>
     * 增强了 switch 语法，通过 Pattern Matching for switch，可以针对对象类型以及对象的内容进行更灵活、更简洁的条件判断。
     * 例如，使其可以匹配 null，也可以配合 Record Patterns（记录模式）以及类型转换和类型推断。
     */
    public void enhanceSwitchGrammar() {
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
        Card[] cards = {Poker.HEART, Tarot.HEART, Poker.SPADE, Tarot.SPADE, Poker.DIAMOND, Tarot.DIAMOND, Poker.CLUB, Tarot.CLUB, null, Tarot.TRUMP, Tarot.EXCUSE};
        Card card = cards[ThreadLocalRandom.current().nextInt(cards.length)];

        // Switch Statement（switch 语句）
        switch (card) {
            case null:
                System.out.println("Card is null");
                break;
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
        // Switch Expression（switch 表达式）
        var text0 = switch (card) {
            case null -> "Card is null";
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

        // 因为 switch 语句和表达式允许限定 enum 常量，所以可以改写成以下示例：
        // Switch Statement（switch 语句）
        switch (card) {
            case null:
                System.out.println("Card is null");
                break;
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
        var text1 = switch (card) {
            case null -> "Card is null";
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
            // 当编译器判断所有分支都已涵盖时，switch 不再需要 default 分支
            // default -> throw new IllegalArgumentException("Unexpected value: " + card);
        };
        System.out.println(text1);
    }

    /**
     * 记录模式
     */
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
     * 虚拟线程
     * <p>
     * 区别于虚拟线程，传统的线程对象叫做平台线程（Platform Thread）。
     * 平台线程在底层 OS 线程上运行 Java 代码，并在代码的整个生命周期中占用该 OS 线程，因此平台线程的数量受限于 OS 线程的数量。
     * 虚拟线程是{@link java.lang.Thread}的一个实例，它在底层 OS 线程上运行 Java 代码，但不会在代码的整个生命周期中占用该 OS 线程。
     * 也就是说，多个虚拟线程可以在同一个 OS 线程上运行其 Java 代码，可以有效地共享该线程。
     * 平台线程独占宝贵的 OS 线程，而虚拟线程则不会，因此虚拟线程的数量可以比 OS 线程的数量多得多，执行阻塞任务的整体吞吐量也就大了很多。
     * 但并不是什么都一味使用虚拟线程就好，虚拟线程的使用也需要根据实际情况来决定。
     * 如果一个任务原本就要 1s，无论使用平台线程还是虚拟线程，都需要 1s 才能完成，因此它并不能让单个任务计算得更快。
     * 虚拟线程最大的优势是带来更高的吞吐量，所以面对大多是由IO密集型逻辑组成的任务时其可能是更好的选择。
     */
    public void addVirtualThread() {
        Runnable printer = () -> System.out.println("Hello, world!");

        // 使用静态构建器
        Thread t1 = Thread.startVirtualThread(printer);
        System.out.println("is virtual: " + t1.isVirtual());
        Thread t2 = Thread.ofVirtual()
                .name("virtual-thread")
                .start(printer);

        // 使用线程池
        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            executorService.execute(printer);
        }

        // 使用线程工厂
        ThreadFactory threadFactory = Thread.ofVirtual()
                .name("virtual-thread", 0L)
                .factory();
        Thread t3 = threadFactory.newThread(printer);
        t3.start();
    }

    /**
     * 有序集合
     */
    public void addSequencedCollections() {
        SequencedCollection<String> sequencedCollection = List.of("a", "b", "c");
        String first = sequencedCollection.getFirst();
        String last = sequencedCollection.getLast();
        System.out.println("first: " + first);
        System.out.println("last: " + last);
        SequencedSet<String> sequencedSet = LinkedHashSet.newLinkedHashSet(3);
        SequencedMap<String, Object> sequencedMap = LinkedHashMap.newLinkedHashMap(6);
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
        System.out.printf("%s is emoji: %s", emoji, isEmoji);
    }

    /**
     * 正则匹配支持 Emoji 字符
     * <p>
     * Emoji 字符现在可以用作 java.util.regex.Pattern 类中的二进制属性。
     * 可以将具有表情符号相关属性的字符与新 \p{IsXXX} 结构进行匹配。
     */
    public void emojiInRegEx() {
        boolean matched = Pattern.compile("\\p{IsEmoji}").matcher("🉐").matches();
        System.out.println("matched: " + matched);
    }

    /**
     * 新增 repeat 方法
     * <p>
     * {@link StringBuilder}和{@link StringBuffer}新增 repeat 方法
     */
    public void addRepeatMethod() {
        StringBuilder stringBuilder = new StringBuilder()
                .repeat('*', 5)
                .repeat("abc", 10);
        System.out.println(stringBuilder);
    }
}