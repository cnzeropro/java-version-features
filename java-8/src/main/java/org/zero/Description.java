package org.zero;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author yufa.wang (yufa.wang@ronganchina.com)
 * @since 2023/6/15
 */
@Documented
@Inherited
@Retention(RUNTIME)
@Target({TYPE, FIELD, CONSTRUCTOR, METHOD, ANNOTATION_TYPE})
@Repeatable(Descriptions.class)
public @interface Description {
    String value() default "";
}
