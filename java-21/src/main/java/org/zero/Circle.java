package org.zero;

/**
 * @author zero
 * @since 2023/11/10
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
