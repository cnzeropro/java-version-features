package org.zero;

/**
 * @author zero
 * @since 2023/9/28
 */
public record Triangle(int x, int y, int z) implements Shape {
    @Override
    public String toPrintString() {
        return """
                This is:
                △
                """;
    }
}
