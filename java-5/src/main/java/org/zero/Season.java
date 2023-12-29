package org.zero;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author zero
 * @since 2021/12/26
 */
@RequiredArgsConstructor
@Getter
public enum Season {
    SPRING(1, "Flowers bloom"),
    SUMMER(2, "Hot and sunny"),
    AUTUMN(3, "Leaves fall"),
    WINTER(4, "Cold and snowy");

    private final int seasonOrder;
    private final String description;
}
