package org.zero;

/**
 * @author zero
 * @since 2023/11/10
 */
public enum Poker implements Card {
    HEART("红桃", 13),
    SPADE("黑桃", 13),
    DIAMOND("方块", 13),
    CLUB("梅花", 13),
    JOKER("大小王", 2),
    ;

    private final String name;
    private final int total;

    Poker(String name, int total) {
        this.name = name;
        this.total = total;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Number getTotal() {
        return total;
    }
}
