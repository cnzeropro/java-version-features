package org.zero;

/**
 * @author zero
 * @since 2023/9/28
 */
public sealed interface Shape permits Circle, Rectangle, Triangle {
    String toPrintString();
}
