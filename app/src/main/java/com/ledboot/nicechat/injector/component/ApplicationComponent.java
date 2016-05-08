package com.ledboot.nicechat.injector.component;

import android.content.Context;

import com.ledboot.nicechat.injector.ContextLife;
import com.ledboot.nicechat.injector.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Eleven on 16/4/22.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ContextLife("Application")
    Context getContext();
}
