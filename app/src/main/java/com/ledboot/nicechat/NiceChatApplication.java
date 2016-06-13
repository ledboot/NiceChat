package com.ledboot.nicechat;

import android.app.Application;
import android.content.Context;

import com.avos.avoscloud.AVOSCloud;
import com.ledboot.nicechat.injector.component.ApplicationComponent;
import com.ledboot.nicechat.injector.component.DaggerApplicationComponent;
import com.ledboot.nicechat.injector.module.ApplicationModule;

/**
 * Created by Eleven on 16/4/22.
 */
public class NiceChatApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initComponent();
        AVOSCloud.initialize(this,"GXD8LRwyyeKis4cXLeLRLQHp-gzGzoHsz","RVU8K4VjUDamXXNKI6scBPun");
    }

    private void initComponent() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}
