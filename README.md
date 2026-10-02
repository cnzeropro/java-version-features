# java-version-features

按 Java 版本整理的**新特性示例**。Maven 多模块工程，**21 个模块覆盖 Java 3 → Java 23**，每个模块用可运行代码 + JUnit 测试演示该版本引入的语言特性与 API 变化；各模块主类的 Javadoc 里附有完整的 JEP 编号与官方链接。

## 模块一览

| 模块 | 主要演示内容 |
| --- | --- |
| java-3 | 占位模块（仅保留 J2SE 1.3 官方文档链接） |
| java-4 | 断言机制（`assert`，`-ea` / `-da` 粗细粒度开关） |
| java-5 | 增强 for、泛型、可变参数、类型安全枚举、静态导入、注解、自动装箱拆箱 |
| java-6 | 占位模块（仅保留 JDK 6 官方文档链接） |
| java-7 | 二进制字面量、数字下划线、switch 支持 String、菱形类型推断、try-with-resources、多异常捕获、ThreadLocalRandom |
| java-8 | Lambda 与四大函数式接口、方法/构造器/数组引用、接口默认与静态方法、Stream、Optional、Date-Time API（JSR 310）、类型注解、`@Repeatable`、Base64、Nashorn |
| java-9 | 模块系统 JPMS（261）、接口私有方法、try-with-resources 精简、集合 `of` 工厂、Stream / Optional / Process / CompletableFuture 增强、紧凑字符串（254）、JShell（222）、StackWalker（259） |
| java-10 | `var` 局部变量类型推断（286）、不可修改集合 API、`Optional.orElseThrow()`、类文件版本 54.0 |
| java-11 | HttpClient（321）、单文件源码运行（330）、Lambda 参数局部变量语法（323）、String 新方法、TLS 1.3（332）、ChaCha20/Poly1305（329）、Epsilon GC（317）、ZGC（333）、移除 Java EE/CORBA（322） |
| java-12 | Shenandoah GC（189）、JVM 常量 API（334）、默认 CDS 归档（341）、G1 可中止混合回收（344）与归还内存（346）、紧凑数字格式化；预览 Switch 表达式（325） |
| java-13 | 动态 CDS 归档（350）、ZGC 归还内存（351）、重写遗留 Socket API（353）、ByteBuffer 批量读写；预览 文本块（355）、Switch 表达式（354） |
| java-14 | 预览 instanceof 模式匹配（305）、记录类（359）、文本块（368）；正式 Switch 表达式（361）、JFR 事件流（349）、移除 CMS GC（363）、ZGC 移植 macOS/Windows（364、365） |
| java-15 | 正式 文本块（378）、隐藏类（371）、EdDSA（339）、ZGC（377）、Shenandoah（379）；预览 密封类（360）、记录类（384）、instanceof 模式匹配（375） |
| java-16 | 正式 记录类（395）、instanceof 模式匹配（394）、强封装 JDK 内部（396）、Unix 域套接字通道（380）、弹性元空间（387）；预览 密封类（397）、向量 API（338） |
| java-17 | 正式 密封类（409）、恢复始终严格浮点语义（306）、增强伪随机数生成器（356）、上下文特定反序列化过滤器（415）、HexFormat；预览 switch 模式匹配（406）、外部函数和内存 API（412） |
| java-18 | 正式 默认 UTF-8（400）、简易 Web 服务器（408）、JavaDoc 代码片段（413）、核心反射重写（416）、弃用终结机制（421）；预览 switch 模式匹配（420） |
| java-19 | 预分配 HashMap / HashSet 创建方法；预览 记录模式（405）、虚拟线程（425）、switch 模式匹配（427）、外部函数和内存 API（424）；孵化 结构化并发（428） |
| java-20 | 已列出 JEP 清单（记录模式 432、switch 模式匹配 433、外部函数和内存 API 434、虚拟线程 436、作用域值 429、结构化并发 437、向量 API 438），示例代码待补 |
| java-21 | 正式 记录模式（440）、switch 模式匹配（441）、虚拟线程（444）、有序集合（431）、分代 ZGC（439）、Unicode Emoji 支持；预览 字符串模板（430）、匿名模式与变量（443）、作用域值（446）、结构化并发（453） |
| java-22 | 正式 外部函数和内存 API（454）、匿名变量与模式（456）、多文件源码启动（458）、G1 区域固定（423）；预览 类文件 API（457）、流收集器（461）、字符串模板（459）、作用域值（464） |
| java-23 | 正式 Markdown 文档注释（467）、ZGC 默认分代模式（474）、弃用 Unsafe 内存访问（471）；预览 基本类型模式（455）、模块导入声明（476）、流收集器（473）、灵活构造函数体（482）、作用域值（481） |

> `java-3`、`java-6` 为占位模块（类体为空，仅保留官方文档链接）；`java-20` 已列出 JEP 清单但示例代码待补。

## 构建与运行

坐标：`org.zero:java-version-features`。Maven 多模块，**每个模块需要对应版本的 JDK 才能编译**（例如 `java-21` 需 JDK 21+，`java-17` 需 JDK 17+）。

```bash
# 校验所有 POM 与模块结构（不需要各版本 JDK）
mvn validate

# 构建并测试单个版本模块（请使用对应 JDK）
mvn -pl java-17 test
mvn -pl java-21 test
```

## 目录结构

```
.
├── pom.xml                # 聚合 POM（parent）
├── java-3/ … java-23/     # 每个 Java 版本一个模块
│   ├── pom.xml
│   └── src/main/java/org/zero/…   # 特性示例
│       src/test/java/org/zero/…   # JUnit 测试
└── explanation.txt        # 提交用词约定（add / optimize / improve / enhance / introduce / support / upgrade / change）
```

## 许可

[MIT](LICENSE)
