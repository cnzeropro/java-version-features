package org.zero;

import java.math.BigInteger;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.SequencedCollection;
import java.util.SequencedMap;
import java.util.SequencedSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;

/**
 * <a href="https://openjdk.org/projects/jdk/21/">JDK 21</a>
 * <a href="https://docs.oracle.com/javase/21/">JDK 21 Documentation</a>
 * <h1>Features</h1>
 * <ol>
 *     <li><a href="https://openjdk.org/jeps/441">441</a>：switch 模式匹配（Pattern Matching for switch Expressions and Statements）{@link #enhanceSwitchGrammar()}</li>
 *     <li><a href="https://openjdk.org/jeps/440">440</a>：记录模式（Record Patterns）{@link #recordPattern()}</li>
 *     <li><a href="https://openjdk.org/jeps/444">444</a>：虚拟线程（Virtual Threads）{@link #virtualThread()}</li>
 *     <li><a href="https://openjdk.org/jeps/431">431</a>：有序集合（Sequenced Collections）{@link #addSequencedCollections()}</li>
 *     <li>新增 Unicode 表情字符相关方法（Unicode Emoji Properties）{@link #addMethodsForCharacter()}</li>
 *     <li>新增 repeat 方法（New StringBuilder and StringBuffer repeat Methods）{@link #addRepeatMethod()}</li>
 *     <li>正则匹配支持 Emoji 字符（Emoji Related Binary Properties in RegEx）{@link #emojiInRegEx()}</li>
 *     <li><a href="https://openjdk.org/jeps/439">439</a>：分代 ZGC（Generational ZGC）{@link #generationalZgc()}</li>
 * </ol>
 * <ol>
 *     <li><a href="https://openjdk.org/jeps/430">430</a>：字符串模板（String Templates）[首次预览]</li>
 *     <li><a href="https://openjdk.org/jeps/443">443</a>：匿名模式和变量（Unnamed Patterns and Variables）[首次预览]</li>
 *     <li><a href="https://openjdk.org/jeps/445">445</a>：匿名类和实例主方法（Unnamed Classes and Instance Main Methods）[首次预览]</li>
 *     <li><a href="https://openjdk.org/jeps/446">446</a>：作用域值（Scoped Values）[首次预览]</li>
 *     <li><a href="https://openjdk.org/jeps/453">453</a>：结构化并发（Structured Concurrency）[首次预览]</li>
 *     <li><a href="https://openjdk.org/jeps/442">442</a>：外部函数和内存 API（Foreign Function & Memory API）[第三次预览]</li>
 * </ol>
 * <ol>
 *     <li><a href="https://openjdk.org/jeps/448">448</a>：向量 API（Vector API）[第六次孵化]</li>
 * </ol>
 *
 * @author Zero
 * @since 2023/11/10
 */
public class Java21 {
    /**
     * 记录模式
     * <p>
     * 记录模式（Record Patterns）在 JDK 19 作为预览功能提出，经过不断讨论修改，最终在 JDK 21 引入。<br>
     * 作为模式匹配的扩展，它允许更简洁地解构记录类型（record），同时支持嵌套模式，实现更复杂的数据查询。
     */
    public void recordPattern() {
        var cards = new Card[]{Poker.JOKER,
                new Mahjong(Mahjong.Material.BAMBOO, new Mahjong.Region("四川", 144)),
                new Domino<>(BigInteger.TEN),
                null,
                Bridge.CLUB,
                new BoxedCard<>(2, new BoxedCard<>(5, new Domino<>(3))),
                new Domino<>(1)};
        int i = ThreadLocalRandom.current().nextInt(cards.length);
        var card = cards[i];
        System.out.println("Current card: " + card);

        // Type Pattern
        if (card instanceof Bridge bridge) {
            String name = bridge.getName();
            System.out.println("Bridge name: " + name);
        }
        // Record Pattern
        if (card instanceof Mahjong(var material, Mahjong.Region(var name, var total))) {
            System.out.printf("""
                    The mahjong is:
                        material: %s
                        region name: %s
                        total: %d
                    """, material, name, total);
        }
        // Generic Type Pattern
        if (card instanceof Domino(Integer num)) {
            System.out.printf("%s Domino%n", num);
        }
        if (card instanceof Domino(BigInteger num)) {
            System.out.printf("%s Domino%n", num);
        }

        String description = switch (card) {
            // Null 匹配标签
            case null -> "";
            case Poker poker -> String.format("Poker[%s]", poker.name());
            case Bridge tarot -> String.format("Bridge[%s]", tarot.name());
            case BoxedCard(var total, var c) -> String.format("%d boxed card %s", total, c);
            // 记录模式中类型参数的推断
            case Mahjong(var material, Mahjong.Region(var name, var total)) ->
                    String.format("%s %d %s mahjong", name, total, material.name().toLowerCase());
            case Domino(Number total) -> String.format("%s Domino", total);
            // 当编译器判断所有分支都已涵盖时，switch 不再需要 default 分支
            // default -> throw new IllegalArgumentException("Unrecognized card: " + card);
        };
        System.out.println("The card description is " + description);
    }

    /**
     * switch 模式匹配
     * <p>
     * switch 模式匹配（Pattern Matching for switch Expressions and Statements）在 JDK 17 作为预览功能提出，经过不断讨论修改，最终在 JDK 21 引入。<br>
     * 它增强了 switch 语法，可以针对对象类型以及对象的内容进行更灵活、更简洁的条件判断。<br>
     * 例如，使其可以匹配 null，也可以配合 Record Patterns（记录模式）以及类型转换和类型推断使用。
     */
    public void enhanceSwitchGrammar() {
        Card[] cards = {Poker.SPADE, Poker.HEART, Poker.DIAMOND, Poker.CLUB, Poker.JOKER,
                null,
                Bridge.SPADE, Bridge.HEART, Bridge.DIAMOND, Bridge.CLUB};
        int i = ThreadLocalRandom.current().nextInt(cards.length);
        Card card = cards[i];
        System.out.println("Current card: " + card);

        // Switch Statement（switch 语句）
        // 之前的写法
        switch (card) {
            case null:
                System.out.println("Card is null");
                break;
            case Poker poker:
                if (poker == Poker.SPADE) {
                    System.out.println("Poker Spades");
                } else if (poker == Poker.HEART) {
                    System.out.println("Poker Hearts");
                } else if (poker == Poker.DIAMOND) {
                    System.out.println("Poker Diamonds");
                } else if (poker == Poker.CLUB) {
                    System.out.println("Poker Clubs");
                } else if (poker == Poker.JOKER) {
                    System.out.println("Poker Jokers");
                }
                break;
            case Bridge bridge:
                if (bridge == Bridge.SPADE) {
                    System.out.println("Bridge Spades");
                } else if (bridge == Bridge.HEART) {
                    System.out.println("Bridge Hearts");
                } else if (bridge == Bridge.DIAMOND) {
                    System.out.println("Bridge Diamonds");
                } else if (bridge == Bridge.CLUB) {
                    System.out.println("Bridge Clubs");
                }
                break;
            default:
                throw new IllegalArgumentException("Unexpected value: " + card);
        }
        // Switch Expression（switch 表达式）
        // 现在的写法
        var text1 = switch (card) {
            case null -> "Card is null";
            case Poker poker when poker == Poker.SPADE -> "Poker Spades";
            case Poker poker when poker == Poker.HEART -> "Poker Hearts";
            case Poker poker when poker == Poker.DIAMOND -> "Poker Diamonds";
            case Poker poker when poker == Poker.CLUB -> "Poker Clubs";
            case Poker poker when poker == Poker.JOKER -> "Poker Jokers";
            case Bridge tarot when tarot == Bridge.SPADE -> "Bridge Spades";
            case Bridge tarot when tarot == Bridge.HEART -> "Bridge Hearts";
            case Bridge tarot when tarot == Bridge.DIAMOND -> "Bridge Diamonds";
            case Bridge tarot when tarot == Bridge.CLUB -> "Bridge Clubs";
            default -> throw new IllegalArgumentException("Unexpected value: " + card);
        };
        System.out.println(text1);

        // 因为 switch 语句和表达式允许限定 enum 常量，所以可以改写成以下示例：
        // Switch Statement（switch 语句）
        // 之前的写法
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
            case Poker.JOKER:
                System.out.println("Poker Jokers");
                break;
            case Bridge.SPADE:
                System.out.println("Bridge Spades");
                break;
            case Bridge.HEART:
                System.out.println("Bridge Hearts");
                break;
            case Bridge.DIAMOND:
                System.out.println("Bridge Diamonds");
                break;
            case Bridge.CLUB:
                System.out.println("Bridge Clubs");
                break;
            default:
                throw new IllegalArgumentException("Unexpected value: " + card);
        }
        // Switch Expression（switch 表达式）
        // 现在的写法
        var text2 = switch (card) {
            case null -> "Card is null";
            case Poker.SPADE -> "Poker Spades";
            case Poker.HEART -> "Poker Hearts";
            case Poker.DIAMOND -> "Poker Diamonds";
            case Poker.CLUB -> "Poker Clubs";
            case Poker.JOKER -> "Poker Jokers";
            case Bridge.SPADE -> "Bridge Spades";
            case Bridge.HEART -> "Bridge Hearts";
            case Bridge.DIAMOND -> "Bridge Diamonds";
            case Bridge.CLUB -> "Bridge Clubs";
            default -> throw new IllegalArgumentException("Unexpected value: " + card);
        };
        System.out.println(text2);
    }

    /**
     * 虚拟线程
     * <p>
     * 虚拟线程（Virtual Threads）在 JDK 19 作为预览功能提出，经过不断讨论修改，最终在 JDK 21 引入。<br>
     * 区别于虚拟线程，传统的线程对象叫做平台线程（Platform Threads）。<br>
     * 平台线程在底层 OS 线程上运行 Java 代码，并在代码的整个生命周期中占用该 OS 线程，因此平台线程的数量受限于 OS 线程的数量。<br>
     * 虚拟线程是 {@link java.lang.Thread} 的一个实例，它在底层 OS 线程上运行 Java 代码，但不会在代码的整个生命周期中占用该 OS 线程。<br>
     * 也就是说，多个虚拟线程可以在同一个 OS 线程上运行其 Java 代码，可以有效地共享该线程。<br>
     * 平台线程独占宝贵的 OS 线程，而虚拟线程则不会，因此虚拟线程的数量可以比 OS 线程的数量多得多，执行阻塞任务的整体吞吐量也就大了很多。<br>
     * 但并不是什么都一味使用虚拟线程就好，虚拟线程的使用也需要根据实际情况来决定。<br>
     * 如果一个任务原本就要 1s，无论使用平台线程还是虚拟线程，都需要 1s 才能完成，因此它并不能让单个任务计算得更快。<br>
     * 虚拟线程最大的优势是带来更高的吞吐量，所以面对大多是由 IO 密集型逻辑组成的任务时其可能是更好的选择，而 CPU 密集型任务中，过度使用虚拟线程可能不会带来预期的性能增益。
     */
    public void virtualThread() {
        Runnable printer = () -> System.out.println(Thread.currentThread().getName() + ": Hello, world!");

        // 使用静态构建器
        Thread t1 = Thread.startVirtualThread(printer);
        System.out.println("Thread is virtual: " + t1.isVirtual());
        Thread.ofVirtual()
                .name("virtual-thread")
                .start(printer);

        // 使用线程工厂
        ThreadFactory threadFactory = Thread.ofVirtual()
                .name("virtual-thread", 0L)
                .factory();
        Thread t3 = threadFactory.newThread(printer);
        t3.start();

        // 使用线程池
        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            executorService.execute(printer);
        }
    }

    /**
     * 有序集合
     */
    public void addSequencedCollections() {
        SequencedCollection<String> sequencedCollection = List.of("a", "b", "c");
        System.out.println("sequencedCollection: " + sequencedCollection);
        String first = sequencedCollection.getFirst();
        String last = sequencedCollection.getLast();
        System.out.println("sequencedCollection first: " + first);
        System.out.println("sequencedCollection last: " + last);

        SequencedSet<String> sequencedSet = LinkedHashSet.newLinkedHashSet(3);
        System.out.println("sequencedSet: " + sequencedSet);

        SequencedMap<String, Object> sequencedMap = LinkedHashMap.newLinkedHashMap(6);
        System.out.println("sequencedMap: " + sequencedMap);
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
        System.out.printf("%s is emoji: %s%n", emoji, isEmoji);
    }

    /**
     * 正则匹配支持 Emoji 字符
     * <p>
     * Emoji 字符现在可以用作 java.util.regex.Pattern 类中的二进制属性。
     * 可以将具有表情符号相关属性的字符与新 \p{IsXXX} 结构进行匹配。
     */
    public void emojiInRegEx() {
        boolean matched = Pattern.compile("\\p{IsEmoji}")
                .matcher("🉐")
                .matches();
        System.out.println("matched: " + matched);
    }

    /**
     * 新增 repeat 方法
     * <p>
     * {@link StringBuilder}和{@link StringBuffer}新增 repeat 方法
     */
    public void addRepeatMethod() {
        StringBuilder stringBuilder = new StringBuilder()
                // 97 is 'a'
                .repeat(97, 5)
                .repeat('*', 2)
                .repeat("abc", 4);
        System.out.println(stringBuilder);
    }

    /**
     * 分代 ZGC
     * <p>
     * 通过扩展 ZGC 来维护年轻对象和年老对象的独立生成，从而提高应用程序性能。
     * 主要为了以下三点：降低分配停滞的风险，降低所需的堆内存开销，以及降低垃圾回收 CPU 开销。
     * 可使用命令行选项 -XX:+UseZGC -XX:+ZGenerational 启用分代 ZGC。
     */
    private void generationalZgc() {
    }
}