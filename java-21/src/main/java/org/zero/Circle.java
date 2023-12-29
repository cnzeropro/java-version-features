package org.zero;

/**
 * @author zero
 * @since 2023/9/28
 */
public record Circle(double radius) implements Shape {
    @Override
    public String toPrintString() {
        return """
                This is:
                ○
                """;
    }
}
