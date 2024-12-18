package org.zero;

/**
 * @author Zero (cnzeropro@163.com)
 * @since 2024/12/12
 */
public record Mahjong(Material material, Region region) implements Card {
    public enum Material {
        BAMBOO,
        PLASTIC,
        BONE,
        ;
    }

    public record Region(String name, int total) {
    }

    @Override
    public String getName() {
       return String.format("%s mahjong", region.name);
    }

    @Override
    public Number getTotal() {
        return region.total;
    }
}
