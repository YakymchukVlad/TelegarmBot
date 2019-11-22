package com.khnu.yakymchuk.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.math.BigDecimal;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Range {

    int maxVal() default 450;

    int min() default 0;

}
