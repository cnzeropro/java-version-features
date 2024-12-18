package org.zero;

/**
 * @author zero
 * @since 2023/11/10
 */
public record Rectangle<T extends Number>(T length, T width) implements Shape {
    @Override
    public String toPrintString() {
        return """
                This is:
                ▭
                Which with length: %s, width: %s
                """
                .formatted(length, width);
    }
}
