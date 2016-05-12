package com.ledboot.nicechat.views.home.impl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ledboot.nicechat.core.Debuger;
import com.ledboot.nicechat.launcher.MainPanel;

/**
 * Created by wengaowei728 on 16/5/10.
 */
public class MainActivity extends AppCompatActivity{

    private static final java.lang.String TAG = MainActivity.class.getSimpleName();
    MainPanel mMainPanel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainPanel = new MainPanel(this);
        setContentView(mMainPanel);

        Debuger.logD(TAG," finish MainActivity~~~~");
    }
}
