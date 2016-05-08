package com.ledboot.nicechat.injector.module;

import android.app.Activity;
import android.content.Context;

import com.ledboot.nicechat.injector.ContextLife;
import com.ledboot.nicechat.injector.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Eleven on 16/4/22.
 */

@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity){
        mActivity = activity;
    }

    @Provides
    @PerActivity
    @ContextLife("Activity")
    public Context provideContext(){
        return mActivity;
    }

    @Provides
    @PerActivity
    public Activity provideActivity(){
        return mActivity;
    }
}
