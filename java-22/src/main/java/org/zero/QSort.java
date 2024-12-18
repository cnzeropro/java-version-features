package org.zero;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

/**
 * @author Zero (cnzeropro@163.com)
 * @since 2024/12/11
 */
public class QSort {
    static int compare(MemorySegment elem1, MemorySegment elem2) {
        return Integer.compare(elem1.get(ValueLayout.JAVA_INT, 0), elem2.get(ValueLayout.JAVA_INT, 0));
    }
}
