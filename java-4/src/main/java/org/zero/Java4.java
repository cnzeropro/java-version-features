package org.zero;

/**
 * @author Zero
 * @since 2018/12/25
 */
public class Java4 {
    /**
     * 新增断言功能
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