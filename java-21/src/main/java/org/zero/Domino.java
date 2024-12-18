package org.zero;

/**
 * @author Zero (cnzeropro@163.com)
 * @since 2024/12/13
 */
public record Domino<T extends Number>(T total) implements Card {
    @Override
    public Number getTotal() {
        return total;
    }
}
