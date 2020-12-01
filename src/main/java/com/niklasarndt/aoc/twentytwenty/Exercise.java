package com.niklasarndt.aoc.twentytwenty;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Exercise {

    /**
     * @return The name of this {@link Exercise}.
     */
    int value() default -1;
}
