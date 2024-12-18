package org.zero;

import lombok.SneakyThrows;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Set;

/**
 * <a href="https://openjdk.org/projects/jdk/13/">JDK 13</a>
 * <a href="https://docs.oracle.com/javase/13/">JDK 13 Documentation</a>
 * <h1>Features</h1>
 * <ol>
 *     <li>FileSystems 类新增 newFileSystem 的重构方法{@link #overloadMethodsForFileSystems()}</li>
 *     <li><a href="https://openjdk.org/jeps/351">351</a>：ZGC 取消提交未使用的内存（ZGC Uncommit Unused Memory）{@link #zgc()}</li>
 * </ol>
 * <ol>
 *     <li><a href="https://openjdk.org/jeps/355">355</a>：文本块（Text Blocks）[首次预览]</li>
 *     <li><a href="https://openjdk.org/jeps/354">354</a>：switch 表达式（Switch Expressions）[第二次预览]</li>
 * </ol>
 *
 * @author Zero
 * @since 2020/04/01
 */
public class Java13 {
    @SneakyThrows
    public void overloadMethodsForFileSystems() {
        FileSystem fileSystem = FileSystems.newFileSystem(Path.of("C:\\Users\\"));
        try (fileSystem) {
            Set<String> attributeViews = fileSystem.supportedFileAttributeViews();
            System.out.println(attributeViews);
        }
    }

    /**
     * ZGC 取消提交未使用的内存
     * <p>
     * ZGC 已得到增强，可将未使用的堆内存返回给操作系统。这对于担心内存占用的应用程序和环境非常有用。
     * 默认情况下，此功能处于启用状态，但可以使用 -XX:-ZUncommit 显式禁用。
     * 而且内存不会被取消提交，因此堆大小会缩小到最小堆大小以下。
     * 这意味着，如果将最小堆大小（-Xms）和最大堆大小（-Xmx）配置相等，则隐式禁用此功能。
     */
    public void zgc() {
    }
}
