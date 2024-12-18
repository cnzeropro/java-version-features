package org.zero;

/**
 * @author zero
 * @since 2023/11/10
 */
public sealed interface Shape permits Circle, Rectangle, Triangle {
    String toPrintString();
}
