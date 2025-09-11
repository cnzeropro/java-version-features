package org.zero;

import jdk.jfr.Category;
import jdk.jfr.Description;
import jdk.jfr.Event;
import jdk.jfr.Label;
import jdk.jfr.Name;
import jdk.jfr.Period;
import jdk.jfr.StackTrace;

/**
 * @author Zero (cnzeropro@163.com)
 * @since 2025/9/10
 */
@Name("org.zero.example.Test")
@Description("Custom Event for Test")
@Label("Custom")
@Category({"Example", "Custom"})
@Period("1 s")
@StackTrace(false)
public class CustomEvent extends Event {
    @Label("Message")
    String message;
}
