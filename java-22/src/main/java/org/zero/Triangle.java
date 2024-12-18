package org.zero;

/**
 * @author zero
 * @since 2023/11/10
 */
public record Triangle<T extends Number>(T x, T y, T z) implements Shape {
    @Override
    public String toPrintString() {
        return """
                This is:
                △
                Which with x: %s, y: %s, z: %s
                """
                .formatted(x, y, z);
    }
}
