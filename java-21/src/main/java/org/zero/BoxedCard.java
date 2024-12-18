package org.zero;

import java.math.BigInteger;

/**
 * @author Zero (cnzeropro@163.com)
 * @since 2024/12/13
 */
public record BoxedCard<T extends Card>(long total, T card) implements Card {
    @Override
    public String getName() {
        return card.getName();
    }

    @Override
    public Number getTotal() {
        return BigInteger.valueOf(total).multiply(new BigInteger(card.getTotal().toString()));
    }
}
