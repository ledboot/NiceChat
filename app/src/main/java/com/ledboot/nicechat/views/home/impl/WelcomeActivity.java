package com.ledboot.nicechat.views.home.impl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.SaveCallback;
import com.ledboot.nicechat.R;
import com.ledboot.nicechat.core.Debuger;
import com.ledboot.nicechat.presenters.home.impl.WelcomePresenterImpl;
import com.ledboot.nicechat.views.BaseActivity;
import com.ledboot.nicechat.views.home.IWelcomeView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wengaowei728 on 16/6/13.
 */
public class WelcomeActivity extends BaseActivity implements IWelcomeView{

    @Inject
    WelcomePresenterImpl mWelcomePresenter;

    @BindView(R.id.login)
    Button btLogin;

    @BindView(R.id.regist)
    Button btRegist;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        AVObject testObj = new AVObject("TestObject");
        testObj.put("words","hello world");
        testObj.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                Debuger.logD("save success!");
            }
        });
    }

    @Override
    public void initInjector() {
        mActivityComponent.inject(this);
        mPresenter = mWelcomePresenter;
    }

    @Override
    public void initUiAndListener() {
        ButterKnife.bind(this);
        mWelcomePresenter.attachView(this);
    }

    @Override
    public int setContentView() {
        return R.layout.activity_welcome;
    }

    @OnClick(R.id.login)
    public void login(View v){
        mWelcomePresenter.goLogin();
    }

}
