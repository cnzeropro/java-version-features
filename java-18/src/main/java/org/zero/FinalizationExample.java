package org.zero;

/**
 * @author Zero (cnzeropro@163.com)
 * @since 2024/12/16
 */
public class FinalizationExample {
    @Override
    protected void finalize() throws Throwable {
        try {
            System.out.println("finalize is deprecated");
        } finally {
            super.finalize();
        }
    }
}
