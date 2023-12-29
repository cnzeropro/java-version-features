package org.zero;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.WeakHashMap;

/**
 * <a href="https://docs.oracle.com/javase/19/index.html">JDK 19 Documentation</a>
 * <h2>Language Changes</h2>
 * <ol>
 *     <li>【update】增强 switch 表达式。（第三次预览）</li>
 *     <li>【update】增强 record 类在 instanceof 中的使用。（首次预览）</li>
 * </ol>
 * <h2>Changes</h2>
 * <ol>
 *     <li>【update】支持 Unicode 14.0。</li>
 *     <li>【new】新增创建预分配方法。{@link Java19#addNewXxxMethod()}</li>
 * </ol>
 *
 * @author @author Zero
 * @since 2018/12/25
 */
public class Java19 {

    public void addNewXxxMethod() {
        // Map
        var hashMap = HashMap.newHashMap(16);
        var linkedHashMap = LinkedHashMap.newLinkedHashMap(32);
        var weakHashMap =  WeakHashMap.newWeakHashMap(4);

        // Set
        var hashSet = HashSet.newHashSet(8);
        var linkedHashSet = LinkedHashSet.newLinkedHashSet(4);


    }
}