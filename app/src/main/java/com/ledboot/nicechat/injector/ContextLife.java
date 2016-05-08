package com.ledboot.nicechat.injector;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by Eleven on 16/4/22.
 */

@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ContextLife {
    String value() default "Application";
}

