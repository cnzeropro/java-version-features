package org.zero;

/**
 * @author zero
 * @since 2023/11/10
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
