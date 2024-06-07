# Java 8

[Java Platform Standard Edition 8 Documentation](https://docs.oracle.com/javase/8/docs)

## What's New in JDK 8

### Java Programming Language

1. Lambda 表达式

   **Lambda Expressions**

2. 方法引用

   **Method References**

3. 接口默认方法

   **Default Methods**

4. 重复注解

   **Repeating Annotations**

5. 类型批注

   **Type Annotations**

6. 改进类型推断

   **Improved Type Inference**

7. 方法参数反射

   **Method Parameter Reflection**

### Collections

1. HashMap 在密钥冲突下的性能改进

   **Performance Improvement for HashMaps with Key Collisions**

2. Stream 管道流操作

   **Stream API**

### Security

1. 默认启用客户端TLS 1.2

   **Client-side TLS 1.2 enabled by default**

2. AccessController.doPrivileged 的新变体

   **New Variants of `AccessController.doPrivileged`**

3. 更强的基于密码的加密算法

   **Stronger algorithms for password-based encryption**

4. JSSE 服务器中的 SSL/TLS 服务器名称指示（SNI）扩展支持

   **SSL/TLS Server Name Indication (SNI) Extension support in JSSE Server**

5. 对 AEAD 算法的支持

   **Support for AEAD Algorithms**

6. KeyStore 增强

   **KeyStore Enhancements**

7. SHA-224 消息摘要算法

   **`SHA-224` Message Digests**

8. 增强对 NSA Suite B 加密的支持

   **Enhanced Support for NSA Suite B Cryptography**

9. 更好地支持高熵随机数生成

   **Better Support for High Entropy Random Number Generation**

10. 用于配置 X.509 证书吊销检查的新 java.security.cert.PKIXRevocationChecker 类

    **New `java.security.cert.PKIXRevocationChecker` class for configuring revocation checking of X.509 certificates**

11. Windows 64位 PKCS 11

    **64-bit PKCS11 for Windows**

12. Kerberos 5 重放缓存中的新缓存类型

    **New rcache Types in Kerberos 5 Replay Caching**

13. 支持 Kerberos 5 协议转换和受限委派

    **Support for Kerberos 5 Protocol Transition and Constrained Delegation**

14. GSS-API/GSS 5 机制的未绑定 SASL

    **Unbound SASL for the GSS-API/Kerberos 5 mechanism**

15. 多主机名SASL服务

    **SASL service for multiple host names**

16. JNI 桥接到 Mac OS X 上的本机 JGSS

    **JNI bridge to native JGSS on Mac OS X**

17. 在 SunJSSE 提供程序中支持更强的临时 DH 密钥

    **Support for stronger strength ephemeral DH keys in the SunJSSE provider**

18. 在 JSSE 中支持服务器端密码套件首选项定制

    **Support for server-side cipher suites preference customization in JSSE**

### JavaFX

1. 新的Modena主题

   **The new Modena theme**

2. 新的 SwingNode 类

   **The new SwingNode class**

3. 新的UI控件

   **The new UI Controls include the `DatePicker` and the `TreeTableView` controls**

4. javafx.print 包为 JavaFX Printing API 提供公共类

   **The `javafx.print` package provides the public classes for the JavaFX Printing API**

5. 3D 图形新功能：3D 形状、相机、灯光、子场景、材质、拾取和抗锯齿

   **The 3D Graphics features now include 3D shapes, camera, lights, subscene, material, picking, and antialiasing**

6. WebView 类提供了新的特性和改进

   **The `WebView` class provides new features and improvements**

7. 增强的文本支持

   **Enhanced text support**

8. 添加对 Hi—DPI 显示的支持

   **Support for Hi-DPI displays has been added in this release**

9. CSS Styleable* 类成为公共API

   **The `CSS Styleable*` classes became public API**

10. 新的 ScheduledService 类允许自动重新启动服务

    **The new `ScheduledService` class allows to automatically restart the service**

11. JavaFX 现在可用于 ARM 平台

    **JavaFX is now available for ARM platforms**

### Tools

1. 提供 jjs 命令以调用Nashorn引擎

   **The `jjs` command is provided to invoke the `Nashorn` engine**

2. java 命令可启动 JavaFX 应用程序

   **The `java` command launches JavaFX applications**

3. 重新制作 java 命令手册页

   **The `java` man page has been reworked**

4. 用于分析类文件的 jdeps 命令行工具

   **The `jdeps` command-line tool is provided for analyzing class files**

5. JMX 提供对诊断命令的远程访问

   **Java Management Extensions (JMX) provide remote access to diagnostic commands**

6. jarsigner 工具增加一个从时间戳授权机构（TSA）请求签名时间戳的选项

   **The jarsigner tool has an option for requesting a signed time stamp from a Time Stamping Authority (TSA)**

#### Javac tool

1. `javac` 命令的 `-parameters` 选项可用于存储形参名称，并使反射 API 能够检索形参名称

   **The javadoc tool supports the new DocTree API that enables you to traverse Javadoc comments as abstract syntax
   trees**

2. `javadoc` 工具支持新的 Javadoc Access API

   **The `javadoc` tool supports the new Javadoc Access API**

3. `javadoc` 工具现在支持在运行时生成的文件中检查 javadoc 注释
    