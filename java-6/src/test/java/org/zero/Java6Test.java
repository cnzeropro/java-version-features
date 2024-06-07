package org.zero;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 * @author zero
 * @since 2020/12/26
 */
public class Java6Test {

    @Before
    public void setUp() {
        System.out.println("************************************************** Start **************************************************");
    }

    @After
    public void tearDown() {
        System.out.println("*************************************************** End ***************************************************");
    }

    @BeforeClass
    public static void init() {
        System.out.println("Java 6 Test Start...");
    }

    @AfterClass
    public static void destroy() {
        System.out.println("Java 6 Test End");
        System.out.println("=============================================================================================================================\n");
    }
}
