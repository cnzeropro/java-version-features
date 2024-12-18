package org.zero;

/**
 * @author zero
 * @since 2023/11/10
 */
public record Circle<T extends Number>(T radius) implements Shape {
    @Override
    public String toPrintString() {
        return """
                This is:
                ○
                Which with radius: %s
                """
                .formatted(radius);
    }
}
