package org.zero;

import lombok.SneakyThrows;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Set;

/**
 * <a href="https://docs.oracle.com/javase/13">JDK 13 Documentation</a>
 * <h2>Language Features</h2>
 * <ol>
 * </ol>
 *
 * <h2>Previews and Incubator</h2>
 * <ol>
 *     <li>【new】文本块（Text Blocks）（首次预览）</li>
 *     <li>【update】switch 表达式（Switch Expressions）（第二次预览）</li>
 * </ol>
 *
 * <h2>Libraries Improvements</h2>
 * <ol>
 *     <li>【new】FileSystems 类新增 newFileSystem 的重构方法（Added FileSystems.newFileSystem(Path, Map<String, ?>) Method）{@link Java13#overloadMethodsForFileSystems()}</li>
 * </ol>
 *
 * <h2>Changes</h2>
 * <ol>
 *     <li>【update】支持 Unicode 12.1（Support for Unicode 12.1）</li>
 *     <li>【update】ZGC 取消提交未使用的内存（ZGC Uncommit Unused Memory）。
 *     ZGC 已得到增强，可将未使用的堆内存返回给操作系统。这对于担心内存占用的应用程序和环境非常有用。
 *     默认情况下，此功能处于启用状态，但可以使用 -XX:-ZUncommit 显式禁用。
 *     而且内存不会被取消提交，因此堆大小会缩小到最小堆大小以下。
 *     这意味着，如果将最小堆大小（-Xms）和最大堆大小（-Xmx）配置相等，则隐式禁用此功能。</li>
 *     <li>【update】ZGC 支持的最大堆大小增加到 16TB（ZGC Maximum Heap Size Increased to 16TB）。
 *     ZGC 支持的最大堆大小从 4TB 增加到 16TB。</li>
 * </ol>
 *
 * @author Zero
 */
public class Java13 {
    @SneakyThrows
    public void overloadMethodsForFileSystems() {
        FileSystem fileSystem = FileSystems.newFileSystem(Path.of(""));
        try (fileSystem) {
            Set<String> attributeViews = fileSystem.supportedFileAttributeViews();
            System.out.println(attributeViews);
        }
    }
}
