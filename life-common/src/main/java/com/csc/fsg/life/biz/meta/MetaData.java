package com.csc.fsg.life.biz.meta;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* Modifications: ENH911, T0150, WMA-1559 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MetaData {
	int fieldSequence() default 99999;
    String datatype() default "";
    boolean signed() default true;
	int size() default 0;
    int occurrenceCount() default 1;
    int scale() default 0;
    int offset() default 0;
    boolean required() default false;
    String avref() default "";
    boolean forceAvmBase() default false;
}