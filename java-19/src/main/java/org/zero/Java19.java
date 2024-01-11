package org.zero;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.WeakHashMap;

/**
 * <a href="https://docs.oracle.com/javase/19">JDK 19 Documentation</a>
 * <h2>Language Features</h2>
 * <ol>
 * </ol>
 *
 * <h2>Previews and Incubator</h2>
 * <ol>
 *     <li>【update】switch 模式匹配（Pattern Matching for switch Expressions and Statements）（第三次预览）</li>
 *     <li>【update】记录模式（Record Patterns）（首次预览）</li>
 *     <li>【new】虚拟线程（Virtual Threads）（首次预览）</li>
 *     <li>【new】结构化并发（Structured Concurrency）（首次孵化）</li>
 *     <li>【new】外部函数和内存API（Foreign Function & Memory API）（首次预览）</li>
 *     <li>【new】向量 API（Vector API）（第四次孵化）</li>
 * </ol>
 *
 * <h2>Libraries Improvements</h2>
 * <ol>
 *     <li>【new】创建预分配 hash 集合的方法（New Methods to Create Preallocated HashMaps and HashSets）{@link Java19#addPreallocatedHashes()}</li>
 * </ol>
 *
 * <h2>Changes</h2>
 * <ol>
 *     <li>【update】支持 Unicode 14.0（Support Unicode 14.0）</li>
 * </ol>
 *
 * @author Zero
 * @since 2023/02/21
 */
public class Java19 {

    public void addPreallocatedHashes() {
        // Map
        // 其中的参数是预期的元素数量（即可存放个数，超过此数量会进行扩容操作），与构造器中参数不同的是，构造器中参数是初始容量。因此 HashMap.newHashMap(12) = new HashMap(16);
        var hashMap = HashMap.newHashMap(12);
        var linkedHashMap = LinkedHashMap.newLinkedHashMap(24);
        var weakHashMap = WeakHashMap.newWeakHashMap(3);

        // Set
        var hashSet = HashSet.newHashSet(6);
        var linkedHashSet = LinkedHashSet.newLinkedHashSet(3);
    }
}