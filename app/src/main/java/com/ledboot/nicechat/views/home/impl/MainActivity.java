package com.ledboot.nicechat.views.home.impl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.ledboot.nicechat.core.Debuger;
import com.ledboot.nicechat.launcher.MainPanel;
import com.ledboot.nicechat.views.BaseActivity;

/**
 * Created by wengaowei728 on 16/5/10.
 */
public class MainActivity extends BaseActivity{

    private static final java.lang.String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Debuger.logD(TAG," finish MainActivity~~~~");
    }

    @Override
    public void initInjector() {

    }

    @Override
    public void initUiAndListener() {

    }

    @Override
    public int setContentView() {
        return 0;
    }

    @Override
    public View setCustomContentView() {
        MainPanel mMainPanel = new MainPanel(this);
        return mMainPanel;
    }
}
