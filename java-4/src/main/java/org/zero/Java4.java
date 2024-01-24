package org.zero;

/**
 * <a href="http://docs.oracle.com/javase/1.4.2/docs/">Java(TM) 2 SDK Documentation</a>
 * <h2>Enhancements</h2>
 * <ol>
 *     <li>【new】断言功能（Assertion Facility）{@link Java4#addAssertionFacility()}</li>
 * </ol>
 *
 * @author @author Zero
 * @since 2018/12/25
 */
public class Java4 {
    /**
     * 新增断言功能
     * <p>
     * 断言语句有两种形式：
     * <table>
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
     *     <li>要启用粗粒度的断言：使用 -enableassertions 或 -ea；要启用细粒度的断言：使用 -enableassertions:xxx.xxx 或 -ea:xxx.xxx。</li>
     *     <li>要启用粗粒度的断言：使用 -disableassertions 或 -da；要启用细粒度的断言：使用 -disableassertions:xxx.xxx 或 -da:xxx.xxx。</li>
     * </ul>
     */
    public void addAssertionFacility() {
        double randomNum = Math.random();

        assert randomNum > 0.3;
        assert randomNum > 0.5 : "不大于0.5";
        assert randomNum > 0.7 : new StringBuffer("Error: ").append(randomNum)
                .append(" is less than or equal to ")
                .append(0.7);
    }
}