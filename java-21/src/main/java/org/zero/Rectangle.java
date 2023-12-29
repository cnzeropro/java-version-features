package org.zero;

/**
 * @author zero
 * @since 2023/9/28
 */
public record Rectangle<T extends Number>(T length, T width) implements Shape {
    @Override
    public String toPrintString() {
        return """
                This is:
                ▭
                """;
    }
}
