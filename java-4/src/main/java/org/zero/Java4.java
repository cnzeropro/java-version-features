package org.zero;

/**
 * <a href="http://docs.oracle.com/javase/1.4.2/docs/">Java(TM) 2 SDK Documentation</a>
 * <h1>Features</h1>
 * <ol>
 *     <li>断言功能（Assertion Facility）{@link #assertionFacility()}</li>
 * </ol>
 *
 * @author Zero
 * @since 2018/12/25
 */
public class Java4 {
    /**
     * 断言功能
     * <p>
     * 断言是 Java 1.4 的一个语言特性，它允许程序员在运行时检查程序逻辑的正确性。
     * 断言使用assert关键字声明。
     * <table>
     *     <tr>
     *         <th>表达式</th>
     *         <th>说明</th>
     *     </tr>
     *     <tr>
     *         <td>assert BoolExpression;</td>
     *         <td>计算 boolean 表达式：如果为 true 正常通过断言语句，否则抛出没有详细信息的 AssertionError</td>
     *     </tr>
     *     <tr>
     *         <td>assert BoolExpression:MsgExpression;</td>
     *         <td>计算 boolean 表达式：如果为 true 正常通过断言语句，否则运行指定表达式获取字符串，使用其构造 AssertionError 并抛出</td>
     *     </tr>
     * </table>
     * 默认情况下，断言在运行时处于禁用状态。通过两个命令行开关允许有选择地启用或禁用断言。
     * <ul>
     *     <li>要启用粗粒度的断言：使用 <code>-enableassertions</code> 或 <code>-ea</code>；要启用细粒度的断言：使用 <code>-enableassertions:xxx.xxx</code> 或 <code>-ea:xxx.xxx</code></li>
     *     <li>要禁用用粗粒度的断言：使用 <code>-disableassertions</code> 或 <code>-da</code>；要禁用细粒度的断言：使用 <code>-disableassertions:xxx.xxx</code> 或 <code>-da:xxx.xxx</code></li>
     * </ul>
     */
    public void assertionFacility() {
        double randomNum = Math.random();
        System.out.println("randomNum: " + randomNum);

        assert randomNum > 0.3;
        assert randomNum > 0.5 : "不大于0.5";
        assert randomNum > 0.7 : new StringBuffer("Error: ").append(randomNum)
                .append(" is less than or equal to ")
                .append(0.7);
    }
}