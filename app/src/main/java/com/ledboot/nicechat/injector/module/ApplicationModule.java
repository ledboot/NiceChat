package com.ledboot.nicechat.injector.module;

import android.content.Context;

import com.ledboot.nicechat.NiceChatApplication;
import com.ledboot.nicechat.injector.ContextLife;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Eleven on 16/4/22.
 */
@Module
public class ApplicationModule {

    public NiceChatApplication mApplication;


    public ApplicationModule(NiceChatApplication application){
        mApplication = application;

    }

    @Provides
    @Singleton
    @ContextLife("Application")
    public Context provideContext() {
        return mApplication.getApplicationContext();
    }
}
