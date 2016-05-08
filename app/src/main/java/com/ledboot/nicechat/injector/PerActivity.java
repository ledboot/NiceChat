package com.ledboot.nicechat.injector;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Eleven on 16/4/22.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
